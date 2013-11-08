package net.minecraft.server;

public class PacketPlayOutHeldItemSlot extends Packet {

    private int a;

    public PacketPlayOutHeldItemSlot() {}

    public PacketPlayOutHeldItemSlot(int i) {
        this.a = i;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
