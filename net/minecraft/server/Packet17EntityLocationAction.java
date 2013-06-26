package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet17EntityLocationAction extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public Packet17EntityLocationAction() {}

    public Packet17EntityLocationAction(Entity entity, int i, int j, int k, int l) {
        this.e = i;
        this.b = j;
        this.c = k;
        this.d = l;
        this.a = entity.id;
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.e = datainput.readByte();
        this.b = datainput.readInt();
        this.c = datainput.readByte();
        this.d = datainput.readInt();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeByte(this.e);
        dataoutput.writeInt(this.b);
        dataoutput.writeByte(this.c);
        dataoutput.writeInt(this.d);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 14;
    }
}
