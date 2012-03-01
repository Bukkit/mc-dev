package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet132TileEntityData extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;

    public Packet132TileEntityData() {
        this.lowPriority = true;
    }

    public Packet132TileEntityData(int i, int j, int k, int l, int i1) {
        this.lowPriority = true;
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
        this.e = i1;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readShort();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readByte();
        this.e = datainputstream.readInt();
        this.f = datainputstream.readInt();
        this.g = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeShort(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeByte((byte) this.d);
        dataoutputstream.writeInt(this.e);
        dataoutputstream.writeInt(this.f);
        dataoutputstream.writeInt(this.g);
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 25;
    }
}
