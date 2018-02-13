package raven.serializer.withMsgpack;

import org.msgpack.MessageTypeException;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.unpacker.Unpacker;
import raven.data.entity.ValueEnum;
import raven.data.entity.ValueEnumHelper;

import java.io.IOException;

/**
 * 枚举模板
 *
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/2/11 18:00:00
 */
public class EnumTemplate<T extends Enum> extends AbstractTemplate<T> {

    private final Class<T> clazz;
    private final static Class<ValueEnum> valueEnumTypeClass = ValueEnum.class;

    /**
     *
     * @param clazz
     */
    public EnumTemplate(final Class<T> clazz){
        this.clazz = clazz;
    }

    /**
     *
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
        if (target instanceof ValueEnum) {
            pk.write(((ValueEnum) target).getValue());
        } else {
            pk.write(target.name());
        }
    }

    /**
     *
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
        if (valueEnumTypeClass.isAssignableFrom(clazz)) {
            return (T) ValueEnumHelper.valueOf((Class<? extends ValueEnum>) clazz, u.readInt());
        } else {
            return (T)Enum.valueOf(clazz, u.readString());
        }
    }
}
