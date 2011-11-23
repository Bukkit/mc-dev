package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagString extends NBTBase {

    public String a;

    public NBTTagString(String s) {
        super(s);
    }

    public NBTTagString(String s, String s1) {
        super(s);
        this.a = s1;
        if (s1 == null) {
            throw new IllegalArgumentException("Empty string not allowed");
        }
    }

    void a(DataOutput dataoutput) {
        dataoutput.writeUTF(this.a);
    }

    void a(DataInput datainput) {
        this.a = datainput.readUTF();
    }

    public byte a() {
        return (byte) 8;
    }

    public String toString() {
        return "" + this.a;
    }

    public NBTBase b() {
        return new NBTTagString(this.c(), this.a);
    }

    public boolean equals(Object object) {
        if (!super.equals(object)) {
            return false;
        } else {
            NBTTagString nbttagstring = (NBTTagString) object;

            return this.a == null && nbttagstring.a == null || this.a != null && this.a.equals(nbttagstring.a);
        }
    }
}
