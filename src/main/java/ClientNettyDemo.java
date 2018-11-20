import handler.FirstClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by chen on 2018/11/19.
 */
public class ClientNettyDemo {
    public static void main(String[] args) throws InterruptedException {

        Bootstrap bootstrap=new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new FirstClientHandler());
                    }
                });
        bindPort(bootstrap,"127.0.0.1",8000);

    }

    public static void bindPort(Bootstrap bootstrap,String addr,Integer port) {
        bootstrap.connect(addr,port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            }else {
                bindPort(bootstrap,addr,port+1);
            }
        });
    }
}
