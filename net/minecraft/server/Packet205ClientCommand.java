package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet205ClientCommand extends Packet {

    public int a;

    public Packet205ClientCommand() {}

    public void a(DataInput datainput) {
        this.a = datainput.readByte();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a & 255);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 1;
    }
}
