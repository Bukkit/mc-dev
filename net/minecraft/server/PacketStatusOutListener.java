package net.minecraft.server;

public interface PacketStatusOutListener extends PacketListener {

    void a(PacketStatusOutServerInfo packetstatusoutserverinfo);

    void a(PacketStatusOutPong packetstatusoutpong);
}
