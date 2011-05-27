package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagDouble extends NBTBase {

    public double a;

    public NBTTagDouble() {}

    public NBTTagDouble(double d0) {
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
}
