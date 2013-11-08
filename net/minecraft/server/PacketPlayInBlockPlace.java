package net.minecraft.server;

public class PacketPlayInBlockPlace extends Packet {

    private int a;
    private int b;
    private int c;
    private int d;
    private ItemStack e;
    private float f;
    private float g;
    private float h;

    public PacketPlayInBlockPlace() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readInt();
        this.b = packetdataserializer.readUnsignedByte();
        this.c = packetdataserializer.readInt();
        this.d = packetdataserializer.readUnsignedByte();
        this.e = packetdataserializer.c();
        this.f = (float) packetdataserializer.readUnsignedByte() / 16.0F;
        this.g = (float) packetdataserializer.readUnsignedByte() / 16.0F;
        this.h = (float) packetdataserializer.readUnsignedByte() / 16.0F;
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a);
        packetdataserializer.writeByte(this.b);
        packetdataserializer.writeInt(this.c);
        packetdataserializer.writeByte(this.d);
        packetdataserializer.a(this.e);
        packetdataserializer.writeByte((int) (this.f * 16.0F));
        packetdataserializer.writeByte((int) (this.g * 16.0F));
        packetdataserializer.writeByte((int) (this.h * 16.0F));
    }

    public void a(PacketPlayInListener packetplayinlistener) {
        packetplayinlistener.a(this);
    }

    public int c() {
        return this.a;
    }

    public int d() {
        return this.b;
    }

    public int e() {
        return this.c;
    }

    public int getFace() {
        return this.d;
    }

    public ItemStack getItemStack() {
        return this.e;
    }

    public float h() {
        return this.f;
    }

    public float i() {
        return this.g;
    }

    public float j() {
        return this.h;
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayInListener) packetlistener);
    }
}
