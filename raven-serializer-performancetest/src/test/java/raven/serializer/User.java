package raven.serializer;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private int id;
    private String name;
    private Date time;
    private List<Integer> list;
    private long a;
    private Date date2;

    private boolean gender;
}
