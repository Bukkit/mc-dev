package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet9Respawn extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public WorldType e;

    public Packet9Respawn() {}

    public Packet9Respawn(int i, byte b0, WorldType worldtype, int j, int k) {
        this.a = i;
        this.b = b0;
        this.c = j;
        this.d = k;
        this.e = worldtype;
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readByte();
        this.d = datainputstream.readByte();
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
        dataoutputstream.writeByte(this.d);
        dataoutputstream.writeShort(this.c);
        a(this.e.name(), dataoutputstream);
    }

    public int a() {
        return 8 + this.e.name().length();
    }
}
