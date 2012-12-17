package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

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

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readByte();
        this.d = EnumGamemode.a(datainputstream.readByte());
        this.c = datainputstream.readShort();
        String s = a(datainputstream, 16);

        this.e = WorldType.getType(s);
        if (this.e == null) {
            this.e = WorldType.NORMAL;
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.b);
        dataoutputstream.writeByte(this.d.a());
        dataoutputstream.writeShort(this.c);
        a(this.e.name(), dataoutputstream);
    }

    public int a() {
        return 8 + (this.e == null ? 0 : this.e.name().length());
    }
}
