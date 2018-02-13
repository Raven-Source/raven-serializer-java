package raven.serializer;

import com.sun.nio.sctp.MessageInfo;

import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/1/3 14:00:00
 */
public class SerializerFactory {

    private static HashMap<String, DataSerializer> _serializerDict = new HashMap<String, DataSerializer>();
    private static EnumMap<SerializerType, String[]> _clazzNameDict = new EnumMap<SerializerType, String[]>(SerializerType.class) {
        {
            put(SerializerType.Jackson, new String[]{"raven.serializer.withJackson", "JacksonSerializer"});
            put(SerializerType.Protobuf, new String[]{"raven.serializer.withProtobuf", "ProtobufSerializer"});
            put(SerializerType.MessagePack, new String[]{"raven.serializer.withMessagePack", "MessagePackSerializer"});
        }
    };

    /**
     * @param serializerType
     * @param args
     * @return
     * @throws Exception
     */
    private static DataSerializer getDataSerializer(SerializerType serializerType, Object[] args) {

        String key = getKey(serializerType, args);
        if (_serializerDict.containsKey(key)) {
            return _serializerDict.get(key);

        } else {
            String[] clazzName = _clazzNameDict.get(serializerType);

            try {
                Class clazz = Class.forName(String.join(".", clazzName[0], clazzName[1]));
                if (args == null || args.length == 0) {
                    return (DataSerializer) clazz.newInstance();
                } else {
                    for (Constructor constructor : clazz.getConstructors()) {

                        int count = constructor.getParameterCount();
                        if (count != args.length) continue;

                        Class<?>[] paramsTypes = constructor.getParameterTypes();
                        boolean applyTo = true;
                        for (int i = 0; i < args.length; i++) {
                            if (!args[i].getClass().isAssignableFrom(paramsTypes[i])) {
                                applyTo = false;
                                break;
                            }
                        }

                        if (applyTo) {
                            return (DataSerializer) constructor.newInstance(args);
                        }
                    }

                    return null;
                }
            } catch (Exception ex) {
                return null;
            }
        }

    }

    /**
     * @param serializerType
     * @param args
     * @return
     */
    private static String getKey(SerializerType serializerType, Object[] args) {

        String argsStr;
        if (args == null) {
            argsStr = "";
        } else {
            StringJoiner joiner = new StringJoiner("_");
            Arrays.stream(args).map(x -> x.toString()).forEach(x -> joiner.add(x));
            argsStr = joiner.toString();
        }
        return MessageFormat.format("{0}:{1}", _clazzNameDict.get(serializerType)[1], argsStr);
    }

    /**
     * 创建类型
     *
     * @param serializerType
     * @return
     */
    public static DataSerializer create(SerializerType serializerType) {
        return create(serializerType, null);
    }

    /**
     * 创建类型
     *
     * @param serializerType
     * @param args
     * @return
     */
    public static DataSerializer create(SerializerType serializerType, Object[] args) {
        DataSerializer serializer = getDataSerializer(serializerType, args);
        //IDataSerializer serializer = (IDataSerializer)Activator.CreateInstance(type, new object[] { });
        return serializer;
    }

    /**
     * 创建类型
     *
     * @param serializerType
     * @return
     */
    public static DataSerializer create(String serializerType) {
        return create(serializerType, null);
    }

    /**
     * 创建类型
     *
     * @param serializerTypeStr
     * @param args
     * @return
     */
    public static DataSerializer create(String serializerTypeStr, Object[] args) {
        SerializerType serializerType = Enum.valueOf(SerializerType.class, serializerTypeStr);
        return create(serializerType, args);
    }

}
