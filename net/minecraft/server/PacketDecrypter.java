package net.minecraft.server;

import java.util.List;
import javax.crypto.Cipher;

import net.minecraft.util.io.netty.buffer.ByteBuf;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.handler.codec.MessageToMessageDecoder;

public class PacketDecrypter extends MessageToMessageDecoder {

    private final PacketEncryptionHandler a;

    public PacketDecrypter(Cipher cipher) {
        this.a = new PacketEncryptionHandler(cipher);
    }

    protected void a(ChannelHandlerContext channelhandlercontext, ByteBuf bytebuf, List list) {
        list.add(this.a.a(channelhandlercontext, bytebuf));
    }

    protected void decode(ChannelHandlerContext channelhandlercontext, Object object, List list) {
        this.a(channelhandlercontext, (ByteBuf) object, list);
    }
}
