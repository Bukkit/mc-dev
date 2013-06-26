package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet132TileEntityData extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public NBTTagCompound e;

    public Packet132TileEntityData() {
        this.lowPriority = true;
    }

    public Packet132TileEntityData(int i, int j, int k, int l, NBTTagCompound nbttagcompound) {
        this.lowPriority = true;
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
        this.e = nbttagcompound;
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readShort();
        this.c = datainput.readInt();
        this.d = datainput.readByte();
        this.e = d(datainput);
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeShort(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.writeByte((byte) this.d);
        a(this.e, dataoutput);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 25;
    }
}
