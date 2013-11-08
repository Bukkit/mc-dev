package net.minecraft.server;

public class PacketPlayInWindowClick extends Packet {

    private int a;
    private int slot;
    private int button;
    private short d;
    private ItemStack item;
    private int shift;

    public PacketPlayInWindowClick() {}

    public void a(PacketPlayInListener packetplayinlistener) {
        packetplayinlistener.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readByte();
        this.slot = packetdataserializer.readShort();
        this.button = packetdataserializer.readByte();
        this.d = packetdataserializer.readShort();
        this.shift = packetdataserializer.readByte();
        this.item = packetdataserializer.c();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeShort(this.slot);
        packetdataserializer.writeByte(this.button);
        packetdataserializer.writeShort(this.d);
        packetdataserializer.writeByte(this.shift);
        packetdataserializer.a(this.item);
    }

    public String b() {
        return this.item != null ? String.format("id=%d, slot=%d, button=%d, type=%d, itemid=%d, itemcount=%d, itemaux=%d", new Object[] { Integer.valueOf(this.a), Integer.valueOf(this.slot), Integer.valueOf(this.button), Integer.valueOf(this.shift), Integer.valueOf(Item.b(this.item.getItem())), Integer.valueOf(this.item.count), Integer.valueOf(this.item.getData())}) : String.format("id=%d, slot=%d, button=%d, type=%d, itemid=-1", new Object[] { Integer.valueOf(this.a), Integer.valueOf(this.slot), Integer.valueOf(this.button), Integer.valueOf(this.shift)});
    }

    public int c() {
        return this.a;
    }

    public int d() {
        return this.slot;
    }

    public int e() {
        return this.button;
    }

    public short f() {
        return this.d;
    }

    public ItemStack g() {
        return this.item;
    }

    public int h() {
        return this.shift;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }
}
