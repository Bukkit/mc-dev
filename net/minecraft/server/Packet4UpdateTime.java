package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet4UpdateTime extends Packet {

    public long a;
    public long b;

    public Packet4UpdateTime() {}

    public Packet4UpdateTime(long i, long j) {
        this.a = i;
        this.b = j;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readLong();
        this.b = datainputstream.readLong();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeLong(this.a);
        dataoutputstream.writeLong(this.b);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 16;
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
