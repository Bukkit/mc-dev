package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet130UpdateSign extends Packet {

    public int a;
    public int b;
    public int c;
    public String[] d;

    public Packet130UpdateSign() {
        this.k = true;
    }

    public Packet130UpdateSign(int i, int j, int k, String[] astring) {
        this.k = true;
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = astring;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readShort();
        this.c = datainputstream.readInt();
        this.d = new String[4];

        for (int i = 0; i < 4; ++i) {
            this.d[i] = datainputstream.readUTF();
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeShort(this.b);
        dataoutputstream.writeInt(this.c);

        for (int i = 0; i < 4; ++i) {
            dataoutputstream.writeUTF(this.d[i]);
        }
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        int i = 0;

        for (int j = 0; j < 4; ++j) {
            i += this.d[j].length();
        }

        return i;
    }
}
