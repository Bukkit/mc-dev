package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet23VehicleSpawn extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;

    public Packet23VehicleSpawn() {}

    public Packet23VehicleSpawn(Entity entity, int i) {
        this(entity, i, 0);
    }

    public Packet23VehicleSpawn(Entity entity, int i, int j) {
        this.a = entity.id;
        this.b = MathHelper.floor(entity.locX * 32.0D);
        this.c = MathHelper.floor(entity.locY * 32.0D);
        this.d = MathHelper.floor(entity.locZ * 32.0D);
        this.h = MathHelper.d(entity.pitch * 256.0F / 360.0F);
        this.i = MathHelper.d(entity.yaw * 256.0F / 360.0F);
        this.j = i;
        this.k = j;
        if (j > 0) {
            double d0 = entity.motX;
            double d1 = entity.motY;
            double d2 = entity.motZ;
            double d3 = 3.9D;

            if (d0 < -d3) {
                d0 = -d3;
            }

            if (d1 < -d3) {
                d1 = -d3;
            }

            if (d2 < -d3) {
                d2 = -d3;
            }

            if (d0 > d3) {
                d0 = d3;
            }

            if (d1 > d3) {
                d1 = d3;
            }

            if (d2 > d3) {
                d2 = d3;
            }

            this.e = (int) (d0 * 8000.0D);
            this.f = (int) (d1 * 8000.0D);
            this.g = (int) (d2 * 8000.0D);
        }
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.j = datainput.readByte();
        this.b = datainput.readInt();
        this.c = datainput.readInt();
        this.d = datainput.readInt();
        this.h = datainput.readByte();
        this.i = datainput.readByte();
        this.k = datainput.readInt();
        if (this.k > 0) {
            this.e = datainput.readShort();
            this.f = datainput.readShort();
            this.g = datainput.readShort();
        }
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeByte(this.j);
        dataoutput.writeInt(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.writeInt(this.d);
        dataoutput.writeByte(this.h);
        dataoutput.writeByte(this.i);
        dataoutput.writeInt(this.k);
        if (this.k > 0) {
            dataoutput.writeShort(this.e);
            dataoutput.writeShort(this.f);
            dataoutput.writeShort(this.g);
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 21 + this.k > 0 ? 6 : 0;
    }
}
