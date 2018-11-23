package handler;

import request.LoginRequestPacket;
import command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import response.LoginResponsePack;

/**
 * Created by chen on 2018/11/23.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf= (ByteBuf) msg;
        System.out.println(byteBuf.toString());
        LoginResponsePack response = (LoginResponsePack) PacketCodeC.INSTANCE.decode(byteBuf);
        if (response.isSuccess()) {
            System.out.println("登录成功：" + response.getMsg());
        }else {
            System.out.println("登录失败：" + response.getMsg());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setPassword("zq438");
        loginRequestPacket.setUserName("zq");
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),loginRequestPacket);
        ctx.channel().writeAndFlush(byteBuf);
    }
}
