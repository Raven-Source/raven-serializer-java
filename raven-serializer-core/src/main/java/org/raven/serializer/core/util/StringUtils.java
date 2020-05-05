package org.raven.serializer.core.util;

/**
 * @author yi.liang
 * date 2018.9.25
 * @since JDK1.8
 */
public final class StringUtils {

    /**
     * Returns true if the parameter is null or of zero length
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(final CharSequence s) {
        if (s == null) {
            return true;
        }
        return s.length() == 0;
    }

    /**
     * Returns true if the parameter is null or contains only whitespace
     *
     * @param s
     * @return
     */
    public static boolean isBlank(final CharSequence s) {
        if (s == null) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @since 4.4
     */
    public static boolean containsBlanks(final CharSequence s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (Character.isWhitespace(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }


    /**
     * @param str
     * @param searchChar
     * @return
     */
    public static int indexOf(String str, char searchChar) {
        return isEmpty(str) ? -1 : str.indexOf(searchChar);
    }

    /**
     * @param str
     * @param searchChar
     * @param startPos
     * @return
     */
    public static int indexOf(String str, char searchChar, int startPos) {
        return isEmpty(str) ? -1 : str.indexOf(searchChar, startPos);
    }

    /**
     * @param str
     * @param searchStr
     * @return
     */
    public static int indexOf(String str, String searchStr) {
        return str != null && searchStr != null ? str.indexOf(searchStr) : -1;
    }
}
