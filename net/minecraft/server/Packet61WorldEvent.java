package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet61WorldEvent extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public Packet61WorldEvent() {}

    public Packet61WorldEvent(int i, int j, int k, int l, int i1) {
        this.a = i;
        this.c = j;
        this.d = k;
        this.e = l;
        this.b = i1;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readByte();
        this.e = datainputstream.readInt();
        this.b = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeByte(this.d);
        dataoutputstream.writeInt(this.e);
        dataoutputstream.writeInt(this.b);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 20;
    }
}
