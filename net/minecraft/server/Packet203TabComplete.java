package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet203TabComplete extends Packet {

    private String a;

    public Packet203TabComplete() {}

    public Packet203TabComplete(String s) {
        this.a = s;
    }

    public void a(DataInputStream datainputstream) {
        this.a = a(datainputstream, Packet3Chat.a);
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.a, dataoutputstream);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2 + this.a.length() * 2;
    }

    public String d() {
        return this.a;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        return true;
    }
}
