package net.minecraft.server;

import net.minecraft.util.io.netty.channel.Channel;
import net.minecraft.util.io.netty.channel.ChannelException;
import net.minecraft.util.io.netty.channel.ChannelInitializer;
import net.minecraft.util.io.netty.channel.ChannelOption;
import net.minecraft.util.io.netty.handler.timeout.ReadTimeoutHandler;

class ServerConnectionChannel extends ChannelInitializer {

    final ServerConnection a;

    ServerConnectionChannel(ServerConnection serverconnection) {
        this.a = serverconnection;
    }

    protected void initChannel(Channel channel) {
        try {
            channel.config().setOption(ChannelOption.IP_TOS, Integer.valueOf(24));
        } catch (ChannelException channelexception) {
            ;
        }

        try {
            channel.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(false));
        } catch (ChannelException channelexception1) {
            ;
        }

        channel.pipeline().addLast("timeout", new ReadTimeoutHandler(30)).addLast("legacy_query", new LegacyPingHandler(this.a)).addLast("splitter", new PacketSplitter()).addLast("decoder", new PacketDecoder(NetworkManager.h)).addLast("prepender", new PacketPrepender()).addLast("encoder", new PacketEncoder(NetworkManager.h));
        NetworkManager networkmanager = new NetworkManager(false);

        ServerConnection.a(this.a).add(networkmanager);
        channel.pipeline().addLast("packet_handler", networkmanager);
        networkmanager.a((PacketListener) (new HandshakeListener(ServerConnection.b(this.a), networkmanager)));
    }
}
