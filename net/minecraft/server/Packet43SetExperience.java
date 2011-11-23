package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

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

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readFloat();
        this.c = datainputstream.readShort();
        this.b = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeFloat(this.a);
        dataoutputstream.writeShort(this.c);
        dataoutputstream.writeShort(this.b);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 4;
    }
}
