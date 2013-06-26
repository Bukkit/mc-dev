package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet108ButtonClick extends Packet {

    public int a;
    public int b;

    public Packet108ButtonClick() {}

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readByte();
        this.b = datainput.readByte();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a);
        dataoutput.writeByte(this.b);
    }

    public int a() {
        return 2;
    }
}
