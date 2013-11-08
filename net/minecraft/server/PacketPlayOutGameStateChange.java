package net.minecraft.server;

public class PacketPlayOutGameStateChange extends Packet {

    public static final String[] a = new String[] { "tile.bed.notValid", null, null, "gameMode.changed"};
    private int b;
    private float c;

    public PacketPlayOutGameStateChange() {}

    public PacketPlayOutGameStateChange(int i, float f) {
        this.b = i;
        this.c = f;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.b = packetdataserializer.readUnsignedByte();
        this.c = packetdataserializer.readFloat();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.b);
        packetdataserializer.writeFloat(this.c);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
