package raven.serializer.withJackson.format;

import java.lang.annotation.*;

/**
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/8
 */
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