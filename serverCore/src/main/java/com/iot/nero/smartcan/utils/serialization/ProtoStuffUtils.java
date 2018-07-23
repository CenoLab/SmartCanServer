package com.iot.nero.smartcan.utils.serialization;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/3/13
 * Time   下午5:16
 */
public class ProtoStuffUtils {

    public ProtoStuffUtils() {
    }

    public static <T> byte[] serializer(T o) {
        Schema schema = RuntimeSchema.getSchema(o.getClass());
        return ProtostuffIOUtil.toByteArray(o, schema, LinkedBuffer.allocate(256));
    }

    public static <T> T deserializer(byte[] bytes, Class<T> clazz) {

        T obj = null;
        try {
            obj = clazz.newInstance();
            Schema schema = RuntimeSchema.getSchema(obj.getClass());
            ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
