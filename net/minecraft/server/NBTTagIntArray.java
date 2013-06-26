package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Arrays;

public class NBTTagIntArray extends NBTBase {

    public int[] data;

    public NBTTagIntArray(String s) {
        super(s);
    }

    public NBTTagIntArray(String s, int[] aint) {
        super(s);
        this.data = aint;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeInt(this.data.length);

        for (int i = 0; i < this.data.length; ++i) {
            dataoutput.writeInt(this.data[i]);
        }
    }

    void load(DataInput datainput, int i) {
        int j = datainput.readInt();

        this.data = new int[j];

        for (int k = 0; k < j; ++k) {
            this.data[k] = datainput.readInt();
        }
    }

    public byte getTypeId() {
        return (byte) 11;
    }

    public String toString() {
        return "[" + this.data.length + " bytes]";
    }

    public NBTBase clone() {
        int[] aint = new int[this.data.length];

        System.arraycopy(this.data, 0, aint, 0, this.data.length);
        return new NBTTagIntArray(this.getName(), aint);
    }

    public boolean equals(Object object) {
        if (!super.equals(object)) {
            return false;
        } else {
            NBTTagIntArray nbttagintarray = (NBTTagIntArray) object;

            return this.data == null && nbttagintarray.data == null || this.data != null && Arrays.equals(this.data, nbttagintarray.data);
        }
    }

    public int hashCode() {
        return super.hashCode() ^ Arrays.hashCode(this.data);
    }
}
