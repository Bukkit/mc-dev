package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet35EntityHeadRotation extends Packet {

    public int a;
    public byte b;

    public Packet35EntityHeadRotation() {}

    public Packet35EntityHeadRotation(int i, byte b0) {
        this.a = i;
        this.b = b0;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.b);
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 5;
    }
}
