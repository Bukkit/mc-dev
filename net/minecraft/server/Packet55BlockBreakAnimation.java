package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet55BlockBreakAnimation extends Packet {

    private int a;
    private int b;
    private int c;
    private int d;
    private int e;

    public Packet55BlockBreakAnimation() {}

    public Packet55BlockBreakAnimation(int i, int j, int k, int l, int i1) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
        this.e = i1;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.read();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.write(this.e);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 13;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        Packet55BlockBreakAnimation packet55blockbreakanimation = (Packet55BlockBreakAnimation) packet;

        return packet55blockbreakanimation.a == this.a;
    }
}
