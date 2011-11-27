package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagByteArray extends NBTBase {

    public byte[] data;

    public NBTTagByteArray(String s) {
        super(s);
    }

    public NBTTagByteArray(String s, byte[] abyte) {
        super(s);
        this.data = abyte;
    }

    void write(DataOutput dataoutput) {
        dataoutput.writeInt(this.data.length);
        dataoutput.write(this.data);
    }

    void load(DataInput datainput) {
        int i = datainput.readInt();

        this.data = new byte[i];
        datainput.readFully(this.data);
    }

    public byte getTypeId() {
        return (byte) 7;
    }

    public String toString() {
        return "[" + this.data.length + " bytes]";
    }

    public boolean equals(Object object) {
        if (!super.equals(object)) {
            return false;
        } else {
            NBTTagByteArray nbttagbytearray = (NBTTagByteArray) object;

            return this.data == null && nbttagbytearray.data == null || this.data != null && this.data.equals(nbttagbytearray.data);
        }
    }

    public NBTBase clone() {
        byte[] abyte = new byte[this.data.length];

        System.arraycopy(this.data, 0, abyte, 0, this.data.length);
        return new NBTTagByteArray(this.getName(), abyte);
    }
}
