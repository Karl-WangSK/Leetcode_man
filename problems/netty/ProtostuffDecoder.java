package netty;

import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ProtostuffDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Schema<registerdMsg> schema = RuntimeSchema.getSchema(registerdMsg.class);
        registerdMsg person = schema.newMessage();
        byte[] array = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(array);
        ProtobufIOUtil.mergeFrom(array, person, schema);
        list.add(person);
    }
}