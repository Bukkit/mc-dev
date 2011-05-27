package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagByteArray extends NBTBase {

    public byte[] a;

    public NBTTagByteArray() {}

    public NBTTagByteArray(byte[] abyte) {
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
}
