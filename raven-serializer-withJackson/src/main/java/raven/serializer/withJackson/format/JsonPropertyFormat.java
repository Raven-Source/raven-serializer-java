package raven.serializer.withJackson.format;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
/**
 * 可以指定属性格式化类型，支持CamelCase、PascalCase
 */
public @interface JsonPropertyFormat {
    /**
     *
     * @return
     */
    JsonPropertyFormatType value() default JsonPropertyFormatType.CamelCase;
}