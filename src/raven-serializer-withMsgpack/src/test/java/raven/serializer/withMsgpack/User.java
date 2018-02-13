package raven.serializer.withMsgpack;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.msgpack.annotation.Index;
import org.msgpack.annotation.Message;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Message
public class User {

    @Index(2)
    private int id;

    @Index(1)
    private String name;

    private Date time;

    private List<Integer> list;
}
