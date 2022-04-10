package emented.lab6.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public final class Serializer {

    private static final int ARRAY_SIZE = 2048;
    private static final int HUMAN_SIZE = 1024;

    private Serializer() {
    }

    public static ByteBuffer serializeRequest(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(ARRAY_SIZE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();
        ByteBuffer bufToSend = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return bufToSend;
    }

    public static ByteBuffer serializeResponse(Response response) throws IOException {
        int bufSize = ARRAY_SIZE;
        if (response.getCollectionToResponse() != null) {
            bufSize = response.getCollectionToResponse().size() * HUMAN_SIZE + ARRAY_SIZE;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bufSize);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        objectOutputStream.flush();
        ByteBuffer bufToSend = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return bufToSend;
    }

}
