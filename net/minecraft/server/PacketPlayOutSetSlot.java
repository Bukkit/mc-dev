package net.minecraft.server;

public class PacketPlayOutSetSlot extends Packet {

    private int a;
    private int b;
    private ItemStack c;

    public PacketPlayOutSetSlot() {}

    public PacketPlayOutSetSlot(int i, int j, ItemStack itemstack) {
        this.a = i;
        this.b = j;
        this.c = itemstack == null ? null : itemstack.cloneItemStack();
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readByte();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.c();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.a(this.c);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
