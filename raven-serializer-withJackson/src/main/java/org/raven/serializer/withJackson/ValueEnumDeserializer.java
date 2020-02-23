package org.raven.serializer.withJackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.raven.commons.data.ValueEnum;
import org.raven.commons.data.ValueEnumUtils;
import org.raven.serializer.core.util.StringUtils;

import java.io.IOException;

/**
 * @author yi.liang
 * date 2018/1/9 23:00:00
 * @since JDK1.8
 */
public class ValueEnumDeserializer<T extends Enum<T> & ValueEnum> extends JsonDeserializer<T>
        implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
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

        /*int value;
        /*int tokenId = p.getCurrentTokenId();
        if (tokenId == JsonTokenId.ID_NUMBER_INT) {
            value = p.getValueAsInt();
            return ValueEnumUtils.valueOf(clazz, value);
        } else if (tokenId == JsonTokenId.ID_STRING) {
            String name = p.getValueAsString();

            try {
                value = Integer.parseInt(name);
                return ValueEnumUtils.valueOf(clazz, value);

            }catch (Exception e){

            }

            if (!StringUtils.isBlank(name)) {
                return Enum.valueOf(clazz, name);
            }
        }*/

        String name = p.getValueAsString();

        try {
            int value = Integer.parseInt(name);
            return ValueEnumUtils.valueOf(clazz, value);

        }catch (Exception e){

        }

        if (!StringUtils.isBlank(name)) {
            return Enum.valueOf(clazz, name);
        }

        return null;
    }

}
