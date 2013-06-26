package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet27PlayerInput extends Packet {

    private float a;
    private float b;
    private boolean c;
    private boolean d;

    public Packet27PlayerInput() {}

    public void a(DataInput datainput) {
        this.a = datainput.readFloat();
        this.b = datainput.readFloat();
        this.c = datainput.readBoolean();
        this.d = datainput.readBoolean();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeFloat(this.a);
        dataoutput.writeFloat(this.b);
        dataoutput.writeBoolean(this.c);
        dataoutput.writeBoolean(this.d);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 10;
    }

    public float d() {
        return this.a;
    }

    public float f() {
        return this.b;
    }

    public boolean g() {
        return this.c;
    }

    public boolean h() {
        return this.d;
    }
}
