package raven.serializer.withJackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import raven.serializer.BasicDataSerializer;
import raven.serializer.DataSerializer;
import raven.serializer.StringDataSerializer;
import raven.serializer.withJackson.annotations.JsonPropertyFormat;
import raven.serializer.withJackson.annotations.JsonPropertyFormatType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

public class JacksonSerializer extends BasicDataSerializer
        implements DataSerializer, StringDataSerializer {

    private ObjectMapper mapper;

    /**
     *
     */
    public JacksonSerializer() {

        mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {

            // 反序列化时调用
            @Override
            public String nameForSetterMethod(MapperConfig<?> config,
                                              AnnotatedMethod method, String defaultName) {

                JsonPropertyFormat jsonPropertyFormat = method.getAnnotation(JsonPropertyFormat.class);
                if (jsonPropertyFormat == null) {
                    jsonPropertyFormat = method.getDeclaringClass().getAnnotation(JsonPropertyFormat.class);
                }
                if (jsonPropertyFormat != null) {
                    return StringUtil.namingFormat(defaultName, jsonPropertyFormat.value());

                }
                return defaultName;

            }
            // 序列化时调用
            @Override
            public String nameForGetterMethod(MapperConfig<?> config,
                                              AnnotatedMethod method, String defaultName) {

                JsonPropertyFormat jsonPropertyFormat = method.getAnnotation(JsonPropertyFormat.class);
                if (jsonPropertyFormat == null) {
                    jsonPropertyFormat = method.getDeclaringClass().getAnnotation(JsonPropertyFormat.class);
                }
                if (jsonPropertyFormat != null) {
                    return StringUtil.namingFormat(defaultName, jsonPropertyFormat.value());

                }
                return defaultName;
            }

            @Override
            public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {

                JsonPropertyFormat jsonPropertyFormat = field.getAnnotation(JsonPropertyFormat.class);
                if (jsonPropertyFormat == null) {
                    jsonPropertyFormat = field.getDeclaringClass().getAnnotation(JsonPropertyFormat.class);
                }
                if (jsonPropertyFormat != null) {
                    return StringUtil.namingFormat(defaultName, jsonPropertyFormat.value());
                }
                return defaultName;
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
        return mapper.writeValueAsString(obj);
    }

    /**
     * @param obj
     * @return
     * @throws IOException
     */
    @Override
    public byte[] serialize(Object obj) throws IOException {
        return mapper.writeValueAsBytes(obj);
    }

    /**
     * @param obj
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void serialize(Object obj, OutputStream outputStream) throws IOException {
        mapper.writeValue(outputStream, obj);
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
        return mapper.readValue(data, clazz);
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
        return mapper.readValue(data, index, count, clazz);
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
        return mapper.readValue(inputStream, clazz);
    }

}
