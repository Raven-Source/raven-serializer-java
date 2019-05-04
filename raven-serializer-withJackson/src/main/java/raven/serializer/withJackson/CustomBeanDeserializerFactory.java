package raven.serializer.withJackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import org.raven.commons.data.ValueEnum;

/**
 * 自定义DeserializerFactory，重写createEnumDeserializer
 *
 * @author yi.liang
 * @since JDK1.8
 * @date 2018/3/20 14:00:00
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
    public DeserializerFactory withConfig(DeserializerFactoryConfig config)
    {
        if (_factoryConfig == config) {
            return this;
        }
        return new CustomBeanDeserializerFactory(config);
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
