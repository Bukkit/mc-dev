package net.minecraft.server;

public class PacketPlayOutPlayerInfo extends Packet {

    private String a;
    private boolean b;
    private int c;

    public PacketPlayOutPlayerInfo() {}

    public PacketPlayOutPlayerInfo(String s, boolean flag, int i) {
        this.a = s;
        this.b = flag;
        this.c = i;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c(16);
        this.b = packetdataserializer.readBoolean();
        this.c = packetdataserializer.readShort();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.writeBoolean(this.b);
        packetdataserializer.writeShort(this.c);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
