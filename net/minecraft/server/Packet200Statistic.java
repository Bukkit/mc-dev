package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet200Statistic extends Packet {

    public int a;
    public int b;

    public Packet200Statistic() {}

    public Packet200Statistic(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readInt();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeInt(this.b);
    }

    public int a() {
        return 6;
    }

    public boolean a_() {
        return true;
    }
}
