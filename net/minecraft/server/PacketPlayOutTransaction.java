package net.minecraft.server;

public class PacketPlayOutTransaction extends Packet {

    private int a;
    private short b;
    private boolean c;

    public PacketPlayOutTransaction() {}

    public PacketPlayOutTransaction(int i, short short1, boolean flag) {
        this.a = i;
        this.b = short1;
        this.c = flag;
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readUnsignedByte();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.writeBoolean(this.c);
    }

    public String b() {
        return String.format("id=%d, uid=%d, accepted=%b", new Object[] { Integer.valueOf(this.a), Short.valueOf(this.b), Boolean.valueOf(this.c)});
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
