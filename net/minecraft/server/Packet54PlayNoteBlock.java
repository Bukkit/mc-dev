package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

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

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readShort();
        this.c = datainput.readInt();
        this.d = datainput.readUnsignedByte();
        this.e = datainput.readUnsignedByte();
        this.f = datainput.readShort() & 4095;
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeShort(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.write(this.d);
        dataoutput.write(this.e);
        dataoutput.writeShort(this.f & 4095);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 14;
    }
}
