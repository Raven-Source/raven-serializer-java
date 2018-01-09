package raven.serializer.withJackson.format;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import raven.serializer.serialization.DataMember;
import raven.serializer.withJackson.StringUtil;

public class JsonPropertyFormatHelper {

    /**
     *
     * @param method
     * @param defaultName
     * @return
     */
    public static String format(AnnotatedMethod method, String defaultName) {

        DataMember dataMember = method.getAnnotation(DataMember.class);
        if (dataMember != null) {
            return dataMember.name();
        }

        JsonPropertyFormat jsonPropertyFormat = method.getAnnotation(JsonPropertyFormat.class);
        if (jsonPropertyFormat == null) {
            jsonPropertyFormat = method.getDeclaringClass().getAnnotation(JsonPropertyFormat.class);
        }
        if (jsonPropertyFormat != null) {
            return StringUtil.namingFormat(defaultName, jsonPropertyFormat.value());
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

        DataMember dataMember = field.getAnnotation(DataMember.class);
        if (dataMember != null) {
            return dataMember.name();
        }

        JsonPropertyFormat jsonPropertyFormat = field.getAnnotation(JsonPropertyFormat.class);
        if (jsonPropertyFormat == null) {
            jsonPropertyFormat = field.getDeclaringClass().getAnnotation(JsonPropertyFormat.class);
        }
        if (jsonPropertyFormat != null) {
            return StringUtil.namingFormat(defaultName, jsonPropertyFormat.value());
        }
        return defaultName;

    }
}
