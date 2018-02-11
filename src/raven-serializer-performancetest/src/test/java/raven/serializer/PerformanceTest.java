package raven.serializer;

import org.junit.Test;
import raven.serializer.withJackson.SerializerSetting;

import java.nio.charset.Charset;
import java.util.Date;

public class PerformanceTest {

    @Test
    public void testAll()
            throws Exception {

        Mall mall = new Mall();
        mall.setId(1);
        mall.setDate(new Date());
        mall.setName("大悦城");
        mall.setGroupId(135);
        mall.setAAAAAAAAAA("aaaa");
        mall.setBBBBBBBBBB("BBBB");
        mall.setCCCCCCCCCC("hygfjrt7kuylkhgliu;oi;yhdhtfjhsj");
        mall.setD("kuykj687jrstskhgfk");
        mall.setEEEEEEEEEE("jhlhlgjhkuykjuyt");
        mall.setF("djsgfjdjg");
        mall.setG("fdsgasdgs");
        mall.setHHHHHHHHHH("hgfdhergfdhs");
        mall.setI("fdjnhterjrgtas");
        mall.setJ("fdhs5htrjgfdfdg");

        User user = new User();
        user.setId(132414);
        user.setTime(new Date());
        user.setName("ggsgshahsahsdha");
        mall.setUser(user);

        DataSerializer serializer = SerializerFactory.getDataSerializer(SerializerType.Jackson, new Object[]{SerializerSetting.getDefault()});
        byte[] data = serializer.serialize(mall);
        String jsonStr = new String(data, Charset.forName("UTF-8"));

        System.out.println(jsonStr);

    }

}
