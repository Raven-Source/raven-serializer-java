package org.raven.serializer.withJackson;

import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.TimeZone;

import static org.raven.commons.constant.DateFormatStringConstant.*;

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

    /**
     *
     */
    private TimeZone timeZone;

    /**
     * @return SerializerSetting
     */
    public static SerializerSetting getDefault() {

        SerializerSetting setting = new SerializerSetting();
        setting.setDateFormatString(ISO_OFFSET_DATE_TIME);
        setting.setDeserializeDateFormatString(new String[]{
            ISO_OFFSET_DATE_TIME,   //ISO_OFFSET
            ISO_INSTANT,
            ISO_INSTANT_SECOND,
            DATE_TIME,
            DATE_TIME_SECOND
        });

        setting.setTimeZone(TimeZone.getTimeZone("UTC"));

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
