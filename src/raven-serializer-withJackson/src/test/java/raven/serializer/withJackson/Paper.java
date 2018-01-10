package raven.serializer.withJackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import raven.serializer.serialization.DataMember;
import raven.serializer.withJackson.format.JsonPropertyFormat;
import raven.serializer.withJackson.format.JsonPropertyFormatType;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonPropertyFormat(JsonPropertyFormatType.PascalCase)
public class Paper {

    @Getter
    @Setter
    private ColorType colorType;

    @Getter
    @Setter
    @DataMember(name = "tlt")
    private String title;

    @Getter
    @Setter
    private String desc;

}
