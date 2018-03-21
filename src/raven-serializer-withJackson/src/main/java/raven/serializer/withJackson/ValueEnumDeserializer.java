package raven.serializer.withJackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import raven.data.entity.ValueEnum;
import raven.data.entity.ValueEnumHelper;

import java.io.IOException;

/**
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/9 23:00:00
 */
public class ValueEnumDeserializer<T extends ValueEnum> extends JsonDeserializer<T> {

    private final Class<T> clazz;

    /**
     * @param clazz
     */
    public ValueEnumDeserializer(final Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * @return
     */
    @Override
    public boolean isCachable() {
        return true;
    }

    /**
     * @param p
     * @param ctxt
     * @return
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        int value = p.getValueAsInt();
        return ValueEnumHelper.valueOf(clazz, value);
    }

}
