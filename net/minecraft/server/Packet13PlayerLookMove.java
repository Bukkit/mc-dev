package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet13PlayerLookMove extends Packet10Flying {

    public Packet13PlayerLookMove() {
        this.i = true;
        this.h = true;
    }

    public Packet13PlayerLookMove(double d0, double d1, double d2, double d3, float f, float f1, boolean flag) {
        this.a = d0;
        this.b = d1;
        this.d = d2;
        this.c = d3;
        this.e = f;
        this.f = f1;
        this.g = flag;
        this.i = true;
        this.h = true;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readDouble();
        this.b = datainputstream.readDouble();
        this.d = datainputstream.readDouble();
        this.c = datainputstream.readDouble();
        this.e = datainputstream.readFloat();
        this.f = datainputstream.readFloat();
        super.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeDouble(this.a);
        dataoutputstream.writeDouble(this.b);
        dataoutputstream.writeDouble(this.d);
        dataoutputstream.writeDouble(this.c);
        dataoutputstream.writeFloat(this.e);
        dataoutputstream.writeFloat(this.f);
        super.a(dataoutputstream);
    }

    public int a() {
        return 41;
    }
}
