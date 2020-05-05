package org.raven.serializer.withJackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.raven.commons.data.StringUtils;
import org.raven.commons.data.ValueType;
import org.raven.commons.data.ValueTypeUtils;

import java.io.IOException;

/**
 * @author yi.liang
 * date 2018/1/9 23:00:00
 * @since JDK1.8
 */
@Slf4j
public class ValueTypeDeserializer<T extends ValueType> extends JsonDeserializer<T>
    implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private final Class<T> target;

    /**
     * @param target
     */
    public ValueTypeDeserializer(final Class<T> target) {
        this.target = target;
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

        int tokenId = p.getCurrentTokenId();
        if (tokenId == JsonTokenId.ID_NUMBER_INT) {
            return ValueTypeUtils.valueOf(target, p.getIntValue());
        } else if (tokenId == JsonTokenId.ID_NUMBER_FLOAT) {
            return ValueTypeUtils.valueOf(target, p.getFloatValue());
        } else {
            String source = p.getValueAsString();
            if (StringUtils.isNumeric(source)) {
                return ValueTypeUtils.valueOf(target, source);
            }

            return (T) ValueTypeUtils.nameOf((Class) target, source);
        }

    }

}
