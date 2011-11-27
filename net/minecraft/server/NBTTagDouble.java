package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagDouble extends NBTBase {

    public double data;

    public NBTTagDouble(String s) {
        super(s);
    }

    public NBTTagDouble(String s, double d0) {
        super(s);
        this.data = d0;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeDouble(this.data);
    }

    void load(DataInput datainput) {
        this.data = datainput.readDouble();
    }

    public byte getTypeId() {
        return (byte) 6;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagDouble(this.getName(), this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagDouble nbttagdouble = (NBTTagDouble) object;

            return this.data == nbttagdouble.data;
        } else {
            return false;
        }
    }
}
