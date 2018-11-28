package server.hanlder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import request.LoginRequestPacket;
import response.LoginResponsePacket;

/**
 * Created by chen on 2018/11/28.
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {

        LoginResponsePacket response = new LoginResponsePacket();
        if (vaild(loginRequestPacket)) {
            response.setSuccess(true);
            response.setMsg("登陆成功！张琦是个死胖子 from server");
        } else {
            response.setSuccess(false);
            response.setMsg("登录失败！死胖子是张琦 from server");
        }
        channelHandlerContext.channel().writeAndFlush(response);
    }

    private boolean vaild(LoginRequestPacket packet) {
        return "zq".equals(packet.getUserName()) && "zq438".equals(packet.getPassword());
    }
}
