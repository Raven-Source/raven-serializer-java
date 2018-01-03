package raven.serializer;

import java.io.IOException;

public interface StringDataSerializer {
    String serializeToString(Object obj) throws IOException;
}
