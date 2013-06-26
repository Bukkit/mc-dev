package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

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

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readInt();
        this.c = datainput.readInt();
        this.d = datainput.readInt();
        this.e = datainput.readUnsignedByte();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeInt(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.writeInt(this.d);
        dataoutput.write(this.e);
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
