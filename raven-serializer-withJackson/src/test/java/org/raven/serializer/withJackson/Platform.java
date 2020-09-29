package org.raven.serializer.withJackson;

import org.raven.commons.data.StringType;

public enum Platform implements StringType {

    WX("wx"),
    Ali("ali");

    private final String value;

    Platform(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
