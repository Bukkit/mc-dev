package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet50PreChunk extends Packet {

    public int a;
    public int b;
    public boolean c;

    public Packet50PreChunk() {
        this.l = false;
    }

    public Packet50PreChunk(int i, int j, boolean flag) {
        this.l = false;
        this.a = i;
        this.b = j;
        this.c = flag;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readInt();
        this.c = datainputstream.read() != 0;
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.write(this.c ? 1 : 0);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 9;
    }
}
