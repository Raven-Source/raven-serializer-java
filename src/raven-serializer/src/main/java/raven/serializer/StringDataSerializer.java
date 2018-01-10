package raven.serializer;

import java.io.IOException;

/**
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/3 14:00:00
 */
public interface StringDataSerializer {
    String serializeToString(Object obj) throws IOException;
}
