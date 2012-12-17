package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet204LocaleAndViewDistance extends Packet {

    private String a;
    private int b;
    private int c;
    private boolean d;
    private int e;
    private boolean f;

    public Packet204LocaleAndViewDistance() {}

    public void a(DataInputStream datainputstream) {
        this.a = a(datainputstream, 7);
        this.b = datainputstream.readByte();
        byte b0 = datainputstream.readByte();

        this.c = b0 & 7;
        this.d = (b0 & 8) == 8;
        this.e = datainputstream.readByte();
        this.f = datainputstream.readBoolean();
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.a, dataoutputstream);
        dataoutputstream.writeByte(this.b);
        dataoutputstream.writeByte(this.c | (this.d ? 1 : 0) << 3);
        dataoutputstream.writeByte(this.e);
        dataoutputstream.writeBoolean(this.f);
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
