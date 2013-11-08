package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagShort extends NBTNumber {

    private short data;

    public NBTTagShort() {}

    public NBTTagShort(short short1) {
        this.data = short1;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeShort(this.data);
    }

    void load(DataInput datainput, int i) {
        this.data = datainput.readShort();
    }

    public byte getTypeId() {
        return (byte) 2;
    }

    public String toString() {
        return "" + this.data + "s";
    }

    public NBTBase clone() {
        return new NBTTagShort(this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagShort nbttagshort = (NBTTagShort) object;

            return this.data == nbttagshort.data;
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
        return this.data;
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
