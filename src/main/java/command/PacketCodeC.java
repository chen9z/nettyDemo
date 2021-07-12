package command;

import io.netty.buffer.ByteBuf;
import request.CreateGroupRequestPacket;
import request.LoginRequestPacket;
import request.LogoutRequestPacket;
import request.MessageRequestPacket;
import response.CreateGroupResponsePacket;
import response.LoginResponsePacket;
import response.LogoutResponsePacket;
import response.MessageResponsePacket;
import serialize.Serializer;
import serialize.iml.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chen on 2018/11/21.
 */
public class PacketCodeC {

    public static final PacketCodeC INSTANCE=new PacketCodeC();

    public static final int MAGIC_NUMBER=0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;

    private static final Map<Byte,Serializer> serializerMap;


    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class);

        serializerMap = new HashMap<>();
        serializerMap.put(Serializer.DEFAULT.getSerializerAlogrithm(), new JSONSerializer());
    }

    public ByteBuf encode(ByteBuf byteBuf,Packet packet) {
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());

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
