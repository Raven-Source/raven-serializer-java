package raven.serializer.withJackson.format;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import org.raven.commons.data.MemberFormatUtil;
import org.raven.commons.data.annotation.Contract;
import org.raven.commons.data.annotation.Member;

/**
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/8
 */
public class JsonPropertyFormatHelper {

    /**
     * @param method
     * @param defaultName
     * @return
     */
    public static String format(AnnotatedMethod method, String defaultName) {

        if (method == null)
            return defaultName;

        Member dataMember = method.getAnnotation(Member.class);
        if (dataMember != null) {
            return dataMember.value();
        }

        Contract contract = method.getAnnotation(Contract.class);
        if (contract == null) {
            contract = method.getDeclaringClass().getAnnotation(Contract.class);
        }
        if (contract != null) {
            return MemberFormatUtil.namingFormat(defaultName, contract.formatType());
        }
        return defaultName;

    }

    /**
     * @param field
     * @param defaultName
     * @return
     */
    public static String format(AnnotatedField field, String defaultName) {

        if (field == null)
            return defaultName;

        Member dataMember = field.getAnnotation(Member.class);
        if (dataMember != null) {
            return dataMember.value();
        }

        Contract contract = field.getAnnotation(Contract.class);
        if (contract == null) {
            contract = field.getDeclaringClass().getAnnotation(Contract.class);
        }
        if (contract != null) {
            return MemberFormatUtil.namingFormat(defaultName, contract.formatType());
        }
        return defaultName;

    }
}
