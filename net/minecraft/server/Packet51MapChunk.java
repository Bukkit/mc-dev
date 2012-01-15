package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Packet51MapChunk extends Packet {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public byte[] buffer;
    private int size;

    public Packet51MapChunk() {
        this.lowPriority = true;
    }

    public Packet51MapChunk(int i, int j, int k, int l, int i1, int j1, World world) {
        this.lowPriority = true;
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
        this.e = i1;
        this.f = j1;
        byte[] abyte = world.getMultiChunkData(i, j, k, l, i1, j1);
        Deflater deflater = new Deflater(-1);

        try {
            deflater.setInput(abyte);
            deflater.finish();
            this.buffer = new byte[l * i1 * j1 * 5 / 2];
            this.size = deflater.deflate(this.buffer);
        } finally {
            deflater.end();
        }
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readShort();
        this.c = datainputstream.readInt();
        this.d = datainputstream.read() + 1;
        this.e = datainputstream.read() + 1;
        this.f = datainputstream.read() + 1;
        this.size = datainputstream.readInt();
        byte[] abyte = new byte[this.size];

        datainputstream.readFully(abyte);
        this.buffer = new byte[this.d * this.e * this.f * 5 / 2];
        Inflater inflater = new Inflater();

        inflater.setInput(abyte);

        try {
            inflater.inflate(this.buffer);
        } catch (DataFormatException dataformatexception) {
            throw new IOException("Bad compressed data format");
        } finally {
            inflater.end();
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeShort(this.b);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.write(this.d - 1);
        dataoutputstream.write(this.e - 1);
        dataoutputstream.write(this.f - 1);
        dataoutputstream.writeInt(this.size);
        dataoutputstream.write(this.buffer, 0, this.size);
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 17 + this.size;
    }
}
