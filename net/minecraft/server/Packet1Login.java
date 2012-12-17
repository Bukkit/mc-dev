package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet1Login extends Packet {

    public int a = 0;
    public WorldType b;
    public boolean c;
    public EnumGamemode d;
    public int e;
    public byte f;
    public byte g;
    public byte h;

    public Packet1Login() {}

    public Packet1Login(int i, WorldType worldtype, EnumGamemode enumgamemode, boolean flag, int j, int k, int l, int i1) {
        this.a = i;
        this.b = worldtype;
        this.e = j;
        this.f = (byte) k;
        this.d = enumgamemode;
        this.g = (byte) l;
        this.h = (byte) i1;
        this.c = flag;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        String s = a(datainputstream, 16);

        this.b = WorldType.getType(s);
        if (this.b == null) {
            this.b = WorldType.NORMAL;
        }

        byte b0 = datainputstream.readByte();

        this.c = (b0 & 8) == 8;
        int i = b0 & -9;

        this.d = EnumGamemode.a(i);
        this.e = datainputstream.readByte();
        this.f = datainputstream.readByte();
        this.g = datainputstream.readByte();
        this.h = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        a(this.b == null ? "" : this.b.name(), dataoutputstream);
        int i = this.d.a();

        if (this.c) {
            i |= 8;
        }

        dataoutputstream.writeByte(i);
        dataoutputstream.writeByte(this.e);
        dataoutputstream.writeByte(this.f);
        dataoutputstream.writeByte(this.g);
        dataoutputstream.writeByte(this.h);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        int i = 0;

        if (this.b != null) {
            i = this.b.name().length();
        }

        return 6 + 2 * i + 4 + 4 + 1 + 1 + 1;
    }
}
