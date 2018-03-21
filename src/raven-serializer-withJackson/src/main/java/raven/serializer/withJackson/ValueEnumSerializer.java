package raven.serializer.withJackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import raven.data.entity.ValueEnum;

import java.io.IOException;

/**
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/9 23:00:00
 */
public class ValueEnumSerializer extends JsonSerializer<ValueEnum> {



    /**
     *
     * @param value
     * @param generator
     * @param provider
     * @throws IOException
     */
    @Override
    public void serialize(ValueEnum value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeNumber(value.getValue());
    }

}