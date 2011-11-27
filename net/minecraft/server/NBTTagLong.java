package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagLong extends NBTBase {

    public long data;

    public NBTTagLong(String s) {
        super(s);
    }

    public NBTTagLong(String s, long i) {
        super(s);
        this.data = i;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeLong(this.data);
    }

    void load(DataInput datainput) {
        this.data = datainput.readLong();
    }

    public byte getTypeId() {
        return (byte) 4;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagLong(this.getName(), this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagLong nbttaglong = (NBTTagLong) object;

            return this.data == nbttaglong.data;
        } else {
            return false;
        }
    }
}
