package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet3Chat extends Packet {

    public String message;

    public Packet3Chat() {}

    public Packet3Chat(String s) {
        if (s.length() > 119) {
            s = s.substring(0, 119);
        }

        this.message = s;
    }

    public void a(DataInputStream datainputstream) {
        this.message = a(datainputstream, 119);
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.message, dataoutputstream);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 2 + this.message.length() * 2;
    }
}
