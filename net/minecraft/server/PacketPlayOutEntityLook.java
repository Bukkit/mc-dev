package net.minecraft.server;

public class PacketPlayOutEntityLook extends PacketPlayOutEntity {

    public PacketPlayOutEntityLook() {
        this.g = true;
    }

    public PacketPlayOutEntityLook(int i, byte b0, byte b1) {
        super(i);
        this.e = b0;
        this.f = b1;
        this.g = true;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        super.a(packetdataserializer);
        this.e = packetdataserializer.readByte();
        this.f = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        super.b(packetdataserializer);
        packetdataserializer.writeByte(this.e);
        packetdataserializer.writeByte(this.f);
    }

    public String b() {
        return super.b() + String.format(", yRot=%d, xRot=%d", new Object[] { Byte.valueOf(this.e), Byte.valueOf(this.f)});
    }

    public void handle(PacketListener packetlistener) {
        super.a((PacketPlayOutListener) packetlistener);
    }
}
