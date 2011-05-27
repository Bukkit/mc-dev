package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet1Login extends Packet {

    public int a;
    public String b;
    public String c;
    public long d;
    public byte e;

    public Packet1Login() {}

    public Packet1Login(String s, String s1, int i, long j, byte b0) {
        this.b = s;
        this.c = s1;
        this.a = i;
        this.d = j;
        this.e = b0;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readUTF();
        this.c = datainputstream.readUTF();
        this.d = datainputstream.readLong();
        this.e = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeUTF(this.b);
        dataoutputstream.writeUTF(this.c);
        dataoutputstream.writeLong(this.d);
        dataoutputstream.writeByte(this.e);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 4 + this.b.length() + this.c.length() + 4 + 5;
    }
}
