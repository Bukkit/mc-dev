package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
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

    public void a(DataInput datainput) {
        this.a = datainput.readByte();
        short short1 = datainput.readShort();

        this.b = new ItemStack[short1];

        for (int i = 0; i < short1; ++i) {
            this.b[i] = c(datainput);
        }
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a);
        dataoutput.writeShort(this.b.length);

        for (int i = 0; i < this.b.length; ++i) {
            a(this.b[i], dataoutput);
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 3 + this.b.length * 5;
    }
}
