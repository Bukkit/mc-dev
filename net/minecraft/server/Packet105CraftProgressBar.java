package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet105CraftProgressBar extends Packet {

    public int a;
    public int b;
    public int c;

    public Packet105CraftProgressBar() {}

    public Packet105CraftProgressBar(int i, int j, int k) {
        this.a = i;
        this.b = j;
        this.c = k;
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readByte();
        this.b = datainputstream.readShort();
        this.c = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a);
        dataoutputstream.writeShort(this.b);
        dataoutputstream.writeShort(this.c);
    }

    public int a() {
        return 5;
    }
}
