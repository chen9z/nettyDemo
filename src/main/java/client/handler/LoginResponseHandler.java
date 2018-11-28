package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import request.LoginRequestPacket;
import response.LoginResponsePacket;
import util.LoginUtils;

/**
 * Created by chen on 2018/11/28.
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setPassword("zq438");
        loginRequestPacket.setUserName("zq");
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.isSuccess()) {
            System.out.println("登录成功：" + loginResponsePacket.getMsg());
            LoginUtils.markAsLogin(channelHandlerContext.channel());
        }else {
            System.out.println("登录失败：" + loginResponsePacket.getMsg());
        }
    }
}
