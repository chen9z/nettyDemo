package command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import request.LoginRequestPacket;
import response.LoginResponsePack;
import serialize.Serializer;
import serialize.SerializerAlogrithm;
import serialize.iml.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chen on 2018/11/21.
 */
public class PacketCodeC {

    public static final PacketCodeC INSTANCE=new PacketCodeC();

    private static final int MAGIC_NUMBER=0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;

    private static final Map<Byte,Serializer> serializerMap;


    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePack.class);

        serializerMap = new HashMap<>();
        serializerMap.put(Serializer.DEFAULT.getSerializerAlogrithm(), new JSONSerializer());
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator,Packet packet) {
        ByteBuf byteBuf=byteBufAllocator.ioBuffer();
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommond());

        //将对象转换为bytes拼接到bytebuf中
        byte[] bytes = Serializer.DEFAULT.serializer(packet);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){
        //跳过magicNumber
        byteBuf.skipBytes(4);
        //跳过版本号
        byteBuf.skipBytes(1);

        byte alogrithmType = byteBuf.readByte();
        byte commandType = byteBuf.readByte();
        Class<? extends Packet> requestType = packetTypeMap.get(commandType);

        Serializer serializer = serializerMap.get(alogrithmType);

        if (serializer != null && requestType != null) {
            int dataLength = byteBuf.readInt();
            byte[] bytes = new byte[dataLength];
            byteBuf.readBytes(bytes);
            return serializer.deserialize(requestType,bytes);
        }
        return null;
    }
}
