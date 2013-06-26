package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet2Handshake extends Packet {

    private int a;
    private String b;
    private String c;
    private int d;

    public Packet2Handshake() {}

    public void a(DataInput datainput) {
        this.a = datainput.readByte();
        this.b = a(datainput, 16);
        this.c = a(datainput, 255);
        this.d = datainput.readInt();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a);
        a(this.b, dataoutput);
        a(this.c, dataoutput);
        dataoutput.writeInt(this.d);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 3 + 2 * this.b.length();
    }

    public int d() {
        return this.a;
    }

    public String f() {
        return this.b;
    }
}
