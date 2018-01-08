package raven.serializer.withJackson;

public enum ColorType {

    A(1),
    B(2),
    C(3);

    private int val;

    private ColorType(int val) {
        this.val = val;
    }

}
