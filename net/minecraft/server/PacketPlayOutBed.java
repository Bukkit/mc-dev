package net.minecraft.server;

public class PacketPlayOutBed extends Packet {

    private int a;
    private int b;
    private int c;
    private int d;

    public PacketPlayOutBed() {}

    public PacketPlayOutBed(EntityHuman entityhuman, int i, int j, int k) {
        this.b = i;
        this.c = j;
        this.d = k;
        this.a = entityhuman.getId();
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readInt();
        this.b = packetdataserializer.readInt();
        this.c = packetdataserializer.readByte();
        this.d = packetdataserializer.readInt();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a);
        packetdataserializer.writeInt(this.b);
        packetdataserializer.writeByte(this.c);
        packetdataserializer.writeInt(this.d);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
