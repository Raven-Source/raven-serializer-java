package raven.serializer;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
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
    @Protobuf
    private long id;

    @Index(1)
    @Protobuf
    private String name;

    @Index(2)
    private java.util.Date date;

    @Index(3)
    @Protobuf
    private long groupId;

    @Index(4)
    @Protobuf
    private String AAAAAAAAAA;

    @Index(5)
    @Protobuf
    private String BBBBBBBBBB;

    @Index(6)
    @Protobuf
    private String CCCCCCCCCC;

    @Index(7)
    @Protobuf
    private String D;

    @Index(8)
    @Protobuf
    private String EEEEEEEEEE;

    @Index(9)
    @Protobuf
    private String F;

    @Index(10)
    @Protobuf
    private String g;

    @Index(11)
    @Protobuf
    private String HHHHHHHHHH;

    @Index(12)
    @Protobuf
    private String I;

    @Index(13)
    @Protobuf
    private String J;

    @Index(14)
    @Protobuf(fieldType = FieldType.OBJECT)
    private User user;

}
