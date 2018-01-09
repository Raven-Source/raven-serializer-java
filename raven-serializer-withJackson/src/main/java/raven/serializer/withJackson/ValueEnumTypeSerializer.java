package raven.serializer.withJackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.EnumSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import raven.serializer.ValueEnumType;

import java.io.IOException;

public class ValueEnumTypeSerializer extends JsonSerializer<ValueEnumType> {

    @Override
    public void serialize(ValueEnumType value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeNumber(value.getValue());

    }

}
