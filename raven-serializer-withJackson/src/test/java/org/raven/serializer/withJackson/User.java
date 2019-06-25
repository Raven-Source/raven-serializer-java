package org.raven.serializer.withJackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.raven.commons.data.MemberFormatType;
import org.raven.commons.data.annotation.Contract;

import java.util.Date;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
@Contract(formatType = MemberFormatType.PascalCase)
public class User {
    private int id;
    private String name;
    private Date time;
    private List<Integer> list;
    private long a;
    private Date date2;

    private boolean gender;

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

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

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
