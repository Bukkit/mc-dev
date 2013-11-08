package net.minecraft.server;

public interface PacketStatusInListener extends PacketListener {

    void a(PacketStatusInPing packetstatusinping);

    void a(PacketStatusInStart packetstatusinstart);
}
