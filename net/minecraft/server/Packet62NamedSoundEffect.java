package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet62NamedSoundEffect extends Packet {

    private String a;
    private int b;
    private int c = Integer.MAX_VALUE;
    private int d;
    private float e;
    private int f;

    public Packet62NamedSoundEffect() {}

    public Packet62NamedSoundEffect(String s, double d0, double d1, double d2, float f, float f1) {
        this.a = s;
        this.b = (int) (d0 * 8.0D);
        this.c = (int) (d1 * 8.0D);
        this.d = (int) (d2 * 8.0D);
        this.e = f;
        this.f = (int) (f1 * 63.0F);
        if (this.f < 0) {
            this.f = 0;
        }

        if (this.f > 255) {
            this.f = 255;
        }
    }

    public void a(DataInput datainput) {
        this.a = a(datainput, 256);
        this.b = datainput.readInt();
        this.c = datainput.readInt();
        this.d = datainput.readInt();
        this.e = datainput.readFloat();
        this.f = datainput.readUnsignedByte();
    }

    public void a(DataOutput dataoutput) {
        a(this.a, dataoutput);
        dataoutput.writeInt(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.writeInt(this.d);
        dataoutput.writeFloat(this.e);
        dataoutput.writeByte(this.f);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 24;
    }
}
