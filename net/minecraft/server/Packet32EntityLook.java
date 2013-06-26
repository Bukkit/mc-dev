package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet32EntityLook extends Packet30Entity {

    public Packet32EntityLook() {
        this.g = true;
    }

    public Packet32EntityLook(int i, byte b0, byte b1) {
        super(i);
        this.e = b0;
        this.f = b1;
        this.g = true;
    }

    public void a(DataInput datainput) {
        super.a(datainput);
        this.e = datainput.readByte();
        this.f = datainput.readByte();
    }

    public void a(DataOutput dataoutput) {
        super.a(dataoutput);
        dataoutput.writeByte(this.e);
        dataoutput.writeByte(this.f);
    }

    public int a() {
        return 6;
    }
}
