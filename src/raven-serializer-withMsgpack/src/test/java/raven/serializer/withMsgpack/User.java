package raven.serializer.withMsgpack;


import lombok.Getter;
import lombok.Setter;
import org.msgpack.annotation.Index;
import org.msgpack.annotation.Message;

import java.util.Date;
import java.util.List;

@Message
public class User {

    @Getter
    @Setter
    @Index(2)
    private int id;

    @Getter
    @Setter
    @Index(1)
    private String name;

    @Getter
    @Setter
    private Date time;

    @Getter
    @Setter
    private List<Integer> list;
}
