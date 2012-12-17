package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;

public class Packet24MobSpawn extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public byte i;
    public byte j;
    public byte k;
    private DataWatcher s;
    private List t;

    public Packet24MobSpawn() {}

    public Packet24MobSpawn(EntityLiving entityliving) {
        this.a = entityliving.id;
        this.b = (byte) EntityTypes.a(entityliving);
        this.c = entityliving.as.a(entityliving.locX);
        this.d = MathHelper.floor(entityliving.locY * 32.0D);
        this.e = entityliving.as.a(entityliving.locZ);
        this.i = (byte) ((int) (entityliving.yaw * 256.0F / 360.0F));
        this.j = (byte) ((int) (entityliving.pitch * 256.0F / 360.0F));
        this.k = (byte) ((int) (entityliving.az * 256.0F / 360.0F));
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
        this.s = entityliving.getDataWatcher();
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readByte() & 255;
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readInt();
        this.i = datainputstream.readByte();
        this.j = datainputstream.readByte();
        this.k = datainputstream.readByte();
        this.f = datainputstream.readShort();
        this.g = datainputstream.readShort();
        this.h = datainputstream.readShort();
        this.t = DataWatcher.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.b & 255);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeInt(this.e);
        dataoutputstream.writeByte(this.i);
        dataoutputstream.writeByte(this.j);
        dataoutputstream.writeByte(this.k);
        dataoutputstream.writeShort(this.f);
        dataoutputstream.writeShort(this.g);
        dataoutputstream.writeShort(this.h);
        this.s.a(dataoutputstream);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 26;
    }
}
