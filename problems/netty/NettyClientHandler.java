package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import static java.lang.Thread.sleep;

public class NettyClientHandler extends ChannelHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);


    @Override
    public void channelActive(ChannelHandlerContext ctx){
        registerdMsg worker1 = new registerdMsg("success");
        ctx.writeAndFlush(worker1);
        System.err.println("客户端发送成功");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {
        ByteBuf buf = (ByteBuf) msg;
        String rev = getMessage(buf);
        if (rev.equals("开始吧")){
            System.out.println("开始了！！！");
            Thread.sleep(3000);
            ClientJob.canStart= true;
        }
        System.err.println("客户端收到服务器消息:" + rev);
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        System.err.println("客户端关闭成功");
        registerdMsg worker1 = new registerdMsg("close");
        ctx.writeAndFlush(worker1);
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt){
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state()== IdleState.WRITER_IDLE){
                registerdMsg worker1 = new registerdMsg("worker1");
                ctx.writeAndFlush(worker1);
            }
        }
        System.out.println("heartbeat");
    }

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


}
