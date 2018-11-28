package server.hanlder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import request.MessageRequestPacket;
import response.MessageResponsePacket;

import java.util.Date;

/**
 * Created by chen on 2018/11/28.
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {

        MessageResponsePacket messageResponsePacket=new MessageResponsePacket();
        messageResponsePacket.setResponseMessage(new Date() +"-服务器返回消息 :" + messageRequestPacket.getRequestMessage()+"from server");
        System.out.println("服务端收到了消息:" + messageRequestPacket.getRequestMessage());
        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }
}
