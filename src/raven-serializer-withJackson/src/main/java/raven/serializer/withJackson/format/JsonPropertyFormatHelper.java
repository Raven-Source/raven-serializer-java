package raven.serializer.withJackson.format;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import raven.data.entity.annotation.Property;
import raven.data.entity.annotation.PropertyFormat;
import raven.serializer.withJackson.StringUtil;

/**
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/8
 */
public class JsonPropertyFormatHelper {

    /**
     *
     * @param method
     * @param defaultName
     * @return
     */
    public static String format(AnnotatedMethod method, String defaultName) {

        Property dataMember = method.getAnnotation(Property.class);
        if (dataMember != null) {
            return dataMember.name();
        }

        PropertyFormat propertyFormat = method.getAnnotation(PropertyFormat.class);
        if (propertyFormat == null) {
            propertyFormat = method.getDeclaringClass().getAnnotation(PropertyFormat.class);
        }
        if (propertyFormat != null) {
            return StringUtil.namingFormat(defaultName, propertyFormat.value());
        }
        return defaultName;

    }

    /**
     *
     * @param field
     * @param defaultName
     * @return
     */
    public static String format(AnnotatedField field, String defaultName) {

        Property dataMember = field.getAnnotation(Property.class);
        if (dataMember != null) {
            return dataMember.name();
        }

        PropertyFormat propertyFormat = field.getAnnotation(PropertyFormat.class);
        if (propertyFormat == null) {
            propertyFormat = field.getDeclaringClass().getAnnotation(PropertyFormat.class);
        }
        if (propertyFormat != null) {
            return StringUtil.namingFormat(defaultName, propertyFormat.value());
        }
        return defaultName;

    }
}
