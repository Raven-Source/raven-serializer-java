package raven.serializer;

import java.nio.charset.Charset;

/**
 *
 */
public abstract class BasicDataSerializer {

    protected final Charset charset = Charset.forName("UTF-8");

    protected final Class stringClazz = String.class;

    protected final Class byteArrayClazz = byte[].class;

    public byte[] serializeString(String obj) {
        return obj.getBytes(charset);
    }

    /**
     *
     * @param obj
     * @param res
     * @return
     * @throws NullPointerException
     */
    public Boolean trySerialize(final Object obj, final ValueBox<byte[]> res)
            throws NullPointerException {

        if (res == null)
            throw new NullPointerException();

        if (obj instanceof byte[]) {
            res.setValue((byte[]) obj);
            return true;
        } else if (obj instanceof String) {
            res.setValue(serializeString((String) obj));
            return true;
        }
        return false;
    }

    /**
     *
     * @param clazz
     * @param data
     * @param res
     * @return
     * @throws NullPointerException
     */
    public Boolean tryDeserialize(final Class clazz, final byte[] data, final ValueBox<Object> res)
            throws NullPointerException {
        return this.tryDeserialize(clazz, data, 0, data.length, res);
    }

    /**
     *
     * @param clazz
     * @param data
     * @param index
     * @param count
     * @param res
     * @return
     * @throws NullPointerException
     */
    public Boolean tryDeserialize(final Class clazz, final byte[] data, final int index, final int count, final ValueBox<Object> res)
            throws NullPointerException {

        if (res == null)
            throw new NullPointerException();
        if (clazz == byteArrayClazz) {
            res.setValue(res);
            return true;
        } else if (clazz == stringClazz) {
            res.setValue(new String(data, index, count, charset));
            return true;
        }
        return false;
    }

}
