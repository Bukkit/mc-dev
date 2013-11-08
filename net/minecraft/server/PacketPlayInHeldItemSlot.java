package net.minecraft.server;

public class PacketPlayInHeldItemSlot extends Packet {

    private int itemInHandIndex;

    public PacketPlayInHeldItemSlot() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.itemInHandIndex = packetdataserializer.readShort();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeShort(this.itemInHandIndex);
    }

    public void a(PacketPlayInListener packetplayinlistener) {
        packetplayinlistener.a(this);
    }

    public int c() {
        return this.itemInHandIndex;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }
}
