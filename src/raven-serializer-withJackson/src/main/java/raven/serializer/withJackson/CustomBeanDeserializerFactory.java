package raven.serializer.withJackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import raven.data.entity.ValueEnum;

/**
 * 自定义DeserializerFactory，重写createEnumDeserializer
 *
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/3/20 14:00:00
 */
public class CustomBeanDeserializerFactory extends BeanDeserializerFactory {

    public final static CustomBeanDeserializerFactory instance = new CustomBeanDeserializerFactory(
            new DeserializerFactoryConfig());

    private final static Class<ValueEnum> valueEnumTypeClass = ValueEnum.class;

    /**
     * @param config
     */
    public CustomBeanDeserializerFactory(DeserializerFactoryConfig config) {
        super(config);
    }

    @Override
    public JsonDeserializer<?> createEnumDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {

        final Class<?> enumClass = type.getRawClass();
        if (valueEnumTypeClass.isAssignableFrom(enumClass)) {
            return new ValueEnumDeserializer(enumClass);
        } else
            return super.createEnumDeserializer(ctxt, type, beanDesc);
    }
}
