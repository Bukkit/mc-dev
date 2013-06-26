package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet255KickDisconnect extends Packet {

    public String a;

    public Packet255KickDisconnect() {}

    public Packet255KickDisconnect(String s) {
        this.a = s;
    }

    public void a(DataInput datainput) {
        this.a = a(datainput, 256);
    }

    public void a(DataOutput dataoutput) {
        a(this.a, dataoutput);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return this.a.length();
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        return true;
    }
}
