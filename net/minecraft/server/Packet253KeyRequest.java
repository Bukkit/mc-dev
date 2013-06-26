package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.security.PublicKey;

public class Packet253KeyRequest extends Packet {

    private String a;
    private PublicKey b;
    private byte[] c = new byte[0];

    public Packet253KeyRequest() {}

    public Packet253KeyRequest(String s, PublicKey publickey, byte[] abyte) {
        this.a = s;
        this.b = publickey;
        this.c = abyte;
    }

    public void a(DataInput datainput) {
        this.a = a(datainput, 20);
        this.b = MinecraftEncryption.a(b(datainput));
        this.c = b(datainput);
    }

    public void a(DataOutput dataoutput) {
        a(this.a, dataoutput);
        a(dataoutput, this.b.getEncoded());
        a(dataoutput, this.c);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2 + this.a.length() * 2 + 2 + this.b.getEncoded().length + 2 + this.c.length;
    }
}
