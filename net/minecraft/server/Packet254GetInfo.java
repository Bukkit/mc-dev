package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet254GetInfo extends Packet {

    public int a = 0;

    public Packet254GetInfo() {}

    public void a(DataInputStream datainputstream) {
        try {
            this.a = datainputstream.readByte();
        } catch (Throwable throwable) {
            this.a = 0;
        }
    }

    public void a(DataOutputStream dataoutputstream) {}

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 0;
    }
}
