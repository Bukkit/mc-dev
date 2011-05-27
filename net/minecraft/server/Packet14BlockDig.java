package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet14BlockDig extends Packet {

    public int a;
    public int b;
    public int c;
    public int face;
    public int e;

    public Packet14BlockDig() {}

    public void a(DataInputStream datainputstream) {
        this.e = datainputstream.read();
        this.a = datainputstream.readInt();
        this.b = datainputstream.read();
        this.c = datainputstream.readInt();
        this.face = datainputstream.read();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.write(this.e);
        dataoutputstream.writeInt(this.a);
        dataoutputstream.write(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.write(this.face);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 11;
    }
}
