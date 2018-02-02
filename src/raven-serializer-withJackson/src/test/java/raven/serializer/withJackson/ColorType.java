package raven.serializer.withJackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum ColorType {

    A(1),
    B(2),
    D(4),
    C(3);

    private int val;

    @JsonValue
    public int getVal() {
        return val;
    }

    @JsonCreator
    public ColorType getItem(int code) {
        for (ColorType item : values()) {
            if (item.getVal() == code) {
                return item;
            }
        }
        return null;
    }

    private ColorType(int val) {
        this.val = val;
    }

}

