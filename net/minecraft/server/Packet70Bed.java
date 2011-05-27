package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet70Bed extends Packet {

    public static final String[] a = new String[] { "tile.bed.notValid"};
    public int b;

    public Packet70Bed() {}

    public Packet70Bed(int i) {
        this.b = i;
    }

    public void a(DataInputStream datainputstream) {
        this.b = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.b);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 1;
    }
}
