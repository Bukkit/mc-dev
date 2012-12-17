package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet35EntityHeadRotation extends Packet {

    public int a;
    public byte b;

    public Packet35EntityHeadRotation() {}

    public Packet35EntityHeadRotation(int i, byte b0) {
        this.a = i;
        this.b = b0;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readInt();
        this.b = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.a);
        dataoutputstream.writeByte(this.b);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 5;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        Packet35EntityHeadRotation packet35entityheadrotation = (Packet35EntityHeadRotation) packet;

        return packet35entityheadrotation.a == this.a;
    }

    public boolean a_() {
        return true;
    }
}
