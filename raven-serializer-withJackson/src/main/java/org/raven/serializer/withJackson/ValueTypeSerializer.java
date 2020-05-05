package org.raven.serializer.withJackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.raven.commons.data.GenericUtils;
import org.raven.commons.data.ValueType;
import org.raven.commons.data.ValueTypeUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author yi.liang
 * date 2018/1/9 23:00:00
 * @since JDK1.8
 */
public class ValueTypeSerializer extends JsonSerializer<ValueType>
    implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @param value
     * @param generator
     * @param provider
     * @throws IOException
     */
    @Override
    public void serialize(ValueType value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        if (value != null) {

            Class clazz = ValueTypeUtils.getGenericType(value.getClass());

            if (clazz.equals(Integer.class)) {
                generator.writeNumber(value.getValue().intValue());
            }
            if (clazz.equals(Long.class)) {
                generator.writeNumber(value.getValue().longValue());
            }
            if (clazz.equals(BigInteger.class)) {
                generator.writeNumber((BigInteger) value.getValue());
            }
            if (clazz.equals(Double.class)) {
                generator.writeNumber(value.getValue().doubleValue());
            }
            if (clazz.equals(Float.class)) {
                generator.writeNumber(value.getValue().floatValue());
            }
            if (clazz.equals(BigDecimal.class)) {
                generator.writeNumber((BigDecimal) value.getValue());
            }

        } else {
            generator.writeNumber(0);
        }
    }

}
