package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagLong extends NBTBase {

    public long a;

    public NBTTagLong() {}

    public NBTTagLong(long i) {
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
}
