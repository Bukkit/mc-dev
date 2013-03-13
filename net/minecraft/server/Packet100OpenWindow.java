package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet100OpenWindow extends Packet {

    public int a;
    public int b;
    public String c;
    public int d;
    public boolean e;

    public Packet100OpenWindow() {}

    public Packet100OpenWindow(int i, int j, String s, int k, boolean flag) {
        this.a = i;
        this.b = j;
        this.c = s;
        this.d = k;
        this.e = flag;
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readByte() & 255;
        this.b = datainputstream.readByte() & 255;
        this.c = a(datainputstream, 32);
        this.d = datainputstream.readByte() & 255;
        this.e = datainputstream.readBoolean();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a & 255);
        dataoutputstream.writeByte(this.b & 255);
        a(this.c, dataoutputstream);
        dataoutputstream.writeByte(this.d & 255);
        dataoutputstream.writeBoolean(this.e);
    }

    public int a() {
        return 4 + this.c.length();
    }
}
