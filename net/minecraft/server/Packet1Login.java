package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet1Login extends Packet {

    public int a;
    public String name;
    public long c;
    public int d;
    public byte e;
    public byte f;
    public byte g;
    public byte h;

    public Packet1Login() {}

    public Packet1Login(String s, int i, long j, int k, byte b0, byte b1, byte b2, byte b3) {
        this.name = s;
        this.a = i;
        this.c = j;
        this.e = b0;
        this.f = b1;
        this.d = k;
        this.g = b2;
        this.h = b3;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.name = a(datainputstream, 16);
        this.c = datainputstream.readLong();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readByte();
        this.f = datainputstream.readByte();
        this.g = datainputstream.readByte();
        this.h = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        a(this.name, dataoutputstream);
        dataoutputstream.writeLong(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeByte(this.e);
        dataoutputstream.writeByte(this.f);
        dataoutputstream.writeByte(this.g);
        dataoutputstream.writeByte(this.h);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 4 + this.name.length() + 4 + 7 + 4;
    }
}
