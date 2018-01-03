package raven.serializer.withMsgpack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MsgpackSerializerTest {

    User user;

    @Before
    public void before() {

        user = new User();
        user.setId(123);
        user.setName("翻船了");
        user.setList(new ArrayList<Integer>() {
            {
                add(1);
            }

            {
                add(3);
            }
        });
        user.setTime(new Date());

    }

    @Test
    public void Serialize() throws Exception {

        MsgpackSerializer serializer = new MsgpackSerializer();

        byte[] data = serializer.serialize(user);
        String json = new String(data, Charset.defaultCharset());

        System.out.println(json);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Assert.assertNotEquals(json, null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        serializer.serialize(user, outputStream);

        json = outputStream.toString("UTF-8");
        outputStream.close();
        Assert.assertNotEquals(json, null);


        User user2 = serializer.deserialize(User.class, data);
        Assert.assertEquals(user.getName(), user2.getName());
        Assert.assertEquals(user.getTime().toString(), user2.getTime().toString());

        user2 = serializer.deserialize(User.class, data, 0, data.length);
        Assert.assertEquals(user.getName(), user2.getName());
        Assert.assertEquals(user.getTime().toString(), user2.getTime().toString());

        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        user2 = serializer.deserialize(User.class, inputStream);
        Assert.assertEquals(user.getName(), user2.getName());
        Assert.assertEquals(user.getTime().toString(), user2.getTime().toString());
        inputStream.close();
    }

    /*@Test
    public void deserialize() throws Exception {

        MsgpackSerializer serializer = new MsgpackSerializer();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String serializerRes = "{\"id\":123,\"name\":\"翻船了\",\"time\":\"" + formatter.format(user.getTime()) + "\",\"list\":[1,3]}";
        byte[] data = serializerRes.getBytes("UTF-8");

        User user2 = serializer.deserialize(User.class, data);
        Assert.assertEquals(user.getName(), user2.getName());
        Assert.assertEquals(user.getTime().toString(), user2.getTime().toString());

        user2 = serializer.deserialize(User.class, data, 0, data.length);
        Assert.assertEquals(user.getName(), user2.getName());
        Assert.assertEquals(user.getTime().toString(), user2.getTime().toString());

        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        user2 = serializer.deserialize(User.class, inputStream);
        Assert.assertEquals(user.getName(), user2.getName());
        Assert.assertEquals(user.getTime().toString(), user2.getTime().toString());
        inputStream.close();


    }*/


}
