package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagFloat extends NBTBase {

    public float data;

    public NBTTagFloat(String s) {
        super(s);
    }

    public NBTTagFloat(String s, float f) {
        super(s);
        this.data = f;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeFloat(this.data);
    }

    void load(DataInput datainput) {
        this.data = datainput.readFloat();
    }

    public byte getTypeId() {
        return (byte) 5;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagFloat(this.getName(), this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagFloat nbttagfloat = (NBTTagFloat) object;

            return this.data == nbttagfloat.data;
        } else {
            return false;
        }
    }
}
