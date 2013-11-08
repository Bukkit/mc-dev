package net.minecraft.server;

public class PacketPlayOutUpdateTime extends Packet {

    private long a;
    private long b;

    public PacketPlayOutUpdateTime() {}

    public PacketPlayOutUpdateTime(long i, long j, boolean flag) {
        this.a = i;
        this.b = j;
        if (!flag) {
            this.b = -this.b;
            if (this.b == 0L) {
                this.b = -1L;
            }
        }
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readLong();
        this.b = packetdataserializer.readLong();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeLong(this.a);
        packetdataserializer.writeLong(this.b);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public String b() {
        return String.format("time=%d,dtime=%d", new Object[] { Long.valueOf(this.a), Long.valueOf(this.b)});
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
