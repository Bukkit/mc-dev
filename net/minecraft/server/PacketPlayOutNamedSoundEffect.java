package net.minecraft.server;

import net.minecraft.util.org.apache.commons.lang3.Validate;

public class PacketPlayOutNamedSoundEffect extends Packet {

    private String a;
    private int b;
    private int c = Integer.MAX_VALUE;
    private int d;
    private float e;
    private int f;

    public PacketPlayOutNamedSoundEffect() {}

    public PacketPlayOutNamedSoundEffect(String s, double d0, double d1, double d2, float f, float f1) {
        Validate.notNull(s, "name", new Object[0]);
        this.a = s;
        this.b = (int) (d0 * 8.0D);
        this.c = (int) (d1 * 8.0D);
        this.d = (int) (d2 * 8.0D);
        this.e = f;
        this.f = (int) (f1 * 63.0F);
        if (this.f < 0) {
            this.f = 0;
        }

        if (this.f > 255) {
            this.f = 255;
        }
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c(256);
        this.b = packetdataserializer.readInt();
        this.c = packetdataserializer.readInt();
        this.d = packetdataserializer.readInt();
        this.e = packetdataserializer.readFloat();
        this.f = packetdataserializer.readUnsignedByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.writeInt(this.b);
        packetdataserializer.writeInt(this.c);
        packetdataserializer.writeInt(this.d);
        packetdataserializer.writeFloat(this.e);
        packetdataserializer.writeByte(this.f);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
