package client.console;

import io.netty.channel.Channel;
import request.LogoutRequestPacket;

import java.util.Scanner;

/**
 * Created by chen on 2018/12/3.
 */
public class LogoutCommand implements ConsoleCommand {
    @Override
    public void exce(Scanner scanner, Channel channel) {
        System.out.println("正在退出登录");
        LogoutRequestPacket requestPacket=new LogoutRequestPacket();
        channel.writeAndFlush(requestPacket);
    }
}
