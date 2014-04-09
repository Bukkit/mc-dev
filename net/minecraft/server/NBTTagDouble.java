package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagDouble extends NBTNumber {

    private double data;

    NBTTagDouble() {}

    public NBTTagDouble(double d0) {
        this.data = d0;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeDouble(this.data);
    }

    void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
        nbtreadlimiter.a(64L);
        this.data = datainput.readDouble();
    }

    public byte getTypeId() {
        return (byte) 6;
    }

    public String toString() {
        return "" + this.data + "d";
    }

    public NBTBase clone() {
        return new NBTTagDouble(this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagDouble nbttagdouble = (NBTTagDouble) object;

            return this.data == nbttagdouble.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        long i = Double.doubleToLongBits(this.data);

        return super.hashCode() ^ (int) (i ^ i >>> 32);
    }

    public long c() {
        return (long) Math.floor(this.data);
    }

    public int d() {
        return MathHelper.floor(this.data);
    }

    public short e() {
        return (short) (MathHelper.floor(this.data) & '\uffff');
    }

    public byte f() {
        return (byte) (MathHelper.floor(this.data) & 255);
    }

    public double g() {
        return this.data;
    }

    public float h() {
        return (float) this.data;
    }
}
