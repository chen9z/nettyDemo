package client.console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by chen on 2018/12/3.
 */
public class ConsoleCommanManager implements ConsoleCommand {

    Map<String,ConsoleCommand> consoleCommandMap;

    public ConsoleCommanManager(){
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("send2User", new Send2UserCommand());
        consoleCommandMap.put("logout", new LogoutCommand());
        consoleCommandMap.put("createGroup", new CreateGroupCommand());
        consoleCommandMap.put("login", new LoginCommand());
    }
    @Override
    public void exce(Scanner scanner, Channel channel) {
        String next = scanner.next();
        if (consoleCommandMap.get(next) != null) {
            ((CreateGroupCommand) consoleCommandMap.get(next)).exce(scanner, channel);
        }else {
            System.out.println("无法识别[" + next + "]指令");
        }

    }

}
