package raven.serializer.withJackson;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import raven.serializer.withJackson.format.JsonPropertyFormatHelper;

/**
 * @author yi.liang
 * @date 2018.9.25
 * @since JDK1.8
 */
public class CustomPropertyNamingStrategy extends PropertyNamingStrategy {

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
}
