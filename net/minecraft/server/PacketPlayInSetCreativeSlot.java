package net.minecraft.server;

public class PacketPlayInSetCreativeSlot extends Packet {

    private int slot;
    private ItemStack b;

    public PacketPlayInSetCreativeSlot() {}

    public void a(PacketPlayInListener packetplayinlistener) {
        packetplayinlistener.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.slot = packetdataserializer.readShort();
        this.b = packetdataserializer.c();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeShort(this.slot);
        packetdataserializer.a(this.b);
    }

    public int c() {
        return this.slot;
    }

    public ItemStack getItemStack() {
        return this.b;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }
}
