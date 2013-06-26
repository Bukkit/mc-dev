package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet42RemoveMobEffect extends Packet {

    public int a;
    public byte b;

    public Packet42RemoveMobEffect() {}

    public Packet42RemoveMobEffect(int i, MobEffect mobeffect) {
        this.a = i;
        this.b = (byte) (mobeffect.getEffectId() & 255);
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
