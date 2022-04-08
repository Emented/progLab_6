package emented.lab6.client.util;

import emented.lab6.common.util.DeSerializer;
import emented.lab6.common.util.Request;
import emented.lab6.common.util.Response;
import emented.lab6.common.util.Serializer;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class ClientSocketWorker {
    private int port = 228;
    private String address = "localhost";
    private final int TIME_TO_RESPONSE = 4000;
    private final DatagramSocket datagramSocket;
    private InetAddress serverAddress;

    public ClientSocketWorker() throws UnknownHostException, SocketException {
        datagramSocket = new DatagramSocket();
        serverAddress = InetAddress.getByName(address);
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return address;
    }

    public void setPort(int port) {
        this.port = port;
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
        byte[] byteBuf = new byte[4096];
        DatagramPacket dpFromServer = new DatagramPacket(byteBuf, byteBuf.length);
        datagramSocket.setSoTimeout(TIME_TO_RESPONSE);
        datagramSocket.receive(dpFromServer);
        byte[] bytesFromServer = dpFromServer.getData();
        return DeSerializer.deSerializeResponse(bytesFromServer);
    }
}
