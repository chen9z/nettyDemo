package server.hanlder;

import attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import request.MessageRequestPacket;
import response.MessageResponsePacket;
import response.Session;
import util.SessionUtils;

import java.util.Date;

/**
 * Created by chen on 2018/11/28.
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {

        MessageResponsePacket messageResponsePacket=new MessageResponsePacket();
        System.out.println("服务端收到了消息:" + messageRequestPacket.getRequestMessage());
        Channel responseChannel = SessionUtils.getChannel(messageRequestPacket.getToUserId());

        if (responseChannel == null || SessionUtils.hasLogin(responseChannel)) {
            messageResponsePacket.setResponseMessage("用户不在线，发送失败");
        }else {
            Session session = responseChannel.attr(Attributes.SESSION).get();
            messageResponsePacket.setFromUserName(session.getUserName());
            messageResponsePacket.setFromUserName(messageRequestPacket.getFromUserId());
            responseChannel.writeAndFlush(messageRequestPacket);
        }
        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }
}
