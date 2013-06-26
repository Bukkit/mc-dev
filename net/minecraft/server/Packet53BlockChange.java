package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet53BlockChange extends Packet {

    public int a;
    public int b;
    public int c;
    public int material;
    public int data;

    public Packet53BlockChange() {
        this.lowPriority = true;
    }

    public Packet53BlockChange(int i, int j, int k, World world) {
        this.lowPriority = true;
        this.a = i;
        this.b = j;
        this.c = k;
        this.material = world.getTypeId(i, j, k);
        this.data = world.getData(i, j, k);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readUnsignedByte();
        this.c = datainput.readInt();
        this.material = datainput.readShort();
        this.data = datainput.readUnsignedByte();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.write(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.writeShort(this.material);
        dataoutput.write(this.data);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 11;
    }
}
