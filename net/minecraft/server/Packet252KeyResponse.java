package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.security.PrivateKey;
import javax.crypto.SecretKey;

public class Packet252KeyResponse extends Packet {

    private byte[] a = new byte[0];
    private byte[] b = new byte[0];
    private SecretKey c;

    public Packet252KeyResponse() {}

    public void a(DataInputStream datainputstream) {
        this.a = b(datainputstream);
        this.b = b(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) {
        a(dataoutputstream, this.a);
        a(dataoutputstream, this.b);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2 + this.a.length + 2 + this.b.length;
    }

    public SecretKey a(PrivateKey privatekey) {
        return privatekey == null ? this.c : (this.c = MinecraftEncryption.a(privatekey, this.a));
    }

    public SecretKey d() {
        return this.a((PrivateKey) null);
    }

    public byte[] b(PrivateKey privatekey) {
        return privatekey == null ? this.b : MinecraftEncryption.b(privatekey, this.b);
    }
}
