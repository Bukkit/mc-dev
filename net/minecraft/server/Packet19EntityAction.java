package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet19EntityAction extends Packet {

    public int a;
    public int animation;
    public int c;

    public Packet19EntityAction() {}

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.animation = datainput.readByte();
        this.c = datainput.readInt();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeByte(this.animation);
        dataoutput.writeInt(this.c);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 9;
    }
}
