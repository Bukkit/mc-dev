package net.minecraft.server;

public class PacketPlayOutCloseWindow extends Packet {

    private int a;

    public PacketPlayOutCloseWindow() {}

    public PacketPlayOutCloseWindow(int i) {
        this.a = i;
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readUnsignedByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
