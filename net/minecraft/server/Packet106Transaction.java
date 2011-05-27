package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet106Transaction extends Packet {

    public int a;
    public short b;
    public boolean c;

    public Packet106Transaction() {}

    public Packet106Transaction(int i, short short1, boolean flag) {
        this.a = i;
        this.b = short1;
        this.c = flag;
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readByte();
        this.b = datainputstream.readShort();
        this.c = datainputstream.readByte() != 0;
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a);
        dataoutputstream.writeShort(this.b);
        dataoutputstream.writeByte(this.c ? 1 : 0);
    }

    public int a() {
        return 4;
    }
}
