package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet3Chat extends Packet {

    public static int b = 119;
    public String message;

    public Packet3Chat() {}

    public Packet3Chat(String s) {
        if (s.length() > b) {
            s = s.substring(0, b);
        }

        this.message = s;
    }

    public void a(DataInputStream datainputstream) {
        this.message = a(datainputstream, b);
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.message, dataoutputstream);
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 2 + this.message.length() * 2;
    }
}
