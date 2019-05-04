package raven.serializer.withMsgpack;

import org.msgpack.MessagePack;
import org.raven.serializer.core.BasicSerializer;
import org.raven.serializer.core.Serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * MessagePack序列化类
 *
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/3 14:00:00
 */
public class MsgpackSerializer extends BasicSerializer
        implements Serializer {

    private MessagePack msgpack;

    /**
     * 构造函数
     */
    public MsgpackSerializer() {

        msgpack = MessagePackFactory.create();
    }

    /**
     * @param obj
     * @return
     * @throws IOException
     */
    public byte[] serialize(Object obj) throws IOException {
        return msgpack.write(obj);
    }

    /**
     * @param obj
     * @param outputStream
     * @throws IOException
     */
    public void serialize(Object obj, OutputStream outputStream) throws IOException {
        msgpack.write(outputStream, obj);
    }

    /**
     * @param clazz
     * @param data
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T deserialize(Class<T> clazz, byte[] data) throws IOException {
        return msgpack.read(data, clazz);
    }

    /**
     * @param clazz
     * @param data
     * @param index
     * @param count
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T deserialize(Class<T> clazz, byte[] data, int index, int count) throws IOException {
        return msgpack.read(data, index, count, clazz);
    }

    /**
     * @param clazz
     * @param inputStream
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T deserialize(Class<T> clazz, InputStream inputStream) throws IOException {
        return msgpack.read(inputStream, clazz);
    }

}
