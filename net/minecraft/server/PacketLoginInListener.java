package net.minecraft.server;

public interface PacketLoginInListener extends PacketListener {

    void a(PacketLoginInStart packetlogininstart);

    void a(PacketLoginInEncryptionBegin packetlogininencryptionbegin);
}
