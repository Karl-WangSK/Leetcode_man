package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

public class NettyServerHandler extends ChannelHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
    private static int missingTime= 0;
    public static Boolean flag = true;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String hostAddress = socketAddress.getAddress().getHostAddress();
        System.out.println(hostAddress + socketAddress.getPort());
        registerdMsg info = (registerdMsg) msg;

        if (info.getInfo().equals("success")) {
            flag= false;
            System.err.println("服务器接收到客户端消息：" + info.toString());
        }else if (info.getInfo().equals("close")){
            synchronized (Test.lock) {
                System.err.println("客户端关闭成功");
                flag= true;
                Test.lock.notify();
                ctx.close();
            }
        }
        try {
            ctx.writeAndFlush(getSendByteBuf("收到"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        System.err.println("客户端关闭成功");
        registerdMsg worker1 = new registerdMsg("close");
        ctx.writeAndFlush(worker1).addListener(ChannelFutureListener.CLOSE);
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        synchronized (Test.lock) {
            System.err.println("客户端关闭成功");
            flag= true;
            Test.lock.notify();
            ctx.close();
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("已经10秒未收到客户端的消息了！");
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                if (missingTime>2){
                    flag= true;
                    System.err.println("close");
                    ctx.close();
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }

    }

    /*
     * 从ByteBuf中获取信息 使用UTF-8编码返回
     */
    private String getMessage(ByteBuf buf) {

        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ByteBuf getSendByteBuf(String message)
            throws UnsupportedEncodingException {

        byte[] req = message.getBytes("UTF-8");
        ByteBuf pingMessage = Unpooled.buffer();
        pingMessage.writeBytes(req);

        return pingMessage;
    }


}