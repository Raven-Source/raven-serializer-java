package raven.serializer;

/**
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/3 14:00:00
 */
public enum SerializerType {

    Jackson(),

    Protobuf(),

    MessagePack();

}

/*

public enum SerializerType {

    Jackson("Jackson"),

    Protobuf("Protobuf"),

    MessagePack("MessagePack");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    SerializerType(String name) {
        this.name = name;
    }
}*/
