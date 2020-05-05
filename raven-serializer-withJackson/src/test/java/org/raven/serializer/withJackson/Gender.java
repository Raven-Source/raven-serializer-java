package org.raven.serializer.withJackson;

import org.raven.commons.data.ValueType;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2020.04.23 01:00
 */
public class Gender implements ValueType<Integer> {

    private int value;

    @Override
    public Integer getValue() {
        return value;
    }

    private Gender(int value) {
        this.value = value;
    }

    public final static Gender man = new Gender(1);
    public final static Gender woman = new Gender(2);

    public static Gender valueOf(Integer i) {
        return new Gender(i.intValue());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Gender))
            return false;
        return ((Gender) obj).value == this.value;
    }
}
