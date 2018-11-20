package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * Created by chen on 2018/11/20.
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("开始读取");
        ByteBuf buf= (ByteBuf) msg;
        System.out.println(buf.toString(Charset.forName("utf-8")));

        ByteBuf outBuf = ctx.alloc().buffer();
        outBuf.writeBytes("zq438 is 291 from server".getBytes());
        ctx.channel().writeAndFlush(outBuf);
    }

}
