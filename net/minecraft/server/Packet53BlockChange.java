package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet53BlockChange extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public Packet53BlockChange() {
        this.k = true;
    }

    public Packet53BlockChange(int i, int j, int k, World world) {
        this.k = true;
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = world.getTypeId(i, j, k);
        this.e = world.getData(i, j, k);
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.read();
        this.c = datainputstream.readInt();
        this.d = datainputstream.read();
        this.e = datainputstream.read();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.write(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.write(this.d);
        dataoutputstream.write(this.e);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 11;
    }
}
