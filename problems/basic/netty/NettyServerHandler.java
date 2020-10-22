package basic.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
    public static Boolean flag = true;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String hostAddress = socketAddress.getAddress().getHostAddress();
        System.out.println(hostAddress + socketAddress.getPort());
        RpcMsg info = (RpcMsg) msg;

        try {
            if (info instanceof registerdMsg) {
                synchronized (Test.isrunning) {
                    flag = false;
                    Test.isrunning.wait();
                    ctx.writeAndFlush(new registerdMsg("开始吧"));
                    System.err.println("服务器接收到客户端消息：" + info.toString());
                }
            } else if (info instanceof HeartBeat) {
                System.out.println("heart beat");
            }else if (info instanceof ShutDown) {
                synchronized (Test.lock) {
                    System.err.println("解锁");
                    flag = true;
                    Test.lock.notify();
                    ctx.close();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        ctx.writeAndFlush(getSendByteBuf("first time"));
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        synchronized (Test.lock) {
            System.err.println("客户端异常关闭");
            flag = true;
            Test.lock.notify();
            ctx.close();
        }
    }

//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        System.out.println("已经10秒未收到客户端的消息了！");
//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent event = (IdleStateEvent) evt;
//            if (event.state() == IdleState.READER_IDLE) {
//                if (missingTime>2){
//                    flag= true;
//                    System.err.println("close");
//                    ctx.close();
//                }
//            }
//        } else {
//            super.userEventTriggered(ctx, evt);
//        }
//
//    }


    private ByteBuf getSendByteBuf(String message)
                    throws UnsupportedEncodingException {

        byte[] req = message.getBytes("UTF-8");
        ByteBuf pingMessage = Unpooled.buffer();
        pingMessage.writeBytes(req);

        return pingMessage;
    }


}