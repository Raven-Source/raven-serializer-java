package raven.serializer.withJackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import raven.serializer.ValueEnumType;

import java.io.IOException;

public class ValueEnumTypeDeserializer<T extends ValueEnumType> extends JsonDeserializer<T> {

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        int value = p.getIntValue();
        //return (T) ValueEnumTypes.valueOf(_valueClass, value);
        return null;
    }

}
