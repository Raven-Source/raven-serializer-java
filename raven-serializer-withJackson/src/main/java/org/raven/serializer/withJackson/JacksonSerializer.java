package org.raven.serializer.withJackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.raven.commons.util.Args;
import org.raven.serializer.core.BasicSerializer;
import org.raven.serializer.core.Serializer;
import org.raven.serializer.core.StringSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

/**
 *  Jackson json
 *
 * @author yi.liang
 * @since JDK1.8
 * date 2018/1/3 14:00:00
 */
public class JacksonSerializer extends BasicSerializer
        implements Serializer, StringSerializer {

    private ObjectMapper mapper;
    private SerializerSetting _setting;

//    /**
//     * 设置dataFormatString
//     *
//     * @param formatString
//     */
//    public void setDateFormatString(String formatString) {
//        this.dataFormatString = formatString;
//        mapper.setDateFormat(new SimpleDateFormat(dataFormatString));
//    }
//
//    /**
//     * 获取dataFormatString
//     *
//     * @return
//     */
//    public String getDataFormatString() {
//        return dataFormatString;
//    }


    public JacksonSerializer() {

        _setting = SerializerSetting.getDefault();

        mapper = ObjectMapperConfig.getObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat(_setting.getDateFormatString()));
        mapper.setTimeZone(_setting.getTimeZone());
    }

    /**
     * 构造函数
     *
     * @param setting
     */
    public JacksonSerializer(SerializerSetting setting) {

        _setting = setting != null ? setting : SerializerSetting.getDefault();

        mapper = ObjectMapperConfig.getObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat(_setting.getDateFormatString()));
        mapper.setTimeZone(_setting.getTimeZone());
    }

    /**
     * @param obj
     * @return
     * @throws IOException
     */
    @Override
    public String serializeToString(Object obj) throws IOException {

        Args.notNull(obj, "obj");

        if (obj instanceof String) return (String) obj;
        return mapper.writeValueAsString(obj);
    }

    /**
     * @param obj
     * @return
     * @throws IOException
     */
    @Override
    public byte[] serialize(Object obj) throws IOException {

        Args.notNull(obj, "obj");

        byte[] res = trySerialize(obj);
        if (res != null) {
            return res;
        } else {
            return mapper.writeValueAsBytes(obj);
        }
    }

    /**
     * @param obj
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void serialize(Object obj, OutputStream outputStream) throws IOException {

        Args.notNull(obj, "obj");

        byte[] res = trySerialize(obj);
        if (res != null) {
            outputStream.write(res);
        } else {
            mapper.writeValue(outputStream, obj);
        }
    }

    /**
     * @param clazz
     * @param data
     * @param <T>
     * @return
     * @throws IOException
     */
    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data) throws IOException {

        Args.notNull(data, "data");

        T res = tryDeserialize(clazz, data);
        if (res != null) {
            return res;
        } else {
            return mapper.readValue(data, clazz);
        }

    }

    /**
     * @param clazz
     * @param data
     * @param index
     * @param count
     * @param <T>
     * @return
     * @throws IOException
     */
    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data, int index, int count) throws IOException {
        Args.notNull(data, "data");

        T res = tryDeserialize(clazz, data, index, count);
        if (res != null) {
            return res;
        } else {
            return mapper.readValue(data, index, count, clazz);
        }
    }

    /**
     * @param clazz
     * @param inputStream
     * @param <T>
     * @return
     * @throws IOException
     */
    @Override
    public <T> T deserialize(Class<T> clazz, InputStream inputStream) throws IOException {
        Args.notNull(inputStream, "inputStream");

        T res = tryDeserialize(clazz, inputStream);
        if (res != null) {
            return res;
        } else {
            return mapper.readValue(inputStream, clazz);
        }
    }

}
