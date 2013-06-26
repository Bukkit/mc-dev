package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet63WorldParticles extends Packet {

    private String a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private int i;

    public Packet63WorldParticles() {}

    public void a(DataInput datainput) {
        this.a = a(datainput, 64);
        this.b = datainput.readFloat();
        this.c = datainput.readFloat();
        this.d = datainput.readFloat();
        this.e = datainput.readFloat();
        this.f = datainput.readFloat();
        this.g = datainput.readFloat();
        this.h = datainput.readFloat();
        this.i = datainput.readInt();
    }

    public void a(DataOutput dataoutput) {
        a(this.a, dataoutput);
        dataoutput.writeFloat(this.b);
        dataoutput.writeFloat(this.c);
        dataoutput.writeFloat(this.d);
        dataoutput.writeFloat(this.e);
        dataoutput.writeFloat(this.f);
        dataoutput.writeFloat(this.g);
        dataoutput.writeFloat(this.h);
        dataoutput.writeInt(this.i);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 64;
    }
}
