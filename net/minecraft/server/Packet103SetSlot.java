package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

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

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readByte();
        this.b = datainput.readShort();
        this.c = c(datainput);
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a);
        dataoutput.writeShort(this.b);
        a(this.c, dataoutput);
    }

    public int a() {
        return 8;
    }
}
