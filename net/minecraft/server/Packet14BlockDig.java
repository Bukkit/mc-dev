package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet14BlockDig extends Packet {

    public int a;
    public int b;
    public int c;
    public int face;
    public int e;

    public Packet14BlockDig() {}

    public void a(DataInput datainput) {
        this.e = datainput.readUnsignedByte();
        this.a = datainput.readInt();
        this.b = datainput.readUnsignedByte();
        this.c = datainput.readInt();
        this.face = datainput.readUnsignedByte();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.write(this.e);
        dataoutput.writeInt(this.a);
        dataoutput.write(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.write(this.face);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 11;
    }
}
