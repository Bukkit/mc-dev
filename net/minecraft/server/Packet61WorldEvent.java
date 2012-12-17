package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet61WorldEvent extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    private boolean f;

    public Packet61WorldEvent() {}

    public Packet61WorldEvent(int i, int j, int k, int l, int i1, boolean flag) {
        this.a = i;
        this.c = j;
        this.d = k;
        this.e = l;
        this.b = i1;
        this.f = flag;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readByte() & 255;
        this.e = datainputstream.readInt();
        this.b = datainputstream.readInt();
        this.f = datainputstream.readBoolean();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeByte(this.d & 255);
        dataoutputstream.writeInt(this.e);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeBoolean(this.f);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 21;
    }
}
