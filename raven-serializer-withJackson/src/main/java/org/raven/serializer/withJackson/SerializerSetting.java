package org.raven.serializer.withJackson;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.TimeZone;

/**
 *
 */
@Getter
@Setter
public class SerializerSetting {

    /**
     *
     */
    private String dateFormatString;

    /**
     *
     */
    private String[] deserializeDateFormatString;

    private TimeZone timeZone;

    /**
     * @return
     */
    public static SerializerSetting getDefault() {

        SerializerSetting setting = new SerializerSetting();
        setting.setDateFormatString("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        setting.setDeserializeDateFormatString(new String[]{
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "yyyy-MM-dd'T'HH:mm:ssZ",
                "yyyy-MM-dd HH:mm:ss.SSS",
                "yyyy-MM-dd HH:mm:ss"
        });
        setting.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        return setting;
    }

    @Override
    public String toString() {
        return "SerializerSetting{" +
                "dateFormatString='" + dateFormatString + '\'' +
                ", deserializeDateFormatString=" + Arrays.toString(deserializeDateFormatString) +
                ", timeZone=" + timeZone +
                '}';
    }
}
