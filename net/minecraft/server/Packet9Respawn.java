package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet9Respawn extends Packet {

    public long a;
    public int b;
    public int c;
    public int d;
    public int e;
    public WorldType f;

    public Packet9Respawn() {}

    public Packet9Respawn(byte b0, byte b1, long i, WorldType worldtype, int j, int k) {
        this.b = b0;
        this.c = b1;
        this.a = i;
        this.d = j;
        this.e = k;
        this.f = worldtype;
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
        String s = a(datainputstream, 16);

        this.f = WorldType.a(s);
        if (this.f == null) {
            this.f = WorldType.NORMAL;
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.b);
        dataoutputstream.writeByte(this.c);
        dataoutputstream.writeByte(this.e);
        dataoutputstream.writeShort(this.d);
        dataoutputstream.writeLong(this.a);
        a(this.f.name(), dataoutputstream);
    }

    public int a() {
        return 13 + this.f.name().length();
    }
}
