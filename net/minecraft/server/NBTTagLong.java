package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagLong extends NBTBase {

    public long a;

    public NBTTagLong(String s) {
        super(s);
    }

    public NBTTagLong(String s, long i) {
        super(s);
        this.a = i;
    }

    void a(DataOutput dataoutput) {
        dataoutput.writeLong(this.a);
    }

    void a(DataInput datainput) {
        this.a = datainput.readLong();
    }

    public byte a() {
        return (byte) 4;
    }

    public String toString() {
        return "" + this.a;
    }

    public NBTBase b() {
        return new NBTTagLong(this.c(), this.a);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagLong nbttaglong = (NBTTagLong) object;

            return this.a == nbttaglong.a;
        } else {
            return false;
        }
    }
}
