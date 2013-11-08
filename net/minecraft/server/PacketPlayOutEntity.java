package net.minecraft.server;

public class PacketPlayOutEntity extends Packet {

    protected int a;
    protected byte b;
    protected byte c;
    protected byte d;
    protected byte e;
    protected byte f;
    protected boolean g;

    public PacketPlayOutEntity() {}

    public PacketPlayOutEntity(int i) {
        this.a = i;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readInt();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public String b() {
        return String.format("id=%d", new Object[] { Integer.valueOf(this.a)});
    }

    public String toString() {
        return "Entity_" + super.toString();
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
