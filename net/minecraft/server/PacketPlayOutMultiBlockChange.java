package net.minecraft.server;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PacketPlayOutMultiBlockChange extends Packet {

    private static final Logger a = LogManager.getLogger();
    private ChunkCoordIntPair b;
    private byte[] c;
    private int d;

    public PacketPlayOutMultiBlockChange() {}

    public PacketPlayOutMultiBlockChange(int i, short[] ashort, Chunk chunk) {
        this.b = new ChunkCoordIntPair(chunk.locX, chunk.locZ);
        this.d = i;
        int j = 4 * i;

        try {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(j);
            DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);

            for (int k = 0; k < i; ++k) {
                int l = ashort[k] >> 12 & 15;
                int i1 = ashort[k] >> 8 & 15;
                int j1 = ashort[k] & 255;

                dataoutputstream.writeShort(ashort[k]);
                dataoutputstream.writeShort((short) ((Block.b(chunk.getType(l, j1, i1)) & 4095) << 4 | chunk.getData(l, j1, i1) & 15));
            }

            this.c = bytearrayoutputstream.toByteArray();
            if (this.c.length != j) {
                throw new RuntimeException("Expected length " + j + " doesn\'t match received length " + this.c.length);
            }
        } catch (IOException ioexception) {
            a.error("Couldn\'t create bulk block update packet", ioexception);
            this.c = null;
        }
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.b = new ChunkCoordIntPair(packetdataserializer.readInt(), packetdataserializer.readInt());
        this.d = packetdataserializer.readShort() & '\uffff';
        int i = packetdataserializer.readInt();

        if (i > 0) {
            this.c = new byte[i];
            packetdataserializer.readBytes(this.c);
        }
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.b.x);
        packetdataserializer.writeInt(this.b.z);
        packetdataserializer.writeShort((short) this.d);
        if (this.c != null) {
            packetdataserializer.writeInt(this.c.length);
            packetdataserializer.writeBytes(this.c);
        } else {
            packetdataserializer.writeInt(0);
        }
    }

    public void a(PacketPlayOutListener packetplayoutlistener) {
        packetplayoutlistener.a(this);
    }

    public String b() {
        return String.format("xc=%d, zc=%d, count=%d", new Object[] { Integer.valueOf(this.b.x), Integer.valueOf(this.b.z), Integer.valueOf(this.d)});
    }

    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }
}
