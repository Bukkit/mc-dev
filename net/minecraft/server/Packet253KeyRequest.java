package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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

    public void a(DataInputStream datainputstream) {
        this.a = a(datainputstream, 20);
        this.b = MinecraftEncryption.a(b(datainputstream));
        this.c = b(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.a, dataoutputstream);
        a(dataoutputstream, this.b.getEncoded());
        a(dataoutputstream, this.c);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2 + this.a.length() * 2 + 2 + this.b.getEncoded().length + 2 + this.c.length;
    }
}
