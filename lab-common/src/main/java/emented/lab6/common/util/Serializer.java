package emented.lab6.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public final class Serializer {

    public static ByteBuffer serializeRequest(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();
        ByteBuffer bufToSend = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return bufToSend;
    }

    public static ByteBuffer serializeResponse(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        objectOutputStream.flush();
        ByteBuffer bufToSend = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return bufToSend;
    }

}
