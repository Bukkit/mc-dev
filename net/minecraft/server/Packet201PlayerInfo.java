package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet201PlayerInfo extends Packet {

    public String a;
    public boolean b;
    public int c;

    public Packet201PlayerInfo() {}

    public Packet201PlayerInfo(String s, boolean flag, int i) {
        this.a = s;
        this.b = flag;
        this.c = i;
    }

    public void a(DataInputStream datainputstream) {
        this.a = a(datainputstream, 16);
        this.b = datainputstream.readByte() != 0;
        this.c = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.a, dataoutputstream);
        dataoutputstream.writeByte(this.b ? 1 : 0);
        dataoutputstream.writeShort(this.c);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return this.a.length() + 2 + 1 + 2;
    }
}
