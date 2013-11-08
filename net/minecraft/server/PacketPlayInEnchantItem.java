package net.minecraft.server;

public class PacketPlayInEnchantItem extends Packet {

    private int a;
    private int b;

    public PacketPlayInEnchantItem() {}

    public void a(PacketPlayInListener packetplayinlistener) {
        packetplayinlistener.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readByte();
        this.b = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeByte(this.b);
    }

    public String b() {
        return String.format("id=%d, button=%d", new Object[] { Integer.valueOf(this.a), Integer.valueOf(this.b)});
    }

    public int c() {
        return this.a;
    }

    public int d() {
        return this.b;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }
}
