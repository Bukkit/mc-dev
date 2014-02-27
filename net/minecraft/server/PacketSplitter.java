package net.minecraft.server;

import java.util.List;

import net.minecraft.util.io.netty.buffer.ByteBuf;
import net.minecraft.util.io.netty.buffer.Unpooled;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.handler.codec.ByteToMessageDecoder;
import net.minecraft.util.io.netty.handler.codec.CorruptedFrameException;

public class PacketSplitter extends ByteToMessageDecoder {

    public PacketSplitter() {}

    protected void decode(ChannelHandlerContext channelhandlercontext, ByteBuf bytebuf, List list) {
        bytebuf.markReaderIndex();
        byte[] abyte = new byte[3];

        for (int i = 0; i < abyte.length; ++i) {
            if (!bytebuf.isReadable()) {
                bytebuf.resetReaderIndex();
                return;
            }

            abyte[i] = bytebuf.readByte();
            if (abyte[i] >= 0) {
                PacketDataSerializer packetdataserializer = new PacketDataSerializer(Unpooled.wrappedBuffer(abyte));

                try {
                    int j = packetdataserializer.a();

                    if (bytebuf.readableBytes() < j) {
                        bytebuf.resetReaderIndex();
                        return;
                    }

                    list.add(bytebuf.readBytes(j));
                } finally {
                    packetdataserializer.release();
                }

                return;
            }
        }

        throw new CorruptedFrameException("length wider than 21-bit");
    }
}
