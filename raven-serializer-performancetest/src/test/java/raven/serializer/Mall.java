package raven.serializer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.msgpack.annotation.Index;
import org.msgpack.annotation.Message;

@Getter
@Setter
@ToString
@Message
public class Mall {

    @Index(0)
    private long id;

    @Index(1)
    private String name;

    @Index(2)
    private java.util.Date date;

    @Index(3)
    private long groupId;

    @Index(4)
    private String AAAAAAAAAA;

    @Index(5)
    private String BBBBBBBBBB;

    @Index(6)
    private String CCCCCCCCCC;

    @Index(7)
    private String D;

    @Index(8)
    private String EEEEEEEEEE;

    @Index(9)
    private String F;

    @Index(10)
    private String g;

    @Index(11)
    private String HHHHHHHHHH;
    @Index(12)
    private String I;
    @Index(13)
    private String J;
    @Index(14)
    private User user;

}
