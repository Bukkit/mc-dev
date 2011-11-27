package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagShort extends NBTBase {

    public short data;

    public NBTTagShort(String s) {
        super(s);
    }

    public NBTTagShort(String s, short short1) {
        super(s);
        this.data = short1;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeShort(this.data);
    }

    void load(DataInput datainput) {
        this.data = datainput.readShort();
    }

    public byte getTypeId() {
        return (byte) 2;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagShort(this.getName(), this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagShort nbttagshort = (NBTTagShort) object;

            return this.data == nbttagshort.data;
        } else {
            return false;
        }
    }
}
