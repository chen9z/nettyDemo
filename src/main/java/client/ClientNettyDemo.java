package client;

import client.console.ConsoleCommanManager;
import client.console.LoginCommand;
import server.hanlder.CreateGroupResponseHandler;
import client.handler.LoginResponseHandler;
import client.handler.LogoutResponseHandler;
import client.handler.MessageResponseHandler;
import codec.PacketDecoder;
import codec.PacketEncoder;
import command.Spliter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import util.SessionUtils;

import java.util.Scanner;

/**
 * Created by chen on 2018/11/19.
 */
public class ClientNettyDemo {
    public static void main(String[] args) throws InterruptedException {

        Bootstrap bootstrap=new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new Spliter());
                        channel.pipeline().addLast(new PacketDecoder());
                        channel.pipeline().addLast(new LoginResponseHandler());
                        channel.pipeline().addLast(new LogoutResponseHandler());
                        channel.pipeline().addLast(new CreateGroupResponseHandler());
                        channel.pipeline().addLast(new MessageResponseHandler());
                        channel.pipeline().addLast(new PacketEncoder());
                    }
                });
        bindPort(bootstrap,"127.0.0.1",8000);

    }

    public static void bindPort(Bootstrap bootstrap,String addr,Integer port) {
        bootstrap.connect(addr,port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
                startConsoleThread(((ChannelFuture) future).channel());
            }else {
                bindPort(bootstrap,addr,port+1);
            }
        });
    }


    private static void startConsoleThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        LoginCommand loginCommand = new LoginCommand();
        ConsoleCommanManager consoleCommanManager = new ConsoleCommanManager();
        new Thread(()->{
            while (!Thread.interrupted()) {
                if (SessionUtils.hasLogin(channel)) {
                    consoleCommanManager.exce(scanner,channel);
                } else {
                    loginCommand.exce(scanner,channel);
                }
            }
        }).start();
    }

}
