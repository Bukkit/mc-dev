package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet28EntityVelocity extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;

    public Packet28EntityVelocity() {}

    public Packet28EntityVelocity(Entity entity) {
        this(entity.id, entity.motX, entity.motY, entity.motZ);
    }

    public Packet28EntityVelocity(int i, double d0, double d1, double d2) {
        this.a = i;
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

        this.b = (int) (d0 * 8000.0D);
        this.c = (int) (d1 * 8000.0D);
        this.d = (int) (d2 * 8000.0D);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readShort();
        this.c = datainput.readShort();
        this.d = datainput.readShort();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeShort(this.b);
        dataoutput.writeShort(this.c);
        dataoutput.writeShort(this.d);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 10;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        Packet28EntityVelocity packet28entityvelocity = (Packet28EntityVelocity) packet;

        return packet28entityvelocity.a == this.a;
    }
}
