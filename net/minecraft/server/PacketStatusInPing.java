package net.minecraft.server;

public class PacketStatusInPing extends Packet {

    private long a;

    public PacketStatusInPing() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readLong();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeLong(this.a);
    }

    public void a(PacketStatusInListener packetstatusinlistener) {
        packetstatusinlistener.a(this);
    }

    public boolean a() {
        return true;
    }

    public long c() {
        return this.a;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketStatusInListener) packetlistener);
    }
}
