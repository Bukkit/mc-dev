package net.minecraft.server;

public class PacketPlayOutWindowData extends Packet {

    private int a;
    private int b;
    private int c;

    public PacketPlayOutWindowData() {}

    public PacketPlayOutWindowData(int i, int j, int k) {
        this.a = i;
        this.b = j;
        this.c = k;
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readUnsignedByte();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.readShort();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.writeShort(this.c);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
