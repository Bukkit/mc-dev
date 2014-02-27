package net.minecraft.server;

import java.util.List;

public class PacketPlayOutSpawnEntityLiving extends Packet {

    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private byte i;
    private byte j;
    private byte k;
    private DataWatcher l;
    private List m;

    public PacketPlayOutSpawnEntityLiving() {}

    public PacketPlayOutSpawnEntityLiving(EntityLiving entityliving) {
        this.a = entityliving.getId();
        this.b = (byte) EntityTypes.a(entityliving);
        this.c = entityliving.as.a(entityliving.locX);
        this.d = MathHelper.floor(entityliving.locY * 32.0D);
        this.e = entityliving.as.a(entityliving.locZ);
        this.i = (byte) ((int) (entityliving.yaw * 256.0F / 360.0F));
        this.j = (byte) ((int) (entityliving.pitch * 256.0F / 360.0F));
        this.k = (byte) ((int) (entityliving.aO * 256.0F / 360.0F));
        double d0 = 3.9D;
        double d1 = entityliving.motX;
        double d2 = entityliving.motY;
        double d3 = entityliving.motZ;

        if (d1 < -d0) {
            d1 = -d0;
        }

        if (d2 < -d0) {
            d2 = -d0;
        }

        if (d3 < -d0) {
            d3 = -d0;
        }

        if (d1 > d0) {
            d1 = d0;
        }

        if (d2 > d0) {
            d2 = d0;
        }

        if (d3 > d0) {
            d3 = d0;
        }

        this.f = (int) (d1 * 8000.0D);
        this.g = (int) (d2 * 8000.0D);
        this.h = (int) (d3 * 8000.0D);
        this.l = entityliving.getDataWatcher();
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.a();
        this.b = packetdataserializer.readByte() & 255;
        this.c = packetdataserializer.readInt();
        this.d = packetdataserializer.readInt();
        this.e = packetdataserializer.readInt();
        this.i = packetdataserializer.readByte();
        this.j = packetdataserializer.readByte();
        this.k = packetdataserializer.readByte();
        this.f = packetdataserializer.readShort();
        this.g = packetdataserializer.readShort();
        this.h = packetdataserializer.readShort();
        this.m = DataWatcher.b(packetdataserializer);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        packetdataserializer.writeByte(this.b & 255);
        packetdataserializer.writeInt(this.c);
        packetdataserializer.writeInt(this.d);
        packetdataserializer.writeInt(this.e);
        packetdataserializer.writeByte(this.i);
        packetdataserializer.writeByte(this.j);
        packetdataserializer.writeByte(this.k);
        packetdataserializer.writeShort(this.f);
        packetdataserializer.writeShort(this.g);
        packetdataserializer.writeShort(this.h);
        this.l.a(packetdataserializer);
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public String b() {
        return String.format("id=%d, type=%d, x=%.2f, y=%.2f, z=%.2f, xd=%.2f, yd=%.2f, zd=%.2f", new Object[] { Integer.valueOf(this.a), Integer.valueOf(this.b), Float.valueOf((float) this.c / 32.0F), Float.valueOf((float) this.d / 32.0F), Float.valueOf((float) this.e / 32.0F), Float.valueOf((float) this.f / 8000.0F), Float.valueOf((float) this.g / 8000.0F), Float.valueOf((float) this.h / 8000.0F)});
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
