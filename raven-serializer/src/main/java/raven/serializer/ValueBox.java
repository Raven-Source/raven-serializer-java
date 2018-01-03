package raven.serializer;

public class ValueBox<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ValueBox(T value) {
        this.value = value;
    }
}
