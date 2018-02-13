package raven.serializer.withJackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import raven.data.entity.ValueEnum;

//@JsonFormat(shape = JsonFormat.Shape.NUMBER)
//@JsonDeserialize(using = ValueEnumTypeDeserializer.class)
public enum ColorType  implements ValueEnum {

    A(1),
    B(2),
    D(4),
    C(3);

    private int val;

    //@JsonValue
    @Override
    public int getValue() {
        return val;
    }

    //@JsonCreator
    public ColorType getItem(int code) {
        for (ColorType item : values()) {
            if (item.getValue() == code) {
                return item;
            }
        }
        return null;
    }

    private ColorType(int val) {
        this.val = val;
    }

}

