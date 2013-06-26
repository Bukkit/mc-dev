package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet43SetExperience extends Packet {

    public float a;
    public int b;
    public int c;

    public Packet43SetExperience() {}

    public Packet43SetExperience(float f, int i, int j) {
        this.a = f;
        this.b = i;
        this.c = j;
    }

    public void a(DataInput datainput) {
        this.a = datainput.readFloat();
        this.c = datainput.readShort();
        this.b = datainput.readShort();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeFloat(this.a);
        dataoutput.writeShort(this.c);
        dataoutput.writeShort(this.b);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 4;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        return true;
    }
}
