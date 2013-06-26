package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet8UpdateHealth extends Packet {

    public float a;
    public int b;
    public float c;

    public Packet8UpdateHealth() {}

    public Packet8UpdateHealth(float f, int i, float f1) {
        this.a = f;
        this.b = i;
        this.c = f1;
    }

    public void a(DataInput datainput) {
        this.a = datainput.readFloat();
        this.b = datainput.readShort();
        this.c = datainput.readFloat();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeFloat(this.a);
        dataoutput.writeShort(this.b);
        dataoutput.writeFloat(this.c);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 8;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        return true;
    }
}
