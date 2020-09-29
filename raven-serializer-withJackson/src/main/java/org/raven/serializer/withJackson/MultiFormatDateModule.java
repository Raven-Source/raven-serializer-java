package org.raven.serializer.withJackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Date;

public class MultiFormatDateModule extends SimpleModule {

    public MultiFormatDateModule(SerializerSetting setting) {
        super("MultiFormatDateModule", PackageVersion.VERSION);

        this.addDeserializer(Date.class, new MultiFormatDateDeserializer(setting.getDeserializeDateFormatString()));
    }
}
