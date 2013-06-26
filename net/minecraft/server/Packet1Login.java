package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet1Login extends Packet {

    public int a;
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

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        String s = a(datainput, 16);

        this.b = WorldType.getType(s);
        if (this.b == null) {
            this.b = WorldType.NORMAL;
        }

        byte b0 = datainput.readByte();

        this.c = (b0 & 8) == 8;
        int i = b0 & -9;

        this.d = EnumGamemode.a(i);
        this.e = datainput.readByte();
        this.f = datainput.readByte();
        this.g = datainput.readByte();
        this.h = datainput.readByte();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        a(this.b == null ? "" : this.b.name(), dataoutput);
        int i = this.d.a();

        if (this.c) {
            i |= 8;
        }

        dataoutput.writeByte(i);
        dataoutput.writeByte(this.e);
        dataoutput.writeByte(this.f);
        dataoutput.writeByte(this.g);
        dataoutput.writeByte(this.h);
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
