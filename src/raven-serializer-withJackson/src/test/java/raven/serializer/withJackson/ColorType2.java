package raven.serializer.withJackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import raven.data.entity.ValueEnum;

//@JsonFormat(shape = JsonFormat.Shape.NUMBER)
//@JsonDeserialize(using = ValueEnumTypeDeserializer.class)
//@JsonSerialize(using = ValueEnumTypeSerializer.class)
public enum ColorType2 implements ValueEnum {
    A(1),
    D(4),
    C(3),
    B(2);

    private int val;

    private ColorType2(int val) {
        this.val = val;
    }

    //@JsonValue
    public int getValue() {
        return val;
    }

    /*@JsonCreator
    public static ColorType2 create(int value) {
        for (ColorType2 type : ColorType2.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }*/
}
