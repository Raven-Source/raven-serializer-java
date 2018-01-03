package raven.serializer;

import java.util.Arrays;
import java.util.HashMap;

public class SerializerFactory {


    private static HashMap<String, DataSerializer> _serializerDict = new HashMap<String, DataSerializer>();
    private static HashMap<SerializerType, String[]> _clazzNameDict = new HashMap<SerializerType, String[]>() {

        {
            _clazzNameDict.put(SerializerType.Jackson, new String[]{"raven.serializer.withJackson", "JacksonSerializer"});
            _clazzNameDict.put(SerializerType.Protobuf, new String[]{"raven.serializer.withProtobuf", "ProtobufSerializer"});
            _clazzNameDict.put(SerializerType.MessagePack, new String[]{"raven.serializer.withMessagePack", "MessagePackSerializer"});
        }
    };

//    private static DataSerializer GetDataSerializer(SerializerType serializerType, Object[] args) {
//
//        String key = null;
//        if (_serializerDict.containsKey(key)) {
//            return _serializerDict.get(key);
//
//        } else {
//            String[] clazzName = _clazzNameDict.get(serializerType);
//            Class clazz = Class.forName(String.)
//        }
//
//    }


//    /// <summary>
//    ///
//    /// </summary>
//    /// <param name="serializerType"></param>
//    /// <param name="args"></param>
//    /// <returns></returns>
//    private static String GetKey(SerializerType serializerType, Object[] args) {
//
//        if(args == null){
//
//        }
//        String[] arrStr = new String[args.length];
//        return string.Format("{0}:{1}", _typeNameDict.get(serializerType)[1], args == null ? null : String.join("_", args);               12
//    }
}
