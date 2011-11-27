package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet108ButtonClick extends Packet {

    public int a;
    public int b;

    public Packet108ButtonClick() {}

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readByte();
        this.b = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a);
        dataoutputstream.writeByte(this.b);
    }

    public int a() {
        return 2;
    }
}
