package raven.serializer.withJackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import raven.serializer.withJackson.format.JsonPropertyFormat;
import raven.serializer.withJackson.format.JsonPropertyFormatType;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonPropertyFormat(JsonPropertyFormatType.CamelCase)
public class Paper2 extends Paper {

    @Override
    public String getDesc() {
        return super.getDesc();
    }

    @Override
    public void setDesc(String desc) {
        super.setDesc(desc);
    }

    @Getter
    @Setter
    private ColorType2 colorType2;

}
