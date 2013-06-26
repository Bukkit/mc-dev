package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet33RelEntityMoveLook extends Packet30Entity {

    public Packet33RelEntityMoveLook() {
        this.g = true;
    }

    public Packet33RelEntityMoveLook(int i, byte b0, byte b1, byte b2, byte b3, byte b4) {
        super(i);
        this.b = b0;
        this.c = b1;
        this.d = b2;
        this.e = b3;
        this.f = b4;
        this.g = true;
    }

    public void a(DataInput datainput) {
        super.a(datainput);
        this.b = datainput.readByte();
        this.c = datainput.readByte();
        this.d = datainput.readByte();
        this.e = datainput.readByte();
        this.f = datainput.readByte();
    }

    public void a(DataOutput dataoutput) {
        super.a(dataoutput);
        dataoutput.writeByte(this.b);
        dataoutput.writeByte(this.c);
        dataoutput.writeByte(this.d);
        dataoutput.writeByte(this.e);
        dataoutput.writeByte(this.f);
    }

    public int a() {
        return 9;
    }
}
