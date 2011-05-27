package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagByte extends NBTBase {

    public byte a;

    public NBTTagByte() {}

    public NBTTagByte(byte b0) {
        this.a = b0;
    }

    void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a);
    }

    void a(DataInput datainput) {
        this.a = datainput.readByte();
    }

    public byte a() {
        return (byte) 1;
    }

    public String toString() {
        return "" + this.a;
    }
}
