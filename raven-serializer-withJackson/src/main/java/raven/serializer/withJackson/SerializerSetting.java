package raven.serializer.withJackson;

import lombok.Getter;
import lombok.Setter;

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

    private TimeZone timeZone;

    /**
     * @return
     */
    public static SerializerSetting getDefault() {

        SerializerSetting setting = new SerializerSetting();
        setting.setDateFormatString("yyyy-MM-dd HH:mm:ss");
        setting.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        return setting;
    }

    @Override
    public String toString() {
        return "SerializerSetting{" +
                "dateFormatString='" + dateFormatString + '\'' +
                ", timeZone=" + timeZone.toZoneId() +
                '}';
    }
}
