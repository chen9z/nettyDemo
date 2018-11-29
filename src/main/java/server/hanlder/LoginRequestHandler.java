package server.hanlder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import request.LoginRequestPacket;
import response.LoginResponsePacket;
import response.Session;
import util.SessionUtils;

/**
 * Created by chen on 2018/11/28.
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {

        LoginResponsePacket response = new LoginResponsePacket();
        if (vaild(loginRequestPacket)) {
            response.setSuccess(true);
            SessionUtils.markAsLogin(channelHandlerContext.channel());
            Session session = new Session(SessionUtils.randomUserId(), loginRequestPacket.getUserName());
            SessionUtils.bindSession(session,channelHandlerContext.channel());
            response.setMsg("登陆成功!欢迎你，"+loginRequestPacket.getUserName());
        } else {
            response.setSuccess(false);
            response.setMsg("登录失败！用户名或密码错误");
        }
        channelHandlerContext.channel().writeAndFlush(response);
    }

    private boolean vaild(LoginRequestPacket packet) {
        return "123456".equals(packet.getPassword());
    }
}
