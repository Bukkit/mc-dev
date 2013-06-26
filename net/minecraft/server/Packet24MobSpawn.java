package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
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
    private DataWatcher t;
    private List u;

    public Packet24MobSpawn() {}

    public Packet24MobSpawn(EntityLiving entityliving) {
        this.a = entityliving.id;
        this.b = (byte) EntityTypes.a(entityliving);
        this.c = entityliving.at.a(entityliving.locX);
        this.d = MathHelper.floor(entityliving.locY * 32.0D);
        this.e = entityliving.at.a(entityliving.locZ);
        this.i = (byte) ((int) (entityliving.yaw * 256.0F / 360.0F));
        this.j = (byte) ((int) (entityliving.pitch * 256.0F / 360.0F));
        this.k = (byte) ((int) (entityliving.aP * 256.0F / 360.0F));
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
        this.t = entityliving.getDataWatcher();
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readByte() & 255;
        this.c = datainput.readInt();
        this.d = datainput.readInt();
        this.e = datainput.readInt();
        this.i = datainput.readByte();
        this.j = datainput.readByte();
        this.k = datainput.readByte();
        this.f = datainput.readShort();
        this.g = datainput.readShort();
        this.h = datainput.readShort();
        this.u = DataWatcher.a(datainput);
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeByte(this.b & 255);
        dataoutput.writeInt(this.c);
        dataoutput.writeInt(this.d);
        dataoutput.writeInt(this.e);
        dataoutput.writeByte(this.i);
        dataoutput.writeByte(this.j);
        dataoutput.writeByte(this.k);
        dataoutput.writeShort(this.f);
        dataoutput.writeShort(this.g);
        dataoutput.writeShort(this.h);
        this.t.a(dataoutput);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 26;
    }
}
