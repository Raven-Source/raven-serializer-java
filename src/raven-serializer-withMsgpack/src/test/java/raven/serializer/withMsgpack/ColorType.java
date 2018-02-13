package raven.serializer.withMsgpack;

import org.msgpack.annotation.Index;
import org.msgpack.annotation.Message;
import org.msgpack.annotation.MessagePackOrdinalEnum;
import raven.data.entity.ValueEnum;

public enum ColorType implements ValueEnum {

    A(1),
    B(2),
    D(4),
    C(3);

    private int value;

    @Override
    public int getValue() {
        return value;
    }

    ColorType(int val) {
        this.value = val;
    }

}
