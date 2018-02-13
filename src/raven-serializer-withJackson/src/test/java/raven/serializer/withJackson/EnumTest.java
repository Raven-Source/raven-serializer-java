package raven.serializer.withJackson;

import org.junit.Test;
import raven.serializer.DataSerializer;

import java.io.IOException;

public class EnumTest {

    @Test
    public void test()
            throws IOException {

        DataSerializer serializer = new JacksonSerializer();
        byte[] data = {34, 66, 34};//serializer.serialize(ColorType.B);
        String res = new String(data, "UTF-8");
        System.out.println(res);

        ColorType color = serializer.deserialize(ColorType.class, data);
        System.out.println(color);

    }

}
