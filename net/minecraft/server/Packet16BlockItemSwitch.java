package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet16BlockItemSwitch extends Packet {

    public int itemInHandIndex;

    public Packet16BlockItemSwitch() {}

    public void a(DataInputStream datainputstream) {
        this.itemInHandIndex = datainputstream.readShort();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeShort(this.itemInHandIndex);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 2;
    }
}
