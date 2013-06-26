package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet31RelEntityMove extends Packet30Entity {

    public Packet31RelEntityMove() {}

    public Packet31RelEntityMove(int i, byte b0, byte b1, byte b2) {
        super(i);
        this.b = b0;
        this.c = b1;
        this.d = b2;
    }

    public void a(DataInput datainput) {
        super.a(datainput);
        this.b = datainput.readByte();
        this.c = datainput.readByte();
        this.d = datainput.readByte();
    }

    public void a(DataOutput dataoutput) {
        super.a(dataoutput);
        dataoutput.writeByte(this.b);
        dataoutput.writeByte(this.c);
        dataoutput.writeByte(this.d);
    }

    public int a() {
        return 7;
    }
}
