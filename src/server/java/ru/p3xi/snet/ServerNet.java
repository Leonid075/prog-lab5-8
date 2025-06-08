package ru.p3xi.snet;

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

public class ServerNet {
    private static final int port = 6789;
    private static final int bufferSize = 10240;
    private ByteBuffer buffer;
    private DatagramChannel channel;
    private SocketAddress clientAddress;
    private ObjectInputStream is;

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
        try {
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            is = new ObjectInputStream(bis);
        } catch (Exception e) {
            System.out.println(e);
        }
        buffer.clear();
        try {
            CommandRequest request = (CommandRequest) is.readObject();
            return request;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void write(CommandResponce responce) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(responce);
        os.flush();

        byte[] serializedData = bos.toByteArray();

        ByteBuffer responseBuffer = ByteBuffer.allocate(bufferSize);
        responseBuffer.put(serializedData);
        responseBuffer.flip();

        channel.send(responseBuffer, clientAddress);

        os.close();
        bos.close();
    }
}
