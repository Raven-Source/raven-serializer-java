package raven.serializer.withMsgpack;

import org.msgpack.template.Template;
import org.msgpack.template.TemplateRegistry;
import raven.data.entity.ValueEnum;

import java.lang.reflect.Type;
import java.util.HashSet;

/**
 * 模板
 *
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/2/11 18:00:00
 */
public class CustomTemplateRegistry extends TemplateRegistry {

    private HashSet<Type> typeCache;

    /**
     *
     */
    public CustomTemplateRegistry() {
        super(null);
        typeCache = new HashSet<>();
    }

    /**
     * @param targetType
     * @return
     */
    @Override
    public synchronized Template lookup(Type targetType) {

        if (!typeCache.contains(targetType) && Enum.class.isAssignableFrom((Class<?>) targetType)) {
            super.register(targetType, new EnumTemplate((Class<?>) targetType));
            typeCache.add(targetType);
        }

        return super.lookup(targetType);
    }
}
