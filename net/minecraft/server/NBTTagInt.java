package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagInt extends NBTBase {

    public int a;

    public NBTTagInt() {}

    public NBTTagInt(int i) {
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
}
