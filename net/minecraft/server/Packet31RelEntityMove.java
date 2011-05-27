package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet31RelEntityMove extends Packet30Entity {

    public Packet31RelEntityMove() {}

    public Packet31RelEntityMove(int i, byte b0, byte b1, byte b2) {
        super(i);
        this.b = b0;
        this.c = b1;
        this.d = b2;
    }

    public void a(DataInputStream datainputstream) {
        super.a(datainputstream);
        this.b = datainputstream.readByte();
        this.c = datainputstream.readByte();
        this.d = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        super.a(dataoutputstream);
        dataoutputstream.writeByte(this.b);
        dataoutputstream.writeByte(this.c);
        dataoutputstream.writeByte(this.d);
    }

    public int a() {
        return 7;
    }
}
