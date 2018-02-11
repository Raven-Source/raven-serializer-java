package raven.serializer.withJackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import raven.serializer.withJackson.format.JsonPropertyFormatHelper;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ObjectMapperConfig {

    public static final TimeZone defaultTimeZone = TimeZone.getTimeZone("GMT+8");

    public static ObjectMapper getObjectMapper() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setTimeZone(defaultTimeZone);

        /*mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(new ModifySerializer()));
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Enum.class, new ValueEnumTypeDeserializer());
        module.addSerializer(Enum.class, new ValueEnumTypeSerializer());

        mapper.registerModules(module);*/

        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {

            // 反序列化时调用
            @Override
            public String nameForSetterMethod(MapperConfig<?> config,
                                              AnnotatedMethod method, String defaultName) {
                return JsonPropertyFormatHelper.format(method, defaultName);
            }

            // 序列化时调用
            @Override
            public String nameForGetterMethod(MapperConfig<?> config,
                                              AnnotatedMethod method, String defaultName) {
                return JsonPropertyFormatHelper.format(method, defaultName);
            }

            @Override
            public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
                return JsonPropertyFormatHelper.format(field, defaultName);
            }
        });

        return mapper;
    }
}
