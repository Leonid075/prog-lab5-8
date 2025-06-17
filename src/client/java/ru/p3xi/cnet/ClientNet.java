package ru.p3xi.cnet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import ru.p3xi.request.*;

public class ClientNet {
    private static final String host = "localhost";
    private static final int port = 6789;
    private static final int bufferSize = 65536;
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
        try {
            byte[] serializedData = Serializer.serialize(request);

            if (serializedData.length > bufferSize) {
                return new CommandResponce.Builder().isOk(false).responce("Запрос слишом длинный").build();
            }

            ByteBuffer buffer = ByteBuffer.allocate(serializedData.length);

            buffer.put(serializedData);
            buffer.flip();

            channel.send(buffer, serverAddress);
        } catch (IOException e) {
            
        }
        try {
            ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
            SocketAddress senderAddress = channel.receive(buffer);
            if (senderAddress != null) {
                buffer.flip();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);

                try {
                    return Serializer.deserialize(data);
                } catch (Exception e) {
                    System.out.println(e);
                    return new CommandResponce.Builder().isOk(false).responce("Ошибка передачи").build();
                }
            }
        } catch (IOException e) {

        }
        return new CommandResponce.Builder().isOk(false).responce("Ошибка передачи").build();
    }

}
