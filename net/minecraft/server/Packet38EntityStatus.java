package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet38EntityStatus extends Packet {

    public int a;
    public byte b;

    public Packet38EntityStatus() {}

    public Packet38EntityStatus(int i, byte b0) {
        this.a = i;
        this.b = b0;
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = datainput.readByte();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeByte(this.b);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 5;
    }
}
