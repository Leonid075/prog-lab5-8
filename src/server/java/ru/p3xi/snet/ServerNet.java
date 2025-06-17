package ru.p3xi.snet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import ru.p3xi.request.*;

public class ServerNet {
    private static final int port = 6789;
    private static final int bufferSize = 65536;
    private ByteBuffer buffer;
    private DatagramChannel channel;
    private SocketAddress clientAddress;

    public ServerNet() throws IOException {
        buffer = ByteBuffer.allocate(bufferSize);
        channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(port));
        channel.configureBlocking(false);
    }

    public boolean ready() throws IOException {
        clientAddress = channel.receive(buffer);
        if (clientAddress != null)
            return true;
        else
            return false;
    }

    public CommandRequest read() {
        buffer.flip();
        byte[] data = new byte[buffer.remaining()];
        buffer.get(data);
        buffer.clear();
        try {
            return Serializer.deserialize(data);
        } catch (Exception e) {
            return null;
        }
    }

    public void write(CommandResponce responce) throws IOException {
        byte[] serializedData = Serializer.serialize(responce);
        if (serializedData.length > bufferSize) {
            write(new CommandResponce.Builder().isOk(false).responce("Ответ слишом длинный").build());
            return;
        }

        ByteBuffer responseBuffer = ByteBuffer.allocate(serializedData.length);
        responseBuffer.put(serializedData);
        responseBuffer.flip();

        channel.send(responseBuffer, clientAddress);

        buffer.clear();
    }
}
