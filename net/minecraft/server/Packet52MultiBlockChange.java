package net.minecraft.server;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet52MultiBlockChange extends Packet {

    public int a;
    public int b;
    public byte[] c;
    public int d;
    private static byte[] e = new byte[0];

    public Packet52MultiBlockChange() {
        this.lowPriority = true;
    }

    public Packet52MultiBlockChange(int i, int j, short[] ashort, int k, World world) {
        this.lowPriority = true;
        this.a = i;
        this.b = j;
        this.d = k;
        int l = 4 * k;
        Chunk chunk = world.getChunkAt(i, j);

        try {
            if (k >= 64) {
                this.m.info("ChunkTilesUpdatePacket compress " + k);
                if (e.length < l) {
                    e = new byte[l];
                }
            } else {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(l);
                DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);

                for (int i1 = 0; i1 < k; ++i1) {
                    int j1 = ashort[i1] >> 12 & 15;
                    int k1 = ashort[i1] >> 8 & 15;
                    int l1 = ashort[i1] & 255;

                    dataoutputstream.writeShort(ashort[i1]);
                    dataoutputstream.writeShort((short) ((chunk.getTypeId(j1, l1, k1) & 4095) << 4 | chunk.getData(j1, l1, k1) & 15));
                }

                this.c = bytearrayoutputstream.toByteArray();
                if (this.c.length != l) {
                    throw new RuntimeException("Expected length " + l + " doesn\'t match received length " + this.c.length);
                }
            }
        } catch (IOException ioexception) {
            this.m.severe("Couldn\'t create chunk packet", ioexception);
            this.c = null;
        }
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readInt();
        this.d = datainput.readShort() & '\uffff';
        int i = datainput.readInt();

        if (i > 0) {
            this.c = new byte[i];
            datainput.readFully(this.c);
        }
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeInt(this.b);
        dataoutput.writeShort((short) this.d);
        if (this.c != null) {
            dataoutput.writeInt(this.c.length);
            dataoutput.write(this.c);
        } else {
            dataoutput.writeInt(0);
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 10 + this.d * 4;
    }
}
