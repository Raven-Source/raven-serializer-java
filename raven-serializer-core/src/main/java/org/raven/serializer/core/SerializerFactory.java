package org.raven.serializer.core;

import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * @author yi.liang
 * @date 2018.9.25
 * @since JDK1.8
 */
public class SerializerFactory {

    private static HashMap<String, Serializer> _serializerDict = new HashMap<String, Serializer>();
    private static EnumMap<SerializerType, String[]> _clazzNameDict = new EnumMap<SerializerType, String[]>(SerializerType.class) {
        {
            put(SerializerType.Jackson, new String[]{"org.raven.serializer.withJackson", "JacksonSerializer"});
            put(SerializerType.MessagePack, new String[]{"org.raven.serializer.withMsgpack", "MsgpackSerializer"});
        }
    };

    /**
     * @param serializerType
     * @param args
     * @return
     * @throws Exception
     */
    private static Serializer getDataSerializer(SerializerType serializerType, Object[] args) {

        String key = getKey(serializerType, args);
        if (_serializerDict.containsKey(key)) {
            return _serializerDict.get(key);

        } else {
            String[] clazzName = _clazzNameDict.get(serializerType);

            try {
                Class clazz = Class.forName(String.join(".", clazzName[0], clazzName[1]));
                if (args == null || args.length == 0) {
                    return (Serializer) clazz.newInstance();
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
                            return (Serializer) constructor.newInstance(args);
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
    public static Serializer create(SerializerType serializerType) {
        return create(serializerType, null);
    }

    /**
     * 创建类型
     *
     * @param serializerType
     * @param args
     * @return
     */
    public static Serializer create(SerializerType serializerType, Object[] args) {
        Serializer serializer = getDataSerializer(serializerType, args);
        //Serializer serializer = (Serializer)Activator.CreateInstance(type, new object[] { });
        return serializer;
    }

    /**
     * 创建类型
     *
     * @param serializerType
     * @return
     */
    public static Serializer create(String serializerType) {
        return create(serializerType, null);
    }

    /**
     * 创建类型
     *
     * @param serializerTypeStr
     * @param args
     * @return
     */
    public static Serializer create(String serializerTypeStr, Object[] args) {
        SerializerType serializerType = Enum.valueOf(SerializerType.class, serializerTypeStr);
        return create(serializerType, args);
    }

}
