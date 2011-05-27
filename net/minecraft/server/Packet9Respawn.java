package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet9Respawn extends Packet {

    public byte a;

    public Packet9Respawn() {}

    public Packet9Respawn(byte b0) {
        this.a = b0;
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a);
    }

    public int a() {
        return 1;
    }
}
