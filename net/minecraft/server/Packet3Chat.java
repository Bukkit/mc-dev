package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet3Chat extends Packet {

    public String a;

    public Packet3Chat() {}

    public Packet3Chat(String s) {
        this.a = s;
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readUTF();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeUTF(this.a);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return this.a.length();
    }
}
