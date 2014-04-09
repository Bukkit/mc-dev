package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagByte extends NBTNumber {

    private byte data;

    NBTTagByte() {}

    public NBTTagByte(byte b0) {
        this.data = b0;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeByte(this.data);
    }

    void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
        nbtreadlimiter.a(8L);
        this.data = datainput.readByte();
    }

    public byte getTypeId() {
        return (byte) 1;
    }

    public String toString() {
        return "" + this.data + "b";
    }

    public NBTBase clone() {
        return new NBTTagByte(this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagByte nbttagbyte = (NBTTagByte) object;

            return this.data == nbttagbyte.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ this.data;
    }

    public long c() {
        return (long) this.data;
    }

    public int d() {
        return this.data;
    }

    public short e() {
        return (short) this.data;
    }

    public byte f() {
        return this.data;
    }

    public double g() {
        return (double) this.data;
    }

    public float h() {
        return (float) this.data;
    }
}
