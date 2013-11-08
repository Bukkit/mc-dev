package net.minecraft.server;

import javax.crypto.Cipher;

import net.minecraft.util.io.netty.buffer.ByteBuf;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncrypter extends MessageToByteEncoder {

    private final PacketEncryptionHandler a;

    public PacketEncrypter(Cipher cipher) {
        this.a = new PacketEncryptionHandler(cipher);
    }

    protected void a(ChannelHandlerContext channelhandlercontext, ByteBuf bytebuf, ByteBuf bytebuf1) {
        this.a.a(bytebuf, bytebuf1);
    }

    protected void encode(ChannelHandlerContext channelhandlercontext, Object object, ByteBuf bytebuf) {
        this.a(channelhandlercontext, (ByteBuf) object, bytebuf);
    }
}
