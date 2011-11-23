package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagFloat extends NBTBase {

    public float a;

    public NBTTagFloat(String s) {
        super(s);
    }

    public NBTTagFloat(String s, float f) {
        super(s);
        this.a = f;
    }

    void a(DataOutput dataoutput) {
        dataoutput.writeFloat(this.a);
    }

    void a(DataInput datainput) {
        this.a = datainput.readFloat();
    }

    public byte a() {
        return (byte) 5;
    }

    public String toString() {
        return "" + this.a;
    }

    public NBTBase b() {
        return new NBTTagFloat(this.c(), this.a);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagFloat nbttagfloat = (NBTTagFloat) object;

            return this.a == nbttagfloat.a;
        } else {
            return false;
        }
    }
}
