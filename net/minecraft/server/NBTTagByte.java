package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagByte extends NBTBase {

    public byte a;

    public NBTTagByte(String s) {
        super(s);
    }

    public NBTTagByte(String s, byte b0) {
        super(s);
        this.a = b0;
    }

    void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a);
    }

    void a(DataInput datainput) {
        this.a = datainput.readByte();
    }

    public byte a() {
        return (byte) 1;
    }

    public String toString() {
        return "" + this.a;
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagByte nbttagbyte = (NBTTagByte) object;

            return this.a == nbttagbyte.a;
        } else {
            return false;
        }
    }

    public NBTBase b() {
        return new NBTTagByte(this.c(), this.a);
    }
}
