package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet133OpenTileEntity extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;

    public Packet133OpenTileEntity() {}

    public Packet133OpenTileEntity(int i, int j, int k, int l) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readByte();
        this.b = datainput.readInt();
        this.c = datainput.readInt();
        this.d = datainput.readInt();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a);
        dataoutput.writeInt(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.writeInt(this.d);
    }

    public int a() {
        return 13;
    }
}
