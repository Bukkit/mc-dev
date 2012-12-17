package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet16BlockItemSwitch extends Packet {

    public int itemInHandIndex;

    public Packet16BlockItemSwitch() {}

    public Packet16BlockItemSwitch(int i) {
        this.itemInHandIndex = i;
    }

    public void a(DataInputStream datainputstream) {
        this.itemInHandIndex = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeShort(this.itemInHandIndex);
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
