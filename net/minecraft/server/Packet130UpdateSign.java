package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet130UpdateSign extends Packet {

    public int x;
    public int y;
    public int z;
    public String[] lines;

    public Packet130UpdateSign() {
        this.l = true;
    }

    public Packet130UpdateSign(int i, int j, int k, String[] astring) {
        this.l = true;
        this.x = i;
        this.y = j;
        this.z = k;
        this.lines = astring;
    }

    public void a(DataInputStream datainputstream) {
        this.x = datainputstream.readInt();
        this.y = datainputstream.readShort();
        this.z = datainputstream.readInt();
        this.lines = new String[4];

        for (int i = 0; i < 4; ++i) {
            this.lines[i] = a(datainputstream, 15);
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.x);
        dataoutputstream.writeShort(this.y);
        dataoutputstream.writeInt(this.z);

        for (int i = 0; i < 4; ++i) {
            a(this.lines[i], dataoutputstream);
        }
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        int i = 0;

        for (int j = 0; j < 4; ++j) {
            i += this.lines[j].length();
        }

        return i;
    }
}
