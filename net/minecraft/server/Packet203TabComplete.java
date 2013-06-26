package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

import org.apache.commons.lang3.StringUtils;

public class Packet203TabComplete extends Packet {

    private String a;

    public Packet203TabComplete() {}

    public Packet203TabComplete(String s) {
        this.a = s;
    }

    public void a(DataInput datainput) {
        this.a = a(datainput, 32767);
    }

    public void a(DataOutput dataoutput) {
        a(StringUtils.substring(this.a, 0, 32767), dataoutput);
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
