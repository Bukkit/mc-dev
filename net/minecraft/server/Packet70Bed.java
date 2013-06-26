package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet70Bed extends Packet {

    public static final String[] a = new String[] { "tile.bed.notValid", null, null, "gameMode.changed"};
    public int b;
    public int c;

    public Packet70Bed() {}

    public Packet70Bed(int i, int j) {
        this.b = i;
        this.c = j;
    }

    public void a(DataInput datainput) {
        this.b = datainput.readByte();
        this.c = datainput.readByte();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.b);
        dataoutput.writeByte(this.c);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2;
    }
}
