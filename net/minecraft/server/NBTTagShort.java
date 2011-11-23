package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagShort extends NBTBase {

    public short a;

    public NBTTagShort(String s) {
        super(s);
    }

    public NBTTagShort(String s, short short1) {
        super(s);
        this.a = short1;
    }

    void a(DataOutput dataoutput) {
        dataoutput.writeShort(this.a);
    }

    void a(DataInput datainput) {
        this.a = datainput.readShort();
    }

    public byte a() {
        return (byte) 2;
    }

    public String toString() {
        return "" + this.a;
    }

    public NBTBase b() {
        return new NBTTagShort(this.c(), this.a);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagShort nbttagshort = (NBTTagShort) object;

            return this.a == nbttagshort.a;
        } else {
            return false;
        }
    }
}
