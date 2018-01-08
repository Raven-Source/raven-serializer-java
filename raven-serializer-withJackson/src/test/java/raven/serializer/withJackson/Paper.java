package raven.serializer.withJackson;

import lombok.Getter;
import lombok.Setter;
import raven.serializer.serialization.DataMember;
import raven.serializer.withJackson.annotations.JsonPropertyFormat;
import raven.serializer.withJackson.annotations.JsonPropertyFormatType;

@JsonPropertyFormat(JsonPropertyFormatType.PascalCase)
public class Paper {

    @Getter
    @Setter
    private ColorType colorType;

    @Getter
    @Setter
    @DataMember(name = "tlt")
    private String title;

}
