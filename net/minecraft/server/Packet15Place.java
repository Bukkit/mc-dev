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
        short short1 = datainputstream.readShort();

        if (short1 >= 0) {
            byte b0 = datainputstream.readByte();
            short short2 = datainputstream.readShort();

            this.itemstack = new ItemStack(short1, b0, short2);
        } else {
            this.itemstack = null;
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.write(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.write(this.face);
        if (this.itemstack == null) {
            dataoutputstream.writeShort(-1);
        } else {
            dataoutputstream.writeShort(this.itemstack.id);
            dataoutputstream.writeByte(this.itemstack.count);
            dataoutputstream.writeShort(this.itemstack.getData());
        }
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 15;
    }
}
