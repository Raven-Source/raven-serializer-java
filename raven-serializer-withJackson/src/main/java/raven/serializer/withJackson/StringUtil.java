package raven.serializer.withJackson;

import org.raven.commons.data.MemberFormatType;

/**
 * String工具类
 *
 * @author yi.liang
 * @since JDK1.8
 * @date 2018/1/3 14:00:00
 */
public class StringUtil {

    /**
     * 命名格式化
     *
     * @param value
     * @param propertyFormatType
     * @return
     */
    public static String namingFormat(String value, MemberFormatType propertyFormatType) {

        char first = value.charAt(0);

        if (propertyFormatType == MemberFormatType.PascalCase && first >= 97 && first <= 122) {

            return convertFirstChar(value, propertyFormatType);

        } else if (propertyFormatType == MemberFormatType.CamelCase && first >= 65 && first <= 90) {

            return convertFirstChar(value, propertyFormatType);
        }

        return value;
    }

    /**
     * @param value
     * @param propertyFormatType
     * @return
     */
    private static String convertFirstChar(String value, MemberFormatType propertyFormatType) {
        char[] _temp = value.toCharArray();
        if (propertyFormatType == MemberFormatType.PascalCase) {
            _temp[0] = Character.toUpperCase(_temp[0]);
        } else {
            _temp[0] = Character.toLowerCase(_temp[0]);
        }
        return new String(_temp);
    }

}
