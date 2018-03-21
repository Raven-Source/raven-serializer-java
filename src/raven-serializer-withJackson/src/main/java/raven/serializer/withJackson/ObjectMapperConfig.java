package raven.serializer.withJackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.module.SimpleModule;
import raven.data.entity.ValueEnum;
import raven.serializer.withJackson.format.JsonPropertyFormatHelper;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * ObjectMapper配置类
 *
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/3/20 14:00:00
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
