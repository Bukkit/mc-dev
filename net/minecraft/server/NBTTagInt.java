package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagInt extends NBTNumber {

    private int data;

    NBTTagInt() {}

    public NBTTagInt(int i) {
        this.data = i;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeInt(this.data);
    }

    void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
        nbtreadlimiter.a(32L);
        this.data = datainput.readInt();
    }

    public byte getTypeId() {
        return (byte) 3;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagInt(this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagInt nbttagint = (NBTTagInt) object;

            return this.data == nbttagint.data;
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
        return (short) (this.data & '\uffff');
    }

    public byte f() {
        return (byte) (this.data & 255);
    }

    public double g() {
        return (double) this.data;
    }

    public float h() {
        return (float) this.data;
    }
}
