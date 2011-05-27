package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet15Place extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public ItemStack e;

    public Packet15Place() {}

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.read();
        this.c = datainputstream.readInt();
        this.d = datainputstream.read();
        short short1 = datainputstream.readShort();

        if (short1 >= 0) {
            byte b0 = datainputstream.readByte();
            short short2 = datainputstream.readShort();

            this.e = new ItemStack(short1, b0, short2);
        } else {
            this.e = null;
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.write(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.write(this.d);
        if (this.e == null) {
            dataoutputstream.writeShort(-1);
        } else {
            dataoutputstream.writeShort(this.e.id);
            dataoutputstream.writeByte(this.e.count);
            dataoutputstream.writeShort(this.e.h());
        }
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 15;
    }
}
