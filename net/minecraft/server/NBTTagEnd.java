package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagEnd extends NBTBase {

    public NBTTagEnd() {
        super((String) null);
    }

    void a(DataInput datainput) {}

    void a(DataOutput dataoutput) {}

    public byte a() {
        return (byte) 0;
    }

    public String toString() {
        return "END";
    }

    public NBTBase b() {
        return new NBTTagEnd();
    }

    public boolean equals(Object object) {
        return super.equals(object);
    }
}
