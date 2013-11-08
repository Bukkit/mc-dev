package net.minecraft.server;

public class PacketStatusOutPong extends Packet {

    private long a;

    public PacketStatusOutPong() {}

    public PacketStatusOutPong(long i) {
        this.a = i;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readLong();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeLong(this.a);
    }

    public void a(PacketStatusOutListener packetstatusoutlistener) {
        packetstatusoutlistener.a(this);
    }

    public boolean a() {
        return true;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketStatusOutListener) packetlistener);
    }
}
