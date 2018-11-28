package server;

import codec.PacketDecoder;
import codec.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import server.hanlder.LoginRequestHandler;
import server.hanlder.MessageRequestHandler;

/**
 * Created by chen on 2018/11/19.
 */
public class ServerNettyDemo {

        public static void main(String[] args) {
        ServerBootstrap bootstrap=new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new PacketDecoder());
                        nioSocketChannel.pipeline().addLast(new LoginRequestHandler());
                        nioSocketChannel.pipeline().addLast(new MessageRequestHandler());
                        nioSocketChannel.pipeline().addLast(new PacketEncoder());
                    }
                }).bind(8000);
    }
}