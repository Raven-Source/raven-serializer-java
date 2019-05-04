package raven.serializer.withMsgpack;

import org.msgpack.template.Template;
import org.msgpack.template.TemplateRegistry;

import java.lang.reflect.*;
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

        if (targetType instanceof ParameterizedType
                || targetType instanceof GenericArrayType
                || targetType instanceof WildcardType
                || targetType instanceof TypeVariable
                || typeCache.contains(targetType)
                || !Enum.class.isAssignableFrom((Class<?>) targetType)) {
        } else {
            super.register(targetType, new EnumTemplate((Class<?>) targetType));
            typeCache.add(targetType);
        }

        return super.lookup(targetType);
    }
}
