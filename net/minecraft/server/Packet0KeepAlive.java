package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet0KeepAlive extends Packet {

    public int a;

    public Packet0KeepAlive() {}

    public Packet0KeepAlive(int i) {
        this.a = i;
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
    }

    public int a() {
        return 4;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        return true;
    }

    public boolean a_() {
        return true;
    }
}
