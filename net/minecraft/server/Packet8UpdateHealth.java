package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet8UpdateHealth extends Packet {

    public int a;

    public Packet8UpdateHealth() {}

    public Packet8UpdateHealth(int i) {
        this.a = i;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeShort(this.a);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 2;
    }
}
