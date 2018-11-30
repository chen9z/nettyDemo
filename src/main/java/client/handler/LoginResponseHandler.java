package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import response.LoginResponsePacket;
import response.Session;
import util.SessionUtils;

/**
 * Created by chen on 2018/11/28.
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.isSuccess()) {
            System.out.println("登录成功：" + "欢迎你！"+"["+loginResponsePacket.getUserName()+"]  "+"[userId]:"+loginResponsePacket.getUserId());
            SessionUtils.bindSession(new Session(loginResponsePacket.getUserId(),loginResponsePacket.getUserName()),channelHandlerContext.channel());
        }else {
            System.out.println("登录失败：" + loginResponsePacket.getMsg());
        }
    }
}
