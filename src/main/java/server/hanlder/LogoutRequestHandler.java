package server.hanlder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import request.LogoutRequestPacket;
import response.LogoutResponsePacket;
import util.SessionUtils;

/**
 * Created by chen on 2018/12/4.
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutRequestPacket logoutRequestPacket) throws Exception {
        String userId = logoutRequestPacket.getUserId();
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        channelHandlerContext.channel().writeAndFlush(logoutResponsePacket);
        SessionUtils.unBindSession(SessionUtils.getSession(channelHandlerContext.channel()), channelHandlerContext.channel());
    }
}