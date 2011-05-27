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
        this.a = datainputstream.readUTF();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeUTF(this.a);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return this.a.length();
    }
}
