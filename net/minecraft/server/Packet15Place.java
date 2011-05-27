package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet15Place extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public Packet15Place() {}

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readShort();
        this.b = datainputstream.readInt();
        this.c = datainputstream.read();
        this.d = datainputstream.readInt();
        this.e = datainputstream.read();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeShort(this.a);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.write(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.write(this.e);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 12;
    }
}
