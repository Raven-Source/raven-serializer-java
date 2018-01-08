package raven.serializer.withMsgpack;

import org.msgpack.MessagePack;
import raven.serializer.BasicDataSerializer;
import raven.serializer.DataSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MsgpackSerializer extends BasicDataSerializer
        implements DataSerializer {

    MessagePack msgpack = new MessagePack();

    public byte[] serialize(Object obj) throws IOException {
        return msgpack.write(obj);
    }


    public void serialize(Object obj, OutputStream outputStream) throws IOException {
        msgpack.write(outputStream, obj);
    }


    public <T> T deserialize(Class<T> clazz, byte[] data) throws IOException {
        return msgpack.read(data, clazz);
    }

    public <T> T deserialize(Class<T> clazz, byte[] data, int index, int count) throws IOException {
        return msgpack.read(data, index, count, clazz);
    }

    public <T> T deserialize(Class<T> clazz, InputStream inputStream) throws IOException {
        return msgpack.read(inputStream, clazz);
    }

}
