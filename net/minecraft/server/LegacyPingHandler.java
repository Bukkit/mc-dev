package net.minecraft.server;

import java.net.InetSocketAddress;

import net.minecraft.util.com.google.common.base.Charsets;
import net.minecraft.util.io.netty.buffer.ByteBuf;
import net.minecraft.util.io.netty.buffer.Unpooled;
import net.minecraft.util.io.netty.channel.ChannelFutureListener;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LegacyPingHandler extends ChannelInboundHandlerAdapter {

    private static final Logger a = LogManager.getLogger();
    private ServerConnection b;

    public LegacyPingHandler(ServerConnection serverconnection) {
        this.b = serverconnection;
    }

    public void channelRead(ChannelHandlerContext channelhandlercontext, Object object) {
        ByteBuf bytebuf = (ByteBuf) object;

        bytebuf.markReaderIndex();
        boolean flag = true;

        try {
            if (bytebuf.readUnsignedByte() != 254) {
                return;
            }

            InetSocketAddress inetsocketaddress = (InetSocketAddress) channelhandlercontext.channel().remoteAddress();
            MinecraftServer minecraftserver = this.b.d();
            int i = bytebuf.readableBytes();
            String s;

            switch (i) {
            case 0:
                a.debug("Ping: (<1.3.x) from {}:{}", new Object[] { inetsocketaddress.getAddress(), Integer.valueOf(inetsocketaddress.getPort())});
                s = String.format("%s�%d�%d", new Object[] { minecraftserver.getMotd(), Integer.valueOf(minecraftserver.C()), Integer.valueOf(minecraftserver.D())});
                this.a(channelhandlercontext, this.a(s));
                break;

            case 1:
                if (bytebuf.readUnsignedByte() != 1) {
                    return;
                }

                a.debug("Ping: (1.4-1.5.x) from {}:{}", new Object[] { inetsocketaddress.getAddress(), Integer.valueOf(inetsocketaddress.getPort())});
                s = String.format("�1, new Object[] { Integer.valueOf(127), minecraftserver.getVersion(), minecraftserver.getMotd(), Integer.valueOf(minecraftserver.C()), Integer.valueOf(minecraftserver.D())});
                this.a(channelhandlercontext, this.a(s));
                break;

            default:
                boolean flag1 = bytebuf.readUnsignedByte() == 1;

                flag1 &= bytebuf.readUnsignedByte() == 250;
                flag1 &= "MC|PingHost".equals(new String(bytebuf.readBytes(bytebuf.readShort() * 2).array(), Charsets.UTF_16BE));
                int j = bytebuf.readUnsignedShort();

                flag1 &= bytebuf.readUnsignedByte() >= 73;
                flag1 &= 3 + bytebuf.readBytes(bytebuf.readShort() * 2).array().length + 4 == j;
                flag1 &= bytebuf.readInt() <= '\uffff';
                flag1 &= bytebuf.readableBytes() == 0;
                if (!flag1) {
                    return;
                }

                a.debug("Ping: (1.6) from {}:{}", new Object[] { inetsocketaddress.getAddress(), Integer.valueOf(inetsocketaddress.getPort())});
                String s1 = String.format("�1, new Object[] { Integer.valueOf(127), minecraftserver.getVersion(), minecraftserver.getMotd(), Integer.valueOf(minecraftserver.C()), Integer.valueOf(minecraftserver.D())});
                ByteBuf bytebuf1 = this.a(s1);

                try {
                    this.a(channelhandlercontext, bytebuf1);
                } finally {
                    bytebuf1.release();
                }
            }

            bytebuf.release();
            flag = false;
        } catch (RuntimeException runtimeexception) {
            ;
        } finally {
            if (flag) {
                bytebuf.resetReaderIndex();
                channelhandlercontext.channel().pipeline().remove("legacy_query");
                channelhandlercontext.fireChannelRead(object);
            }
        }
    }

    private void a(ChannelHandlerContext channelhandlercontext, ByteBuf bytebuf) {
        channelhandlercontext.pipeline().firstContext().writeAndFlush(bytebuf).addListener(ChannelFutureListener.CLOSE);
    }

    private ByteBuf a(String s) {
        ByteBuf bytebuf = Unpooled.buffer();

        bytebuf.writeByte(255);
        char[] achar = s.toCharArray();

        bytebuf.writeShort(achar.length);
        char[] achar1 = achar;
        int i = achar.length;

        for (int j = 0; j < i; ++j) {
            char c0 = achar1[j];

            bytebuf.writeChar(c0);
        }

        return bytebuf;
    }
}
