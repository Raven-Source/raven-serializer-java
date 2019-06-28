package org.raven.serializer.withJackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import org.raven.commons.data.ValueEnum;

import java.util.Date;

/**
 * custom DeserializerFactory, rewrite createEnumDeserializer
 *
 * @author yi.liang
 * @since JDK1.8
 * date 2018/3/20 14:00:00
 */
public class CustomBeanDeserializerFactory extends BeanDeserializerFactory {

    public final static CustomBeanDeserializerFactory instance(SerializerSetting setting) {
        return new CustomBeanDeserializerFactory(
                new DeserializerFactoryConfig()
                , setting);
    }

    private final static Class<ValueEnum> valueEnumTypeClass = ValueEnum.class;

    private SerializerSetting setting;
    private CustomDateDeserializer customDateDeserializer;

    /**
     * @param config
     */
    public CustomBeanDeserializerFactory(DeserializerFactoryConfig config, SerializerSetting setting) {
        super(config);

        this.setting = setting;
        this.customDateDeserializer = new CustomDateDeserializer(setting.getDeserializeDateFormatString());
    }

    @Override
    public DeserializerFactory withConfig(DeserializerFactoryConfig config) {
        if (_factoryConfig == config) {
            return this;
        }
        return new CustomBeanDeserializerFactory(config, setting);
    }

    @Override
    public JsonDeserializer createBeanDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {

        final Class<?> clazz = type.getRawClass();
        if (clazz.equals(Date.class)) {
            return customDateDeserializer;
        }

        return super.createBeanDeserializer(ctxt, type, beanDesc);
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
