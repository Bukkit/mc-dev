package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagInt extends NBTBase {

    public int a;

    public NBTTagInt(String s) {
        super(s);
    }

    public NBTTagInt(String s, int i) {
        super(s);
        this.a = i;
    }

    void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
    }

    void a(DataInput datainput) {
        this.a = datainput.readInt();
    }

    public byte a() {
        return (byte) 3;
    }

    public String toString() {
        return "" + this.a;
    }

    public NBTBase b() {
        return new NBTTagInt(this.c(), this.a);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagInt nbttagint = (NBTTagInt) object;

            return this.a == nbttagint.a;
        } else {
            return false;
        }
    }
}
