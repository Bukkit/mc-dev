package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagShort extends NBTBase {

    public short a;

    public NBTTagShort() {}

    public NBTTagShort(short short1) {
        this.a = short1;
    }

    void a(DataOutput dataoutput) {
        dataoutput.writeShort(this.a);
    }

    void a(DataInput datainput) {
        this.a = datainput.readShort();
    }

    public byte a() {
        return (byte) 2;
    }

    public String toString() {
        return "" + this.a;
    }
}
