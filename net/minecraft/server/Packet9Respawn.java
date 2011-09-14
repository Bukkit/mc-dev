package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet9Respawn extends Packet {

    public long a;
    public int b;
    public int c;
    public int d;
    public int e;

    public Packet9Respawn() {}

    public Packet9Respawn(byte b0, byte b1, long i, int j, int k) {
        this.b = b0;
        this.c = b1;
        this.a = i;
        this.d = j;
        this.e = k;
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) {
        this.b = datainputstream.readByte();
        this.c = datainputstream.readByte();
        this.e = datainputstream.readByte();
        this.d = datainputstream.readShort();
        this.a = datainputstream.readLong();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.b);
        dataoutputstream.writeByte(this.c);
        dataoutputstream.writeByte(this.e);
        dataoutputstream.writeShort(this.d);
        dataoutputstream.writeLong(this.a);
    }

    public int a() {
        return 13;
    }
}
