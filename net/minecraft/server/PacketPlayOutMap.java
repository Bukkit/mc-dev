package net.minecraft.server;

public class PacketPlayOutMap extends Packet {

    private int a;
    private byte[] b;

    public PacketPlayOutMap() {}

    public PacketPlayOutMap(int i, byte[] abyte) {
        this.a = i;
        this.b = abyte;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.a();
        this.b = new byte[packetdataserializer.readUnsignedShort()];
        packetdataserializer.readBytes(this.b);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        packetdataserializer.writeShort(this.b.length);
        packetdataserializer.writeBytes(this.b);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public String b() {
        return String.format("id=%d, length=%d", new Object[] { Integer.valueOf(this.a), Integer.valueOf(this.b.length)});
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
