package org.raven.serializer.withMsgpack;

import org.msgpack.MessageTypeException;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.unpacker.Unpacker;
import org.raven.commons.data.ValueType;
import org.raven.commons.data.ValueTypeUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2018/2/11 18:00:00
 */
public class ValueTypeTemplate<T extends ValueType> extends AbstractTemplate<T> {

    private final Class<T> target;

    /**
     * @param target
     */
    public ValueTypeTemplate(final Class<T> target) {
        this.target = target;
    }

    /**
     * @param pk
     * @param target
     * @param required
     * @throws IOException
     */
    @Override
    public void write(Packer pk, T target, boolean required) throws IOException {

        if (target == null) {
            if (required) {
                throw new MessageTypeException("Attempted to write null");
            }
            pk.writeNil();
            return;
        }
        pk.write((target).getValue());
    }

    /**
     * @param u
     * @param to
     * @param required
     * @return
     * @throws IOException
     */
    @Override
    public T read(Unpacker u, T to, boolean required) throws IOException {
        if (!required && u.trySkipNil()) {
            return null;
        }

        Class genericType = ValueTypeUtils.getGenericType(target);
        if (genericType.equals(Integer.class)) {
            return ValueTypeUtils.valueOf(target, u.readInt());
        }
        if (genericType.equals(Long.class)) {
            return ValueTypeUtils.valueOf(target, u.readLong());
        }
        if (genericType.equals(BigInteger.class)) {
            return ValueTypeUtils.valueOf(target, u.readBigInteger());
        }
        if (genericType.equals(Double.class)) {
            return ValueTypeUtils.valueOf(target, u.readDouble());
        }
        if (genericType.equals(Float.class)) {
            return ValueTypeUtils.valueOf(target, u.readFloat());
        }
        if (genericType.equals(BigDecimal.class)) {
            return ValueTypeUtils.valueOf(target, u.readBigInteger());
        }

        return null;
    }
}
