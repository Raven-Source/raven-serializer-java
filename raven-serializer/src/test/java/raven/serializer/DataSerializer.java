package raven.serializer;

import org.junit.Test;

public class DataSerializer {

    @Test
    public void SerializerTypeTest(){

        for(SerializerType serializerType : SerializerType.values()){

            System.out.println(serializerType);
        }

    }
}
