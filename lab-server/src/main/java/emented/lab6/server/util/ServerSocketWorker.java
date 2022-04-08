package emented.lab6.server.util;

import emented.lab6.common.util.DeSerializer;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.Serializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class ServerSocketWorker {

    private Selector selector;
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private int port = 228;

    public ServerSocketWorker(int port) throws IOException {
        initialization(port);
    }

    public ServerSocketWorker() throws IOException {
        initialization(this.port);
    }

    private void initialization(int port) throws IOException {
        datagramChannel = DatagramChannel.open();
        selector = Selector.open();
        datagramChannel.socket().bind(new InetSocketAddress(port));
        datagramChannel.configureBlocking(false);
        datagramChannel.register(selector, SelectionKey.OP_READ);
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Request listenForRequest() throws IOException, ClassNotFoundException {
        selector.select();
        Set<SelectionKey> readyKeys = selector.selectedKeys();
        for (SelectionKey key : readyKeys) {
            if (key.isReadable()) {
                ByteBuffer packet = ByteBuffer.allocate(4096);
                socketAddress = datagramChannel.receive(packet);
                packet.flip();
                byte[] bytes = new byte[packet.remaining()];
                packet.get(bytes);
                return DeSerializer.deSerializeRequest(bytes);
            }
        }
        return null;
    }

    public void sendResponse(Response response) throws IOException {
        ByteBuffer bufferToSend = Serializer.serializeResponse(response);
        datagramChannel.send(bufferToSend, socketAddress);
    }
}
