package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet254GetInfo extends Packet {

    private static final int d = (new Packet250CustomPayload()).n();
    public int a;
    public String b;
    public int c;

    public Packet254GetInfo() {}

    public void a(DataInput datainput) {
        try {
            datainput.readByte();
            datainput.readByte();
            a(datainput, 255);
            datainput.readShort();
            this.a = datainput.readByte();
            if (this.a >= 73) {
                this.b = a(datainput, 255);
                this.c = datainput.readInt();
            }
        } catch (Throwable throwable) {
            this.a = 0;
            this.b = "";
        }
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(1);
        dataoutput.writeByte(d);
        Packet.a("MC|PingHost", dataoutput);
        dataoutput.writeShort(3 + 2 * this.b.length() + 4);
        dataoutput.writeByte(this.a);
        Packet.a(this.b, dataoutput);
        dataoutput.writeInt(this.c);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 3 + this.b.length() * 2 + 4;
    }

    public boolean d() {
        return this.a == 0;
    }
}
