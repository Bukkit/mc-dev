package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet2Handshake extends Packet {

    public String a;

    public Packet2Handshake() {}

    public Packet2Handshake(String s) {
        this.a = s;
    }

    public void a(DataInputStream datainputstream) {
        this.a = a(datainputstream, 32);
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.a, dataoutputstream);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 4 + this.a.length() + 4;
    }
}
