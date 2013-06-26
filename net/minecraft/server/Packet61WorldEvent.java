package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet61WorldEvent extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    private boolean f;

    public Packet61WorldEvent() {}

    public Packet61WorldEvent(int i, int j, int k, int l, int i1, boolean flag) {
        this.a = i;
        this.c = j;
        this.d = k;
        this.e = l;
        this.b = i1;
        this.f = flag;
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.c = datainput.readInt();
        this.d = datainput.readByte() & 255;
        this.e = datainput.readInt();
        this.b = datainput.readInt();
        this.f = datainput.readBoolean();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeInt(this.c);
        dataoutput.writeByte(this.d & 255);
        dataoutput.writeInt(this.e);
        dataoutput.writeInt(this.b);
        dataoutput.writeBoolean(this.f);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 21;
    }
}
