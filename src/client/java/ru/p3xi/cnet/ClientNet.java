package ru.p3xi.cnet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import ru.p3xi.request.CommandRequest;
import ru.p3xi.request.CommandResponce;

public class ClientNet {
    ObjectOutputStream os;
    ObjectInputStream is;
    private static final String host = "localhost";
    private static final int port = 6789;
    private static final int bufferSize = 10240;
    private DatagramChannel channel;
    private SocketAddress serverAddress;

    public ClientNet() {
        try {
            channel = DatagramChannel.open();
            serverAddress = new InetSocketAddress(host, port);
        } catch (Exception e) {

        }
    }

    public CommandResponce SendRequest(CommandRequest request) {
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(request);
            oos.flush();

            byte[] serializedData = bos.toByteArray();

            buffer.put(serializedData);
            buffer.flip();

            channel.send(buffer, serverAddress);
            oos.close();
            bos.close();
            buffer.clear();
        } catch (IOException e) {
            return new CommandResponce.Builder().build();
        }
        try {
            SocketAddress senderAddress = channel.receive(buffer);
            if (senderAddress != null) {
                buffer.flip();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);

                try {
                    ByteArrayInputStream bis = new ByteArrayInputStream(data);
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    Object receivedObject = ois.readObject();

                    CommandResponce responce = (CommandResponce) receivedObject;

                    ois.close();
                    bis.close();
                    return responce;
                } catch (Exception e) {
                    System.out.println(e);
                    return new CommandResponce.Builder().isOk(false).responce("Ошибка передачи").build();
                }
            }
        } catch (IOException e) {
            return new CommandResponce.Builder().isOk(false).responce("Ошибка передачи").build();
        }
        return new CommandResponce.Builder().isOk(false).responce("Ошибка передачи").build();
    }

}
