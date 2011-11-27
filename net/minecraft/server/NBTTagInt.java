package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagInt extends NBTBase {

    public int data;

    public NBTTagInt(String s) {
        super(s);
    }

    public NBTTagInt(String s, int i) {
        super(s);
        this.data = i;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeInt(this.data);
    }

    void load(DataInput datainput) {
        this.data = datainput.readInt();
    }

    public byte getTypeId() {
        return (byte) 3;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagInt(this.getName(), this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagInt nbttagint = (NBTTagInt) object;

            return this.data == nbttagint.data;
        } else {
            return false;
        }
    }
}
