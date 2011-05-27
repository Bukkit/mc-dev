package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet19EntityAction extends Packet {

    public int a;
    public int animation;

    public Packet19EntityAction() {}

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.animation = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.animation);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 5;
    }
}
