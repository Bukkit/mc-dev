package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet9Respawn extends Packet {

    public Packet9Respawn() {}

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) {}

    public void a(DataOutputStream dataoutputstream) {}

    public int a() {
        return 0;
    }
}
