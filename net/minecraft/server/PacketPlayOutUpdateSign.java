package net.minecraft.server;

public class PacketPlayOutUpdateSign extends Packet {

    private int x;
    private int y;
    private int z;
    private String[] lines;

    public PacketPlayOutUpdateSign() {}

    public PacketPlayOutUpdateSign(int i, int j, int k, String[] astring) {
        this.x = i;
        this.y = j;
        this.z = k;
        this.lines = new String[] { astring[0], astring[1], astring[2], astring[3]};
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.x = packetdataserializer.readInt();
        this.y = packetdataserializer.readShort();
        this.z = packetdataserializer.readInt();
        this.lines = new String[4];

        for (int i = 0; i < 4; ++i) {
            this.lines[i] = packetdataserializer.c(15);
        }
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.x);
        packetdataserializer.writeShort(this.y);
        packetdataserializer.writeInt(this.z);

        for (int i = 0; i < 4; ++i) {
            packetdataserializer.a(this.lines[i]);
        }
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
