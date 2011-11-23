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
        this.c = this.b(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a);
        dataoutputstream.writeShort(this.b);
        this.a(this.c, dataoutputstream);
    }

    public int a() {
        return 8;
    }
}
