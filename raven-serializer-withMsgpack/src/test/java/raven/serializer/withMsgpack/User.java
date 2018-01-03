package raven.serializer.withMsgpack;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class User {

    @JsonProperty(index = 2)
    private int id;
    @JsonProperty(index = 1)
    private String name;
    @JsonProperty(index = 0)
    private Date time;
    @JsonProperty(index = 3)
    private List<Integer> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
