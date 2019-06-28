package org.raven.serializer.withJackson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.raven.serializer.core.Serializer;

import java.io.IOException;

public class EnumTest {

    private Serializer serializer;

    @Before
    public void init() {
        serializer = new JacksonSerializer();
    }

    @Test
    public void valueEnumSerializer()
            throws IOException {

        valueEnumDeserializerTest(ColorType.A);
        valueEnumDeserializerTest(ColorType.B);
        valueEnumDeserializerTest(ColorType.C);
        valueEnumDeserializerTest(ColorType.D);


        valueEnumSerializerTest(ColorType.A);
        valueEnumSerializerTest(ColorType.B);
        valueEnumSerializerTest(ColorType.C);
        valueEnumSerializerTest(ColorType.D);
    }

    /**
     * @param colorType
     * @throws IOException
     */
    private void valueEnumSerializerTest(ColorType colorType)
            throws IOException {

        byte[] data = serializer.serialize(colorType);
        String color = new String(data);

        Assert.assertEquals(color, Integer.toString(colorType.getValue()));
        System.out.println("deserialize, color:" + color);
    }


    private void valueEnumDeserializerTest(ColorType colorType)
            throws IOException {

        ColorType color = serializer.deserialize(ColorType.class, Integer.toString(colorType.getValue()).getBytes());
        Assert.assertEquals(color, colorType);
        System.out.println("deserialize, color:" + color);
    }

    @Test
    public void paperDeserializerTest()
            throws IOException {

        Paper paper = new Paper();
        paper.setColor(ColorType.B);

        byte[] data = serializer.serialize(paper);
        System.out.println(new String(data));

        String json = "{\"Color\":2,\"tlt\":null,\"Desc\":null}";
        paper = serializer.deserialize(Paper.class, json.getBytes());

        System.out.println(paper.toString());

    }

    /**
     * int到字节数组的转换.
     */
    public byte[] intToByte(int number) {
        int temp = number;
        byte[] b = new byte[4];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位
            temp = temp >> 8;// 向右移8位
        }
        return b;
    }
}
