package command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import serialize.Serializer;
import serialize.SerializerAlogrithm;
import serialize.iml.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chen on 2018/11/21.
 */
public class PacketCodeC {


    private static final int MAGIC_NUMBER=0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;

    private static final Map<Byte,Serializer> serializerMap;


    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();
        serializerMap.put(SerializerAlogrithm.JSON, new JSONSerializer());
    }

    public ByteBuf encode(Packet packet) {
        ByteBuf byteBuf=ByteBufAllocator.DEFAULT.ioBuffer();
        byteBuf.writeByte(MAGIC_NUMBER);
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommond());

        //将对象转换为bytes拼接到bytebuf中
        byte[] serializer = Serializer.DEFAULT.serializer(packet);
        byteBuf.writeInt(serializer.length);
        byteBuf.writeBytes(serializer);
        return byteBuf;
    }
}
