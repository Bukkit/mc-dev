package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet131ItemData extends Packet {

    public short a;
    public short b;
    public byte[] c;

    public Packet131ItemData() {
        this.lowPriority = true;
    }

    public Packet131ItemData(short short1, short short2, byte[] abyte) {
        this.lowPriority = true;
        this.a = short1;
        this.b = short2;
        this.c = abyte;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readShort();
        this.b = datainputstream.readShort();
        this.c = new byte[datainputstream.readUnsignedShort()];
        datainputstream.readFully(this.c);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeShort(this.a);
        dataoutputstream.writeShort(this.b);
        dataoutputstream.writeShort(this.c.length);
        dataoutputstream.write(this.c);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 4 + this.c.length;
    }
}
