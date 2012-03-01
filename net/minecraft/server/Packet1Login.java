package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet1Login extends Packet {

    public int a;
    public String name;
    public WorldType c;
    public int d;
    public int e;
    public byte f;
    public byte g;
    public byte h;

    public Packet1Login() {}

    public Packet1Login(String s, int i, WorldType worldtype, int j, int k, byte b0, byte b1, byte b2) {
        this.name = s;
        this.a = i;
        this.c = worldtype;
        this.e = k;
        this.f = b0;
        this.d = j;
        this.g = b1;
        this.h = b2;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.name = a(datainputstream, 16);
        String s = a(datainputstream, 16);

        this.c = WorldType.getType(s);
        if (this.c == null) {
            this.c = WorldType.NORMAL;
        }

        this.d = datainputstream.readInt();
        this.e = datainputstream.readInt();
        this.f = datainputstream.readByte();
        this.g = datainputstream.readByte();
        this.h = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        a(this.name, dataoutputstream);
        if (this.c == null) {
            a("", dataoutputstream);
        } else {
            a(this.c.name(), dataoutputstream);
        }

        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeInt(this.e);
        dataoutputstream.writeByte(this.f);
        dataoutputstream.writeByte(this.g);
        dataoutputstream.writeByte(this.h);
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        int i = 0;

        if (this.c != null) {
            i = this.c.name().length();
        }

        return 4 + this.name.length() + 4 + 7 + 7 + i;
    }
}
