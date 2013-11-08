package net.minecraft.server;

public class PacketPlayOutEntityEquipment extends Packet {

    private int a;
    private int b;
    private ItemStack c;

    public PacketPlayOutEntityEquipment() {}

    public PacketPlayOutEntityEquipment(int i, int j, ItemStack itemstack) {
        this.a = i;
        this.b = j;
        this.c = itemstack == null ? null : itemstack.cloneItemStack();
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readInt();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.c();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.a(this.c);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public String b() {
        return String.format("entity=%d, slot=%d, item=%s", new Object[] { Integer.valueOf(this.a), Integer.valueOf(this.b), this.c});
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
