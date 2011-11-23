package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagByteArray extends NBTBase {

    public byte[] a;

    public NBTTagByteArray(String s) {
        super(s);
    }

    public NBTTagByteArray(String s, byte[] abyte) {
        super(s);
        this.a = abyte;
    }

    void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a.length);
        dataoutput.write(this.a);
    }

    void a(DataInput datainput) {
        int i = datainput.readInt();

        this.a = new byte[i];
        datainput.readFully(this.a);
    }

    public byte a() {
        return (byte) 7;
    }

    public String toString() {
        return "[" + this.a.length + " bytes]";
    }

    public boolean equals(Object object) {
        if (!super.equals(object)) {
            return false;
        } else {
            NBTTagByteArray nbttagbytearray = (NBTTagByteArray) object;

            return this.a == null && nbttagbytearray.a == null || this.a != null && this.a.equals(nbttagbytearray.a);
        }
    }

    public NBTBase b() {
        byte[] abyte = new byte[this.a.length];

        System.arraycopy(this.a, 0, abyte, 0, this.a.length);
        return new NBTTagByteArray(this.c(), abyte);
    }
}
