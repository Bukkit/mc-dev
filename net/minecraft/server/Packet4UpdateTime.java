package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet4UpdateTime extends Packet {

    public long a;

    public Packet4UpdateTime() {}

    public Packet4UpdateTime(long i) {
        this.a = i;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readLong();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeLong(this.a);
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 8;
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
