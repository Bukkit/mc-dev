package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet102WindowClick extends Packet {

    public int a;
    public int b;
    public int c;
    public short d;
    public ItemStack e;
    public boolean f;

    public Packet102WindowClick() {}

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readByte();
        this.b = datainputstream.readShort();
        this.c = datainputstream.readByte();
        this.d = datainputstream.readShort();
        this.f = datainputstream.readBoolean();
        this.e = this.b(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a);
        dataoutputstream.writeShort(this.b);
        dataoutputstream.writeByte(this.c);
        dataoutputstream.writeShort(this.d);
        dataoutputstream.writeBoolean(this.f);
        this.a(this.e, dataoutputstream);
    }

    public int a() {
        return 11;
    }
}
