package raven.serializer.serialization;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataMember {

    /**
     * 数据成员名称
     *
     * @return
     */
    String name() default "";

    /**
     * 序列化和反序列化的顺序。
     *
     * @return
     */
    int Order() default -1;

    /**
     * 该值指示序列化引擎该成员在读取或反序列化时必须存在
     *
     * @return
     */
    boolean IsRequired() default true;
}
