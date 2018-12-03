package client.console;

import io.netty.channel.Channel;
import request.LoginRequestPacket;

import java.util.Scanner;

/**
 * Created by chen on 2018/12/3.
 */
public class LoginCommand implements ConsoleCommand {
    @Override
    public void exce(Scanner scanner, Channel channel) {
        System.out.println("请输入用户名：");
        if (scanner.hasNext()) {
            LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
            String inputUserName = scanner.nextLine();
            loginRequestPacket.setUserName(inputUserName);
            loginRequestPacket.setPassword("123456");
            channel.writeAndFlush(loginRequestPacket);
            waitForLoginResponse();
        }
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
    }
}
