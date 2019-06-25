package org.raven.serializer.withJackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.raven.commons.data.ValueEnum;

import java.util.TimeZone;

/**
 * ObjectMapper config
 *
 * @author yi.liang
 * date 2018/3/20 14:00:00
 * @since JDK1.8
 */
public class ObjectMapperConfig {

    public static final TimeZone defaultTimeZone = TimeZone.getTimeZone("GMT+8");

    public static ObjectMapper getObjectMapper() {

        DefaultDeserializationContext deserializationContext = new DefaultDeserializationContext.Impl(CustomBeanDeserializerFactory.instance);

        ObjectMapper mapper = new ObjectMapper(null, null, deserializationContext);
        //mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(new ModifySerializer()));

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setTimeZone(defaultTimeZone);

        SimpleModule module = new SimpleModule();
        module.addSerializer(ValueEnum.class, new ValueEnumSerializer());

        mapper.registerModules(module);

        mapper.setAnnotationIntrospector(new CustomAnnotationIntrospector());
        mapper.setPropertyNamingStrategy(new CustomPropertyNamingStrategy());

        return mapper;
    }
}
