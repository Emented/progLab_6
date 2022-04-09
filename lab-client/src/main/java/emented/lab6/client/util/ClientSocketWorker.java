package emented.lab6.client.util;

import emented.lab6.common.util.DeSerializer;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.Serializer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class ClientSocketWorker {
    private final int defaultPort = 228;
    private final int timeToResponse = 4000;
    private final int arraySize = 4096;
    private final DatagramSocket datagramSocket;
    private int port;
    private String address = "localhost";
    private InetAddress serverAddress;

    public ClientSocketWorker() throws UnknownHostException, SocketException {
        port = defaultPort;
        datagramSocket = new DatagramSocket();
        serverAddress = InetAddress.getByName(address);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws UnknownHostException {
        this.address = address;
        serverAddress = InetAddress.getByName(address);
    }

    public void sendRequest(Request request) throws IOException {
        ByteBuffer byteBuffer = Serializer.serializeRequest(request);
        byte[] bufferToSend = byteBuffer.array();
        DatagramPacket datagramPacket = new DatagramPacket(bufferToSend, bufferToSend.length, serverAddress, port);
        datagramSocket.send(datagramPacket);
    }

    public Response receiveResponse() throws ClassNotFoundException, IOException {
        byte[] byteBuf = new byte[arraySize];
        DatagramPacket dpFromServer = new DatagramPacket(byteBuf, byteBuf.length);
        datagramSocket.setSoTimeout(timeToResponse);
        datagramSocket.receive(dpFromServer);
        byte[] bytesFromServer = dpFromServer.getData();
        return DeSerializer.deSerializeResponse(bytesFromServer);
    }
}
