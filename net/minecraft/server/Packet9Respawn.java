package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet9Respawn extends Packet {

    public int a;
    public int b;
    public int c;
    public EnumGamemode d;
    public WorldType e;

    public Packet9Respawn() {}

    public Packet9Respawn(int i, byte b0, WorldType worldtype, int j, EnumGamemode enumgamemode) {
        this.a = i;
        this.b = b0;
        this.c = j;
        this.d = enumgamemode;
        this.e = worldtype;
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readByte();
        this.d = EnumGamemode.a(datainput.readByte());
        this.c = datainput.readShort();
        String s = a(datainput, 16);

        this.e = WorldType.getType(s);
        if (this.e == null) {
            this.e = WorldType.NORMAL;
        }
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeByte(this.b);
        dataoutput.writeByte(this.d.a());
        dataoutput.writeShort(this.c);
        a(this.e.name(), dataoutput);
    }

    public int a() {
        return 8 + (this.e == null ? 0 : this.e.name().length());
    }
}
