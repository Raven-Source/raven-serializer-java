package raven.serializer.withJackson;

import raven.serializer.withJackson.annotations.JsonPropertyFormatType;

/**
 *
 */
public class StringUtil {

    /**
     * 命名格式化
     *
     * @param value
     * @param jsonPropertyFormat
     * @return
     */
    public static String namingFormat(String value, JsonPropertyFormatType jsonPropertyFormat) {

        char first = value.charAt(0);

        if (jsonPropertyFormat == JsonPropertyFormatType.PascalCase && first >= 97 && first <= 122) {

            return convertFirstChar(value, jsonPropertyFormat);

        } else if (jsonPropertyFormat == JsonPropertyFormatType.CamelCase && first >= 65 && first <= 90) {

            return convertFirstChar(value, jsonPropertyFormat);
        }

        return value;
    }

    /**
     * @param value
     * @param jsonPropertyFormat
     * @return
     */
    private static String convertFirstChar(String value, JsonPropertyFormatType jsonPropertyFormat) {
        char[] _temp = value.toCharArray();
        if (jsonPropertyFormat == JsonPropertyFormatType.PascalCase) {
            _temp[0] = Character.toUpperCase(_temp[0]);
        } else {
            _temp[0] = Character.toLowerCase(_temp[0]);
        }
        return new String(_temp);
    }

}
