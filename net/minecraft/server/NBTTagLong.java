package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagLong extends NBTNumber {

    private long data;

    NBTTagLong() {}

    public NBTTagLong(long i) {
        this.data = i;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeLong(this.data);
    }

    void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
        nbtreadlimiter.a(64L);
        this.data = datainput.readLong();
    }

    public byte getTypeId() {
        return (byte) 4;
    }

    public String toString() {
        return "" + this.data + "L";
    }

    public NBTBase clone() {
        return new NBTTagLong(this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagLong nbttaglong = (NBTTagLong) object;

            return this.data == nbttaglong.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ (int) (this.data ^ this.data >>> 32);
    }

    public long c() {
        return this.data;
    }

    public int d() {
        return (int) (this.data & -1L);
    }

    public short e() {
        return (short) ((int) (this.data & 65535L));
    }

    public byte f() {
        return (byte) ((int) (this.data & 255L));
    }

    public double g() {
        return (double) this.data;
    }

    public float h() {
        return (float) this.data;
    }
}
