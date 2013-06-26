package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet18ArmAnimation extends Packet {

    public int a;
    public int b;

    public Packet18ArmAnimation() {}

    public Packet18ArmAnimation(Entity entity, int i) {
        this.a = entity.id;
        this.b = i;
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
