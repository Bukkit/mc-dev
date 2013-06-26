package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet16BlockItemSwitch extends Packet {

    public int itemInHandIndex;

    public Packet16BlockItemSwitch() {}

    public Packet16BlockItemSwitch(int i) {
        this.itemInHandIndex = i;
    }

    public void a(DataInput datainput) {
        this.itemInHandIndex = datainput.readShort();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeShort(this.itemInHandIndex);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        return true;
    }
}
