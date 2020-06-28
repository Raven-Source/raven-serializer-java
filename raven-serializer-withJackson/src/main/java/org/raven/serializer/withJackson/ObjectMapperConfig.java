package org.raven.serializer.withJackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.SimpleDateFormat;

/**
 * ObjectMapper config
 *
 * @author yi.liang
 * date 2018/3/20 14:00:00
 * @since JDK1.8
 */
public class ObjectMapperConfig {

    /**
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return getObjectMapper(SerializerSetting.getDefault());
    }

    /**
     * @param setting
     * @return
     */
    public static ObjectMapper getObjectMapper(SerializerSetting setting) {

//        DefaultDeserializationContext deserializationContext =
//                new DefaultDeserializationContext.Impl(BeanDeserializerFactoryWarp.instance(setting));

        ObjectMapper mapper = new ObjectMapper(null, null, null);
        //mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(new ModifySerializer()));

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        if (setting.getTimeZone() != null) {
            mapper.setTimeZone(setting.getTimeZone());
        }

        if (setting.getDateFormatString() != null) {
            mapper.setDateFormat(new SimpleDateFormat(setting.getDateFormatString()));
        }

        ValueTypeModel module = new ValueTypeModel(setting);
//        module.addSerializer(ValueType.class, new ValueTypeSerializer());

        mapper.registerModules(module);
        mapper.registerModules(new JavaTimeModule());

        mapper.setAnnotationIntrospector(new AnnotationIntrospectorWarp());
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategyWarp());

        return mapper;
    }
}
