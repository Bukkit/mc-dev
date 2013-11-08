package net.minecraft.server;

public class PacketPlayOutRelEntityMoveLook extends PacketPlayOutEntity {

    public PacketPlayOutRelEntityMoveLook() {
        this.g = true;
    }

    public PacketPlayOutRelEntityMoveLook(int i, byte b0, byte b1, byte b2, byte b3, byte b4) {
        super(i);
        this.b = b0;
        this.c = b1;
        this.d = b2;
        this.e = b3;
        this.f = b4;
        this.g = true;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        super.a(packetdataserializer);
        this.b = packetdataserializer.readByte();
        this.c = packetdataserializer.readByte();
        this.d = packetdataserializer.readByte();
        this.e = packetdataserializer.readByte();
        this.f = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        super.b(packetdataserializer);
        packetdataserializer.writeByte(this.b);
        packetdataserializer.writeByte(this.c);
        packetdataserializer.writeByte(this.d);
        packetdataserializer.writeByte(this.e);
        packetdataserializer.writeByte(this.f);
    }

    public String b() {
        return super.b() + String.format(", xa=%d, ya=%d, za=%d, yRot=%d, xRot=%d", new Object[] { Byte.valueOf(this.b), Byte.valueOf(this.c), Byte.valueOf(this.d), Byte.valueOf(this.e), Byte.valueOf(this.f)});
    }

    public void handle(PacketListener packetlistener) {
        super.a((PacketPlayOutListener) packetlistener);
    }
}
