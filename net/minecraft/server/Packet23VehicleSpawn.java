package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

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

    public Packet23VehicleSpawn() {}

    public Packet23VehicleSpawn(Entity entity, int i) {
        this(entity, i, 0);
    }

    public Packet23VehicleSpawn(Entity entity, int i, int j) {
        this.a = entity.id;
        this.b = MathHelper.floor(entity.locX * 32.0D);
        this.c = MathHelper.floor(entity.locY * 32.0D);
        this.d = MathHelper.floor(entity.locZ * 32.0D);
        this.h = i;
        this.i = j;
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

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.h = datainputstream.readByte();
        this.b = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.i = datainputstream.readInt();
        if (this.i > 0) {
            this.e = datainputstream.readShort();
            this.f = datainputstream.readShort();
            this.g = datainputstream.readShort();
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.h);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeInt(this.i);
        if (this.i > 0) {
            dataoutputstream.writeShort(this.e);
            dataoutputstream.writeShort(this.f);
            dataoutputstream.writeShort(this.g);
        }
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 21 + this.i > 0 ? 6 : 0;
    }
}
