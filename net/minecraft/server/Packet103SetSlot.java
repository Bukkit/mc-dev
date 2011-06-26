package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet103SetSlot extends Packet {

    public int a;
    public int b;
    public ItemStack c;

    public Packet103SetSlot() {}

    public Packet103SetSlot(int i, int j, ItemStack itemstack) {
        this.a = i;
        this.b = j;
        this.c = itemstack == null ? itemstack : itemstack.cloneItemStack();
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readByte();
        this.b = datainputstream.readShort();
        short short1 = datainputstream.readShort();

        if (short1 >= 0) {
            byte b0 = datainputstream.readByte();
            short short2 = datainputstream.readShort();

            this.c = new ItemStack(short1, b0, short2);
        } else {
            this.c = null;
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a);
        dataoutputstream.writeShort(this.b);
        if (this.c == null) {
            dataoutputstream.writeShort(-1);
        } else {
            dataoutputstream.writeShort(this.c.id);
            dataoutputstream.writeByte(this.c.count);
            dataoutputstream.writeShort(this.c.getData());
        }
    }

    public int a() {
        return 8;
    }
}
