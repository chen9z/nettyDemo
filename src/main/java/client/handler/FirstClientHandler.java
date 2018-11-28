package client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * Created by chen on 2018/11/28.
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 1000; i++) {
            byte[] bytes = ("客户端" + i + "次请求").getBytes(Charset.forName("utf-8"));
            ByteBuf byteBuf = ctx.alloc().ioBuffer().writeBytes(bytes);
            ctx.channel().writeAndFlush(byteBuf);
        }
    }
}
