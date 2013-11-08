package net.minecraft.server;

import java.io.IOException;

import net.minecraft.util.com.google.common.collect.BiMap;
import net.minecraft.util.io.netty.buffer.ByteBuf;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.handler.codec.MessageToByteEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class PacketEncoder extends MessageToByteEncoder {

    private static final Logger a = LogManager.getLogger();
    private static final Marker b = MarkerManager.getMarker("PACKET_SENT", NetworkManager.b);

    public PacketEncoder() {}

    protected void a(ChannelHandlerContext channelhandlercontext, Packet packet, ByteBuf bytebuf) {
        Integer integer = (Integer) ((BiMap) channelhandlercontext.channel().attr(NetworkManager.e).get()).inverse().get(packet.getClass());

        if (a.isDebugEnabled()) {
            a.debug(b, "OUT: [{}:{}] {}[{}]", new Object[] { channelhandlercontext.channel().attr(NetworkManager.c).get(), integer, packet.getClass().getName(), packet.b()});
        }

        if (integer == null) {
            throw new IOException("Can\'t serialize unregistered packet");
        } else {
            PacketDataSerializer packetdataserializer = new PacketDataSerializer(bytebuf);

            packetdataserializer.b(integer.intValue());
            packet.b(packetdataserializer);
        }
    }

    protected void encode(ChannelHandlerContext channelhandlercontext, Object object, ByteBuf bytebuf) {
        this.a(channelhandlercontext, (Packet) object, bytebuf);
    }
}
