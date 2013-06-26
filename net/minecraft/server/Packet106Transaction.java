package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet106Transaction extends Packet {

    public int a;
    public short b;
    public boolean c;

    public Packet106Transaction() {}

    public Packet106Transaction(int i, short short1, boolean flag) {
        this.a = i;
        this.b = short1;
        this.c = flag;
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readByte();
        this.b = datainput.readShort();
        this.c = datainput.readByte() != 0;
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a);
        dataoutput.writeShort(this.b);
        dataoutput.writeByte(this.c ? 1 : 0);
    }

    public int a() {
        return 4;
    }
}
