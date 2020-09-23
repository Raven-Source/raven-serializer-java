package org.raven.serializer.withMsgpack;

import org.msgpack.MessageTypeException;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.unpacker.Unpacker;
import org.raven.commons.data.SerializableTypeUtils;
import org.raven.commons.data.StringType;

import java.io.IOException;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2020/9/24
 */
public class StringTypeTemplate<T extends StringType> extends AbstractTemplate<T> {

    private final Class<T> target;

    /**
     * @param target
     */
    public StringTypeTemplate(final Class<T> target) {
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

        return SerializableTypeUtils.valueOf(target, u.readString());
    }
}
