package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet7UseEntity extends Packet {

    public int a;
    public int target;
    public int action;

    public Packet7UseEntity() {}

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.target = datainput.readInt();
        this.action = datainput.readByte();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        dataoutput.writeInt(this.target);
        dataoutput.writeByte(this.action);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 9;
    }
}
