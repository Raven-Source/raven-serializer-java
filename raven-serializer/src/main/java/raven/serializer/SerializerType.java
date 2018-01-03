package raven.serializer;

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
