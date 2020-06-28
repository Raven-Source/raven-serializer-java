package org.raven.serializer.withJackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.raven.commons.data.ValueType;

import java.util.Date;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2020.06.29 01:26
 */
public class ValueTypeModule extends SimpleModule {

    protected SerializerSetting setting;

    public ValueTypeModule(SerializerSetting setting) {
        super("ValueTypeModule", PackageVersion.VERSION);

        this.setting = setting;
        this.addSerializer(ValueType.class, ValueTypeSerializer.INSTANCE);
        this.addDeserializer(Date.class, new MultiFormatDateDeserializer(setting.getDeserializeDateFormatString()));
    }

    @Override
    public void setupModule(SetupContext context) {

        super.setupModule(context);
        context.addDeserializers(new ValueTypeDeserializers());
    }

}
