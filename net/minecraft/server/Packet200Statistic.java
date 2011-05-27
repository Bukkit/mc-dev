package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet200Statistic extends Packet {

    public int a;
    public int b;

    public Packet200Statistic() {}

    public Packet200Statistic(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.b);
    }

    public int a() {
        return 6;
    }
}
