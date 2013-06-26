package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagEnd extends NBTBase {

    public NBTTagEnd() {
        super((String) null);
    }

    void load(DataInput datainput, int i) {}

    void write(DataOutput dataoutput) {}

    public byte getTypeId() {
        return (byte) 0;
    }

    public String toString() {
        return "END";
    }

    public NBTBase clone() {
        return new NBTTagEnd();
    }
}
