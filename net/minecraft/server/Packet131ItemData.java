package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet131ItemData extends Packet {

    public short a;
    public short b;
    public byte[] c;

    public Packet131ItemData() {
        this.lowPriority = true;
    }

    public Packet131ItemData(short short1, short short2, byte[] abyte) {
        this.lowPriority = true;
        this.a = short1;
        this.b = short2;
        this.c = abyte;
    }

    public void a(DataInput datainput) {
        this.a = datainput.readShort();
        this.b = datainput.readShort();
        this.c = new byte[datainput.readUnsignedShort()];
        datainput.readFully(this.c);
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeShort(this.a);
        dataoutput.writeShort(this.b);
        dataoutput.writeShort(this.c.length);
        dataoutput.write(this.c);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 4 + this.c.length;
    }
}
