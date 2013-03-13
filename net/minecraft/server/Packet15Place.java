package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet15Place extends Packet {

    private int a;
    private int b;
    private int c;
    private int d;
    private ItemStack e;
    private float f;
    private float g;
    private float h;

    public Packet15Place() {}

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.read();
        this.c = datainputstream.readInt();
        this.d = datainputstream.read();
        this.e = c(datainputstream);
        this.f = (float) datainputstream.read() / 16.0F;
        this.g = (float) datainputstream.read() / 16.0F;
        this.h = (float) datainputstream.read() / 16.0F;
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.write(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.write(this.d);
        a(this.e, dataoutputstream);
        dataoutputstream.write((int) (this.f * 16.0F));
        dataoutputstream.write((int) (this.g * 16.0F));
        dataoutputstream.write((int) (this.h * 16.0F));
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 19;
    }

    public int d() {
        return this.a;
    }

    public int f() {
        return this.b;
    }

    public int g() {
        return this.c;
    }

    public int getFace() {
        return this.d;
    }

    public ItemStack getItemStack() {
        return this.e;
    }

    public float j() {
        return this.f;
    }

    public float k() {
        return this.g;
    }

    public float l() {
        return this.h;
    }
}
