package handler;

import request.LoginRequestPacket;
import command.Packet;
import command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import response.LoginResponsePack;

/**
 * Created by chen on 2018/11/23.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf= (ByteBuf) msg;
        System.out.println(byteBuf.toString());
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        LoginResponsePack response = new LoginResponsePack();
        if (packet instanceof LoginRequestPacket) {
            if (vaild((LoginRequestPacket) packet)) {
                response.setSuccess(true);
                response.setMsg("登陆成功！张琦是个死胖子 from server");
            }else {
                response.setSuccess(false);
                response.setMsg("登录失败！死胖子是张琦 from server");
            }
        }
        ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), response);
        ctx.channel().writeAndFlush(buf);
    }

    private boolean vaild(LoginRequestPacket packet) {
        return "zq".equals(packet.getUserName()) && "zq438".equals(packet.getPassword());
    }

}
