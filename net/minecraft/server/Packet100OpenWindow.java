package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet100OpenWindow extends Packet {

    public int a;
    public int b;
    public String c;
    public int d;
    public boolean e;
    public int f;

    public Packet100OpenWindow() {}

    public Packet100OpenWindow(int i, int j, String s, int k, boolean flag) {
        this.a = i;
        this.b = j;
        this.c = s;
        this.d = k;
        this.e = flag;
    }

    public Packet100OpenWindow(int i, int j, String s, int k, boolean flag, int l) {
        this(i, j, s, k, flag);
        this.f = l;
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readByte() & 255;
        this.b = datainput.readByte() & 255;
        this.c = a(datainput, 32);
        this.d = datainput.readByte() & 255;
        this.e = datainput.readBoolean();
        if (this.b == 11) {
            this.f = datainput.readInt();
        }
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a & 255);
        dataoutput.writeByte(this.b & 255);
        a(this.c, dataoutput);
        dataoutput.writeByte(this.d & 255);
        dataoutput.writeBoolean(this.e);
        if (this.b == 11) {
            dataoutput.writeInt(this.f);
        }
    }

    public int a() {
        return this.b == 11 ? 8 + this.c.length() : 4 + this.c.length();
    }
}
