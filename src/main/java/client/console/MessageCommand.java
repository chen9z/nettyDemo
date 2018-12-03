package client.console;

import io.netty.channel.Channel;
import request.MessageRequestPacket;
import util.SessionUtils;

import java.util.Scanner;

/**
 * Created by chen on 2018/12/3.
 */
public class MessageCommand implements ConsoleCommand{
    @Override
    public void exce(Scanner scanner, Channel channel) {

        System.out.println("输入消息发送至服务器：");
        String message = scanner.next();
        String toUserId = scanner.next();

        MessageRequestPacket packet = new MessageRequestPacket();
        packet.setToUserId(toUserId);
        packet.setFromUserId(SessionUtils.getSession(channel).getUserId());
        packet.setRequestMessage(message);
        channel.writeAndFlush(packet);
    }
}
