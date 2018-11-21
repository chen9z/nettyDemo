package serialize;

import serialize.iml.JSONSerializer;

/**
 * Created by chen on 2018/11/21.
 */
public interface Serializer {

    Serializer DEFAULT=new JSONSerializer();

    byte getSerializerAlogrithm();

    byte[] serializer(Object object);

    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
