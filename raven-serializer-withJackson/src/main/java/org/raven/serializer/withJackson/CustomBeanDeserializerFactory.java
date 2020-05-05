package org.raven.serializer.withJackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import org.raven.commons.data.ValueType;

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

    private SerializerSetting setting;
    private CustomDateDeserializer dateDeserializer;

    /**
     * @param config
     */
    public CustomBeanDeserializerFactory(DeserializerFactoryConfig config, SerializerSetting setting) {
        super(config);

        this.setting = setting;
        this.dateDeserializer = new CustomDateDeserializer(setting.getDeserializeDateFormatString());
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

        final Class<?> target = type.getRawClass();
        if (target.equals(Date.class)) {
            return dateDeserializer;
        }
        if (ValueType.class.isAssignableFrom(target)) {
            return createValueTypeDeserializer((Class<? extends ValueType>) target);
        }

        return super.createBeanDeserializer(ctxt, type, beanDesc);
    }

    @Override
    public JsonDeserializer<?> createEnumDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {

        final Class<?> target = type.getRawClass();
        if (ValueType.class.isAssignableFrom(target)) {
            return createValueTypeDeserializer((Class<? extends ValueType>) target);
        } else
            return super.createEnumDeserializer(ctxt, type, beanDesc);
    }

    private JsonDeserializer<?> createValueTypeDeserializer(Class<? extends ValueType> target) {

        JsonDeserializer<?> deserializer = new ValueTypeDeserializer(target);
        return deserializer;
    }
}
