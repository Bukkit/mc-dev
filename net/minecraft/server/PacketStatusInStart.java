package net.minecraft.server;

public class PacketStatusInStart extends Packet {

    public PacketStatusInStart() {}

    public void a(PacketDataSerializer packetdataserializer) {}

    public void b(PacketDataSerializer packetdataserializer) {}

    public void a(PacketStatusInListener packetstatusinlistener) {
        packetstatusinlistener.a(this);
    }

    public boolean a() {
        return true;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketStatusInListener) packetlistener);
    }
}
