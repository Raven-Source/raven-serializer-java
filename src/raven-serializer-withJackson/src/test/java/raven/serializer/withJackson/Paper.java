package raven.serializer.withJackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import raven.data.entity.annotation.Property;
import raven.data.entity.annotation.PropertyFormat;
import raven.data.entity.annotation.PropertyFormatType;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
@PropertyFormat(value = PropertyFormatType.PascalCase)
public class Paper {

    @Getter
    @Setter
    private ColorType color;

    @Getter
    @Setter
    @Property(name = "tlt")
    private String title;

    @Getter
    @Setter
    private String desc;

}
