package raven.serializer.withMsgpack;

import lombok.Getter;
import lombok.Setter;
import org.msgpack.annotation.Index;
import org.msgpack.annotation.Message;

import java.util.Date;

@Message
public class User2 {

    @Getter
    @Setter
    @Index(2)
    private int id;

    @Getter
    @Setter
    @Index(1)
    private String name;

}
