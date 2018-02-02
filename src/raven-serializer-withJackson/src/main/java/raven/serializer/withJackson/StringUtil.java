package raven.serializer.withJackson;

import raven.data.entity.annotation.PropertyFormatType;

/**
 * String工具类
 *
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/3 14:00:00
 */
public class StringUtil {

    /**
     * 命名格式化
     *
     * @param value
     * @param propertyFormatType
     * @return
     */
    public static String namingFormat(String value, PropertyFormatType propertyFormatType) {

        char first = value.charAt(0);

        if (propertyFormatType == PropertyFormatType.PascalCase && first >= 97 && first <= 122) {

            return convertFirstChar(value, propertyFormatType);

        } else if (propertyFormatType == PropertyFormatType.CamelCase && first >= 65 && first <= 90) {

            return convertFirstChar(value, propertyFormatType);
        }

        return value;
    }

    /**
     * @param value
     * @param propertyFormatType
     * @return
     */
    private static String convertFirstChar(String value, PropertyFormatType propertyFormatType) {
        char[] _temp = value.toCharArray();
        if (propertyFormatType == PropertyFormatType.PascalCase) {
            _temp[0] = Character.toUpperCase(_temp[0]);
        } else {
            _temp[0] = Character.toLowerCase(_temp[0]);
        }
        return new String(_temp);
    }

}
