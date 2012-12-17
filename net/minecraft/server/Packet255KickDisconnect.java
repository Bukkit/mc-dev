package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet255KickDisconnect extends Packet {

    public String a;

    public Packet255KickDisconnect() {}

    public Packet255KickDisconnect(String s) {
        this.a = s;
    }

    public void a(DataInputStream datainputstream) {
        this.a = a(datainputstream, 256);
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.a, dataoutputstream);
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
