package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

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

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readShort();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readByte();
        this.e = d(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeShort(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeByte((byte) this.d);
        a(this.e, dataoutputstream);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 25;
    }
}
