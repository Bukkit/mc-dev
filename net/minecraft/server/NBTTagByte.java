package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagByte extends NBTBase {

    public byte data;

    public NBTTagByte(String s) {
        super(s);
    }

    public NBTTagByte(String s, byte b0) {
        super(s);
        this.data = b0;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeByte(this.data);
    }

    void load(DataInput datainput) {
        this.data = datainput.readByte();
    }

    public byte getTypeId() {
        return (byte) 1;
    }

    public String toString() {
        return "" + this.data;
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagByte nbttagbyte = (NBTTagByte) object;

            return this.data == nbttagbyte.data;
        } else {
            return false;
        }
    }

    public NBTBase clone() {
        return new NBTTagByte(this.getName(), this.data);
    }
}
