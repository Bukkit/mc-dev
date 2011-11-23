package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet52MultiBlockChange extends Packet {

    public int a;
    public int b;
    public short[] c;
    public byte[] d;
    public byte[] e;
    public int f;

    public Packet52MultiBlockChange() {
        this.l = true;
    }

    public Packet52MultiBlockChange(int i, int j, short[] ashort, int k, World world) {
        this.l = true;
        this.a = i;
        this.b = j;
        this.f = k;
        this.c = new short[k];
        this.d = new byte[k];
        this.e = new byte[k];
        Chunk chunk = world.getChunkAt(i, j);

        for (int l = 0; l < k; ++l) {
            int i1 = ashort[l] >> 12 & 15;
            int j1 = ashort[l] >> 8 & 15;
            int k1 = ashort[l] & 255;

            this.c[l] = ashort[l];
            this.d[l] = (byte) chunk.getTypeId(i1, k1, j1);
            this.e[l] = (byte) chunk.getData(i1, k1, j1);
        }
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readInt();
        this.f = datainputstream.readShort() & '\uffff';
        this.c = new short[this.f];
        this.d = new byte[this.f];
        this.e = new byte[this.f];

        for (int i = 0; i < this.f; ++i) {
            this.c[i] = datainputstream.readShort();
        }

        datainputstream.readFully(this.d);
        datainputstream.readFully(this.e);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeInt(this.b);
        dataoutputstream.writeShort((short) this.f);

        for (int i = 0; i < this.f; ++i) {
            dataoutputstream.writeShort(this.c[i]);
        }

        dataoutputstream.write(this.d);
        dataoutputstream.write(this.e);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 10 + this.f * 4;
    }
}
