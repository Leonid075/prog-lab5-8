package ru.p3xi.snet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.p3xi.cm.Model;
import ru.p3xi.request.*;
import ru.p3xi.scommands.ArgsException;
import ru.p3xi.scommands.Command;

public class ServerNet {
    private static final int port = 6789;
    private static final int bufferSize = 65536;
    private ByteBuffer buffer;
    private DatagramChannel channel;
    // private SocketAddress clientAddress;

    private ExecutorService executor;

    public ServerNet() throws IOException {
        buffer = ByteBuffer.allocate(bufferSize);
        channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(port));
        channel.configureBlocking(false);
        executor = Executors.newCachedThreadPool();
    }

    public void process(HashMap<String, Command> commands, Model model) {
        SocketAddress clientAddress;
        try {
            clientAddress = channel.receive(buffer);
        } catch (IOException e) {
            return;
        }
        if (clientAddress == null)
            return;
        new Thread(() -> {
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            buffer.clear();
            executor.submit(() -> {
                try {
                    CommandRequest request = (CommandRequest) Serializer.deserialize(data);

                    if (request == null)
                        return;
                    if (!request.getCommand().equals("check_user") && !request.getCommand().equals("register")
                            && !model.comparePassword(request.getUsername(), request.getPassword())) {
                        new Thread(() -> {
                            try {
                                write(new CommandResponce.Builder().isOk(false).responce("Неверный пароль")
                                        .build(), clientAddress);
                            } catch (IOException ee) {
                            }
                        }).start();
                    }
                    System.out.println(request.getCommand());
                    try {
                        Command executed = commands.get(request.getCommand());
                        CommandResponce responce = executed.execute(model, request);
                        new Thread(() -> {
                            try {
                                write(responce, clientAddress);
                            } catch (IOException ee) {
                            }
                        }).start();
                    } catch (ArgsException e) {
                        new Thread(() -> {
                            try {
                                write(new CommandResponce.Builder().isOk(false)
                                        .responce("Неверные аргуметы команды")
                                        .build(), clientAddress);
                            } catch (IOException ee) {
                            }
                        }).start();
                    }
                } catch (Exception e) {
                    return;
                }
            });
        }).start();
    }

    public void write(CommandResponce responce, SocketAddress clientAddress) throws IOException {
        byte[] serializedData = Serializer.serialize(responce);
        if (serializedData.length > bufferSize) {
            write(new CommandResponce.Builder().isOk(false).responce("Ответ слишом длинный").build(), clientAddress);
            return;
        }

        ByteBuffer responseBuffer = ByteBuffer.allocate(serializedData.length);
        responseBuffer.put(serializedData);
        responseBuffer.flip();

        channel.send(responseBuffer, clientAddress);

        buffer.clear();
    }
}
