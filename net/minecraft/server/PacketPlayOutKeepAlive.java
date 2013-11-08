package net.minecraft.server;

public class PacketPlayOutKeepAlive extends Packet {

    private int a;

    public PacketPlayOutKeepAlive() {}

    public PacketPlayOutKeepAlive(int i) {
        this.a = i;
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readInt();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a);
    }

    public boolean a() {
        return true;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
