package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet102WindowClick extends Packet {

    public int a;
    public int slot;
    public int button;
    public short d;
    public ItemStack item;
    public int shift;

    public Packet102WindowClick() {}

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInput datainput) {
        this.a = datainput.readByte();
        this.slot = datainput.readShort();
        this.button = datainput.readByte();
        this.d = datainput.readShort();
        this.shift = datainput.readByte();
        this.item = c(datainput);
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a);
        dataoutput.writeShort(this.slot);
        dataoutput.writeByte(this.button);
        dataoutput.writeShort(this.d);
        dataoutput.writeByte(this.shift);
        a(this.item, dataoutput);
    }

    public int a() {
        return 11;
    }
}
