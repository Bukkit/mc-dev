package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;

public class Packet104WindowItems extends Packet {

    public int a;
    public ItemStack[] b;

    public Packet104WindowItems() {}

    public Packet104WindowItems(int i, List list) {
        this.a = i;
        this.b = new ItemStack[list.size()];

        for (int j = 0; j < this.b.length; ++j) {
            ItemStack itemstack = (ItemStack) list.get(j);

            this.b[j] = itemstack == null ? null : itemstack.cloneItemStack();
        }
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readByte();
        short short1 = datainputstream.readShort();

        this.b = new ItemStack[short1];

        for (int i = 0; i < short1; ++i) {
            this.b[i] = this.b(datainputstream);
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a);
        dataoutputstream.writeShort(this.b.length);

        for (int i = 0; i < this.b.length; ++i) {
            this.a(this.b[i], dataoutputstream);
        }
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 3 + this.b.length * 5;
    }
}
