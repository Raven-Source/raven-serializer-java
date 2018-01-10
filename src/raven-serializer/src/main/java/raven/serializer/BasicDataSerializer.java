package raven.serializer;

import raven.serializer.util.Args;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/3 14:00:00
 */
public abstract class BasicDataSerializer {

    protected static final Charset charset = Charset.forName("UTF-8");

    protected static final Class stringClazz = String.class;

    protected static final Class byteArrayClazz = byte[].class;

    public byte[] serializeString(String obj) {

        Args.notNull(obj, "obj");
        return obj.getBytes(charset);
    }

    /**
     * @param obj
     * @return
     * @throws NullPointerException
     */
    public byte[] trySerialize(final Object obj)
            throws NullPointerException {

        Args.notNull(obj, "obj");

        if (obj instanceof byte[]) {
            return (byte[]) obj;
        } else if (obj instanceof String) {
            return serializeString((String) obj);
        }
        return null;
    }

    /**
     * @param clazz
     * @param data
     * @return
     * @throws NullPointerException
     */
    public <T> T tryDeserialize(final Class<T> clazz, final byte[] data)
            throws NullPointerException {
        Args.notNull(data, "data");

        return this.tryDeserialize(clazz, data, 0, data.length);
    }

    /**
     * @param clazz
     * @param data
     * @param index
     * @param count
     * @return
     * @throws NullPointerException
     */
    public <T> T tryDeserialize(final Class<T> clazz, final byte[] data, final int index, final int count)
            throws NullPointerException {

        Args.notNull(data, "data");

        if (clazz == byteArrayClazz) {
            return (T) data;
        } else if (clazz == stringClazz) {
            return (T) new String(data, index, count, charset);
        }
        return null;
    }

    /**
     * @param clazz
     * @param inputStream
     * @param <T>
     * @return
     * @throws NullPointerException
     * @throws IOException
     */
    public <T> T tryDeserialize(final Class<T> clazz, final InputStream inputStream)
            throws NullPointerException, IOException {

        Args.notNull(inputStream, "inputStream");

        if (clazz == byteArrayClazz || clazz == stringClazz) {
            int count = inputStream.available();
            byte[] data = new byte[count];
            inputStream.read(data);

            if (clazz == byteArrayClazz) {
                return (T) data;
            } else if (clazz == stringClazz) {
                return (T) new String(data, charset);
            }
        }
        return null;
    }

}
