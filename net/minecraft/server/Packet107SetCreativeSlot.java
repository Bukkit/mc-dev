package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet107SetCreativeSlot extends Packet {

    public int slot;
    public ItemStack b;

    public Packet107SetCreativeSlot() {}

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInput datainput) {
        this.slot = datainput.readShort();
        this.b = c(datainput);
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeShort(this.slot);
        a(this.b, dataoutput);
    }

    public int a() {
        return 8;
    }
}
