package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

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

    public void a(DataInputStream datainputstream) {
        this.a = a(datainputstream, 32);
        this.b = datainputstream.readInt();
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readFloat();
        this.f = datainputstream.readUnsignedByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.a, dataoutputstream);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeFloat(this.e);
        dataoutputstream.writeByte(this.f);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 24;
    }
}
