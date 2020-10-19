package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class NettyClient {

    /*
     * 服务器端口号
     */
    private int port;

    /*
     * 服务器IP
     */
    private String host;

    public NettyClient(int port, String host) throws InterruptedException {
        this.port = port;
        this.host = host;
        start();
    }

    private void start() throws InterruptedException {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.group(eventLoopGroup);
            bootstrap.remoteAddress(host, port);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel)
                                throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new IdleStateHandler(0, 5, 5, TimeUnit.SECONDS));
                    pipeline.addLast(new LengthFieldPrepender(4));
                    pipeline.addLast(new ProtostuffEncoder());
                    pipeline.addLast(new NettyClientHandler());
                }
            });
            Channel channel = bootstrap.connect(host, port).sync().channel();
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            while(true){
//                channel.writeAndFlush(new registerdMsg(in.readLine()));
//            }
            Thread.sleep(5000);
            channel.close();
//      ClientJob.test();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        NettyClient client = new NettyClient(10086, "localhost");

    }
}
