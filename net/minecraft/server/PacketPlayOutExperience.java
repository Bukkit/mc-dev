package net.minecraft.server;

public class PacketPlayOutExperience extends Packet {

    private float a;
    private int b;
    private int c;

    public PacketPlayOutExperience() {}

    public PacketPlayOutExperience(float f, int i, int j) {
        this.a = f;
        this.b = i;
        this.c = j;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readFloat();
        this.c = packetdataserializer.readShort();
        this.b = packetdataserializer.readShort();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeFloat(this.a);
        packetdataserializer.writeShort(this.c);
        packetdataserializer.writeShort(this.b);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
