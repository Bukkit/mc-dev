package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet204LocaleAndViewDistance extends Packet {

    private String a;
    private int b;
    private int c;
    private boolean d;
    private int e;
    private boolean f;

    public Packet204LocaleAndViewDistance() {}

    public void a(DataInput datainput) {
        this.a = a(datainput, 7);
        this.b = datainput.readByte();
        byte b0 = datainput.readByte();

        this.c = b0 & 7;
        this.d = (b0 & 8) == 8;
        this.e = datainput.readByte();
        this.f = datainput.readBoolean();
    }

    public void a(DataOutput dataoutput) {
        a(this.a, dataoutput);
        dataoutput.writeByte(this.b);
        dataoutput.writeByte(this.c | (this.d ? 1 : 0) << 3);
        dataoutput.writeByte(this.e);
        dataoutput.writeBoolean(this.f);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 7;
    }

    public String d() {
        return this.a;
    }

    public int f() {
        return this.b;
    }

    public int g() {
        return this.c;
    }

    public boolean h() {
        return this.d;
    }

    public int i() {
        return this.e;
    }

    public boolean j() {
        return this.f;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        return true;
    }
}
