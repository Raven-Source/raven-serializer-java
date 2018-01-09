package raven.serializer.withJackson;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import raven.serializer.BasicDataSerializer;
import raven.serializer.DataSerializer;
import raven.serializer.StringDataSerializer;
import raven.serializer.util.Args;
import raven.serializer.withJackson.format.JsonPropertyFormatHelper;

import java.io.*;
import java.text.SimpleDateFormat;

public class JacksonSerializer extends BasicDataSerializer
        implements DataSerializer, StringDataSerializer {

    private String dataFormatString;
    private ObjectMapper mapper;

    /**
     * 设置dataFormatString
     *
     * @param formatString
     */
    public void setDateFormatString(String formatString) {
        this.dataFormatString = formatString;
        mapper.setDateFormat(new SimpleDateFormat(dataFormatString));
    }

    /**
     * 获取dataFormatString
     *
     * @return
     */
    public String getDataFormatString() {
        return dataFormatString;
    }

    /**
     *
     */
    public JacksonSerializer() {


        dataFormatString = "yyyy-MM-dd HH:mm:ss";
        mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat(dataFormatString));
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        /*mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(new ModifySerializer()));
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Enum.class, new ValueEnumTypeDeserializer());
        module.addSerializer(Enum.class, new ValueEnumTypeSerializer());

        mapper.registerModules(module);*/

        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {

            // 反序列化时调用
            @Override
            public String nameForSetterMethod(MapperConfig<?> config,
                                              AnnotatedMethod method, String defaultName) {
                return JsonPropertyFormatHelper.format(method, defaultName);
            }

            // 序列化时调用
            @Override
            public String nameForGetterMethod(MapperConfig<?> config,
                                              AnnotatedMethod method, String defaultName) {
                return JsonPropertyFormatHelper.format(method, defaultName);
            }

            @Override
            public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
                return JsonPropertyFormatHelper.format(field, defaultName);
            }
        });


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
