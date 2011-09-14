package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet8UpdateHealth extends Packet {

    public int a;
    public int b;
    public float c;

    public Packet8UpdateHealth() {}

    public Packet8UpdateHealth(int i, int j, float f) {
        this.a = i;
        this.b = j;
        this.c = f;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readShort();
        this.b = datainputstream.readShort();
        this.c = datainputstream.readFloat();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeShort(this.a);
        dataoutputstream.writeShort(this.b);
        dataoutputstream.writeFloat(this.c);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 8;
    }
}
