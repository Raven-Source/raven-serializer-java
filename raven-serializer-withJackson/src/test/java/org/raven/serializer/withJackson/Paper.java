package org.raven.serializer.withJackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import org.raven.commons.data.MemberFormatType;
import org.raven.commons.data.annotation.Contract;
import org.raven.commons.data.annotation.Member;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
@Contract(formatType = MemberFormatType.PascalCase)
public class Paper {

    @Getter
    @Setter
    private ColorType color;

    @Getter
    @Setter
    @Member("tlt")
    private String title;

    @Getter
    @Setter
    private String desc;

}
