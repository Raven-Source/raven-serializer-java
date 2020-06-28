package org.raven.serializer.withJackson;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2020.06.29 02:30
 */
public class JavaTimeTest {

    @Test
    public void test() throws IOException {

        JacksonSerializer serializer = new JacksonSerializer();

        LocalDateTime localDateTime = LocalDateTime.now();

        String res = serializer.serializeToString(localDateTime);
        System.out.println(res);

        localDateTime = serializer.deserialize(LocalDateTime.class, res.getBytes());
        System.out.println(localDateTime);

        Book book = new Book();
        book.setDate(localDateTime);res = serializer.serializeToString(book);
        System.out.println(res);

    }

    @Getter
    @Setter
    static class Book {

        private LocalDateTime date;

    }
}
