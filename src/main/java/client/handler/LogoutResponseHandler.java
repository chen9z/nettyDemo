package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import response.LogoutResponsePacket;
import util.SessionUtils;

/**
 * Created by chen on 2018/12/3.
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutResponsePacket logoutResponsePacket) throws Exception {
        if (logoutResponsePacket.isSuccess()) {
            System.out.println("登出成功");
            SessionUtils.unBindSession(SessionUtils.getSession(channelHandlerContext.channel()),channelHandlerContext.channel());
        }else {
            System.out.println("登出失败");
        }
    }
}
