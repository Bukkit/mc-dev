package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet201PlayerInfo extends Packet {

    public String a;
    public boolean b;
    public int c;

    public Packet201PlayerInfo() {}

    public Packet201PlayerInfo(String s, boolean flag, int i) {
        this.a = s;
        this.b = flag;
        this.c = i;
    }

    public void a(DataInput datainput) {
        this.a = a(datainput, 16);
        this.b = datainput.readByte() != 0;
        this.c = datainput.readShort();
    }

    public void a(DataOutput dataoutput) {
        a(this.a, dataoutput);
        dataoutput.writeByte(this.b ? 1 : 0);
        dataoutput.writeShort(this.c);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return this.a.length() + 2 + 1 + 2;
    }
}
