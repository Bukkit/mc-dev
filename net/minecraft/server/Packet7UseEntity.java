package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet7UseEntity extends Packet {

    public int a;
    public int target;
    public int c;

    public Packet7UseEntity() {}

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.target = datainputstream.readInt();
        this.c = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeInt(this.target);
        dataoutputstream.writeByte(this.c);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 9;
    }
}
