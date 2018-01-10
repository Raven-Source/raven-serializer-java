package raven.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/3 14:00:00
 */
public interface DataSerializer {

    byte[] serialize(Object obj) throws IOException;

    void serialize(Object obj, OutputStream outputStream) throws IOException;

    <T> T deserialize(Class<T> clazz, byte[] data) throws IOException;

    //Object deserialize(Class clazz, byte[] data) throws IOException;

    <T> T deserialize(Class<T> clazz, byte[] data, int index, int count) throws IOException;

    //Object deserialize(Class clazz, byte[] data, int index, int count) throws IOException;

    <T> T deserialize(Class<T> clazz, InputStream inputStream) throws IOException;

    //Object deserialize(Class clazz, InputStream inputStream) throws IOException;
}
