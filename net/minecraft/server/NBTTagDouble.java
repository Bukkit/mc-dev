package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagDouble extends NBTBase {

    public double a;

    public NBTTagDouble(String s) {
        super(s);
    }

    public NBTTagDouble(String s, double d0) {
        super(s);
        this.a = d0;
    }

    void a(DataOutput dataoutput) {
        dataoutput.writeDouble(this.a);
    }

    void a(DataInput datainput) {
        this.a = datainput.readDouble();
    }

    public byte a() {
        return (byte) 6;
    }

    public String toString() {
        return "" + this.a;
    }

    public NBTBase b() {
        return new NBTTagDouble(this.c(), this.a);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagDouble nbttagdouble = (NBTTagDouble) object;

            return this.a == nbttagdouble.a;
        } else {
            return false;
        }
    }
}
