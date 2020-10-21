package basic.netty;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ProtostuffEncoder extends MessageToByteEncoder<registerdMsg> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, registerdMsg msg, ByteBuf byteBuf) throws Exception {
        LinkedBuffer buffer = LinkedBuffer.allocate(1024);
        Schema<registerdMsg> schema = RuntimeSchema.getSchema(registerdMsg.class);
        byte[] array = ProtobufIOUtil.toByteArray(msg, schema, buffer);
        byteBuf.writeBytes(array);
    }
}