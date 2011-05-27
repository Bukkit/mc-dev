package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagFloat extends NBTBase {

    public float a;

    public NBTTagFloat() {}

    public NBTTagFloat(float f) {
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
}
