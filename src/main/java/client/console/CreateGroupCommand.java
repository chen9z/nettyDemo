package client.console;

import io.netty.channel.Channel;
import request.CreateGroupRequestPacket;
import util.SessionUtils;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by chen on 2018/12/3.
 */
public class CreateGroupCommand implements ConsoleCommand {
    @Override
    public void exce(Scanner scanner, Channel channel) {

        System.out.println("请输入用户id,逗号分开");

        String next = scanner.nextLine();
        String[] userArray = next.split(",");

        CreateGroupRequestPacket requestPacket = new CreateGroupRequestPacket();
        requestPacket.setCreateUserId(SessionUtils.getSession(channel).getUserId());
        requestPacket.setUserIdList(Arrays.asList(userArray));
        channel.writeAndFlush(requestPacket);
    }
}
