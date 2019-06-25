package raven.serializer;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.msgpack.annotation.Message;

@Getter
@Setter
@ToString
@Message
public class User {

    private int id;
    private String name;
    private Date time;
    private List<Integer> list;
    private long a;
    private Date date2;

    private boolean gender;
}
