package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet105CraftProgressBar extends Packet {

    public int a;
    public int b;
    public int c;

    public Packet105CraftProgressBar() {}

    public Packet105CraftProgressBar(int i, int j, int k) {
        this.a = i;
        this.b = j;
        this.c = k;
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readByte();
        this.b = datainput.readShort();
        this.c = datainput.readShort();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a);
        dataoutput.writeShort(this.b);
        dataoutput.writeShort(this.c);
    }

    public int a() {
        return 5;
    }
}
