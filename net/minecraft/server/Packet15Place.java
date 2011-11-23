package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet15Place extends Packet {

    public int a;
    public int b;
    public int c;
    public int face;
    public ItemStack itemstack;

    public Packet15Place() {}

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.read();
        this.c = datainputstream.readInt();
        this.face = datainputstream.read();
        this.itemstack = this.b(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.write(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.write(this.face);
        this.a(this.itemstack, dataoutputstream);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 15;
    }
}
