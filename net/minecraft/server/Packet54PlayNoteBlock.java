package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet54PlayNoteBlock extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public Packet54PlayNoteBlock() {}

    public Packet54PlayNoteBlock(int i, int j, int k, int l, int i1, int j1) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = i1;
        this.e = j1;
        this.f = l;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readShort();
        this.c = datainputstream.readInt();
        this.d = datainputstream.read();
        this.e = datainputstream.read();
        this.f = datainputstream.readShort() & 4095;
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeShort(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.write(this.d);
        dataoutputstream.write(this.e);
        dataoutputstream.writeShort(this.f & 4095);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 14;
    }
}
