package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet22Collect extends Packet {

    public int a;
    public int b;

    public Packet22Collect() {}

    public Packet22Collect(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readInt();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeInt(this.b);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 8;
    }
}
