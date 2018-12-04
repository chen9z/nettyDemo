package server.hanlder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import response.CreateGroupResponsePacket;

/**
 * Created by chen on 2018/12/3.
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {

        if (createGroupResponsePacket.isSuccess()) {
            System.out.println("创建群组成功");
        }else {
            System.out.println(createGroupResponsePacket.getMessage());
        }

    }
}
