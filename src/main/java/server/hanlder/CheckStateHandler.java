package server.hanlder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by chen on 2018/12/4.
 */
public class CheckStateHandler extends IdleStateHandler {


    public CheckStateHandler() {
        super(15, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println("15秒没有检测到读数据连接关闭");
        ctx.channel().close();
    }
}
