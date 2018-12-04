package client.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import request.CreateGroupRequestPacket;
import response.CreateGroupResponsePacket;
import util.SessionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2018/12/3.
 */
public class CreateGroupRequestHanlder extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();
        List<String> userNameList = new ArrayList<>();

        ChannelGroup channelGroup = new DefaultChannelGroup(channelHandlerContext.executor());

        for (String userId : userIdList) {
            Channel userChannel = SessionUtils.getChannel(userId);
            if (userChannel != null) {
                channelGroup.add(userChannel);
                userNameList.add(SessionUtils.getSession(userChannel).getUserName());
            }
        }

        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setGroupId(SessionUtils.randomUserId());
        responsePacket.setMessage("群组创建成功");
        responsePacket.setSuccess(true);
        System.out.println("群组里面有" + userNameList.toString());

        channelGroup.writeAndFlush(responsePacket);

    }
}
