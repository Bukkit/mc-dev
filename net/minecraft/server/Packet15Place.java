package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

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

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readUnsignedByte();
        this.c = datainput.readInt();
        this.d = datainput.readUnsignedByte();
        this.e = c(datainput);
        this.f = (float) datainput.readUnsignedByte() / 16.0F;
        this.g = (float) datainput.readUnsignedByte() / 16.0F;
        this.h = (float) datainput.readUnsignedByte() / 16.0F;
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.write(this.b);
        dataoutput.writeInt(this.c);
        dataoutput.write(this.d);
        a(this.e, dataoutput);
        dataoutput.write((int) (this.f * 16.0F));
        dataoutput.write((int) (this.g * 16.0F));
        dataoutput.write((int) (this.h * 16.0F));
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
