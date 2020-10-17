package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.io.UnsupportedEncodingException;

public class NettyClientHandler extends ChannelHandlerAdapter {
    private ByteBuf firstMessage;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        registerdMsg worker1 = new registerdMsg("sucess");
        ctx.writeAndFlush(worker1);
        System.err.println("客户端发送成功");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String rev = getMessage(buf);
        System.err.println("客户端收到服务器消息:" + rev);
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

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state()== IdleState.WRITER_IDLE){
                registerdMsg worker1 = new registerdMsg("worker1");
                ctx.writeAndFlush(worker1);
            }
        }
        System.out.println("heartbeat");
    }
}
