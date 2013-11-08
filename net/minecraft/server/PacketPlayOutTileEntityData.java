package net.minecraft.server;

public class PacketPlayOutTileEntityData extends Packet {

    private int a;
    private int b;
    private int c;
    private int d;
    private NBTTagCompound e;

    public PacketPlayOutTileEntityData() {}

    public PacketPlayOutTileEntityData(int i, int j, int k, int l, NBTTagCompound nbttagcompound) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
        this.e = nbttagcompound;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readInt();
        this.b = packetdataserializer.readShort();
        this.c = packetdataserializer.readInt();
        this.d = packetdataserializer.readUnsignedByte();
        this.e = packetdataserializer.b();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a);
        packetdataserializer.writeShort(this.b);
        packetdataserializer.writeInt(this.c);
        packetdataserializer.writeByte((byte) this.d);
        packetdataserializer.a(this.e);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
