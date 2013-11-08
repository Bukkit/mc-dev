package net.minecraft.server;

import net.minecraft.util.io.netty.util.concurrent.GenericFutureListener;

public class PacketStatusListener implements PacketStatusInListener {

    private final MinecraftServer minecraftServer;
    private final NetworkManager networkManager;

    public PacketStatusListener(MinecraftServer minecraftserver, NetworkManager networkmanager) {
        this.minecraftServer = minecraftserver;
        this.networkManager = networkmanager;
    }

    public void a(IChatBaseComponent ichatbasecomponent) {}

    public void a(EnumProtocol enumprotocol, EnumProtocol enumprotocol1) {
        if (enumprotocol1 != EnumProtocol.STATUS) {
            throw new UnsupportedOperationException("Unexpected change in protocol to " + enumprotocol1);
        }
    }

    public void a() {}

    public void a(PacketStatusInStart packetstatusinstart) {
        this.networkManager.handle(new PacketStatusOutServerInfo(this.minecraftServer.at()), new GenericFutureListener[0]);
    }

    public void a(PacketStatusInPing packetstatusinping) {
        this.networkManager.handle(new PacketStatusOutPong(packetstatusinping.c()), new GenericFutureListener[0]);
    }
}
