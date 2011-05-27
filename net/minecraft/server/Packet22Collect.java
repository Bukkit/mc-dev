package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet22Collect extends Packet {

    public int a;
    public int b;

    public Packet22Collect() {}

    public Packet22Collect(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeInt(this.b);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 8;
    }
}
