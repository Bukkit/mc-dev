package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet16BlockItemSwitch extends Packet {

    public int a;
    public int b;

    public Packet16BlockItemSwitch() {}

    public Packet16BlockItemSwitch(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeShort(this.b);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 6;
    }
}
