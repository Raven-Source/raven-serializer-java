package raven.serializer.withJackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import raven.data.entity.ValueEnum;

import java.io.IOException;

/**
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/9 23:00:00
 */
@Deprecated
public class ValueEnumTypeDeserializer<T extends ValueEnum> extends JsonDeserializer<T> {

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        int value = p.getIntValue();
        Class<?> clazz = ctxt.getActiveView();
        //return (T) ValueEnumHelper.valueOf(_valueClass, value);
        return null;
    }

}
