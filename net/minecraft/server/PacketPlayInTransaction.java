package net.minecraft.server;

public class PacketPlayInTransaction extends Packet {

    private int a;
    private short b;
    private boolean c;

    public PacketPlayInTransaction() {}

    public void a(PacketPlayInListener packetplayinlistener) {
        packetplayinlistener.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readByte();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.readByte() != 0;
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.writeByte(this.c ? 1 : 0);
    }

    public String b() {
        return String.format("id=%d, uid=%d, accepted=%b", new Object[] { Integer.valueOf(this.a), Short.valueOf(this.b), Boolean.valueOf(this.c)});
    }

    public int c() {
        return this.a;
    }

    public short d() {
        return this.b;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }
}
