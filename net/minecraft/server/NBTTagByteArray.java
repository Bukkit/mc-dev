package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Arrays;

public class NBTTagByteArray extends NBTBase {

    private byte[] data;

    NBTTagByteArray() {}

    public NBTTagByteArray(byte[] abyte) {
        this.data = abyte;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeInt(this.data.length);
        dataoutput.write(this.data);
    }

    void load(DataInput datainput, int i) {
        int j = datainput.readInt();

        this.data = new byte[j];
        datainput.readFully(this.data);
    }

    public byte getTypeId() {
        return (byte) 7;
    }

    public String toString() {
        return "[" + this.data.length + " bytes]";
    }

    public NBTBase clone() {
        byte[] abyte = new byte[this.data.length];

        System.arraycopy(this.data, 0, abyte, 0, this.data.length);
        return new NBTTagByteArray(abyte);
    }

    public boolean equals(Object object) {
        return super.equals(object) ? Arrays.equals(this.data, ((NBTTagByteArray) object).data) : false;
    }

    public int hashCode() {
        return super.hashCode() ^ Arrays.hashCode(this.data);
    }

    public byte[] c() {
        return this.data;
    }
}
