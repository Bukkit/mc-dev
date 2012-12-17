package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet205ClientCommand extends Packet {

    public int a;

    public Packet205ClientCommand() {}

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a & 255);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 1;
    }
}
