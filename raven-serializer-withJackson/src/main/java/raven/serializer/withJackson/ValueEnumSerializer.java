package raven.serializer.withJackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.raven.commons.data.ValueEnum;

import java.io.IOException;

/**
 * @author yi.liang
 * @date 2018/1/9 23:00:00
 * @since JDK1.8
 */
public class ValueEnumSerializer extends JsonSerializer<ValueEnum>
        implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @param value
     * @param generator
     * @param provider
     * @throws IOException
     */
    @Override
    public void serialize(ValueEnum value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        if (value != null) {
            generator.writeNumber(value.getValue());
        } else {
            generator.writeNumber(0);
        }
    }

}
