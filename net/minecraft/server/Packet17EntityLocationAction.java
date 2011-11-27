package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet17EntityLocationAction extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public Packet17EntityLocationAction() {}

    public Packet17EntityLocationAction(Entity entity, int i, int j, int k, int l) {
        this.e = i;
        this.b = j;
        this.c = k;
        this.d = l;
        this.a = entity.id;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.e = datainputstream.readByte();
        this.b = datainputstream.readInt();
        this.c = datainputstream.readByte();
        this.d = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.e);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeByte(this.c);
        dataoutputstream.writeInt(this.d);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 14;
    }
}
