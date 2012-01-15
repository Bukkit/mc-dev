package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet250CustomPayload extends Packet {

    public String tag;
    public int length;
    public byte[] data;

    public Packet250CustomPayload() {}

    public void a(DataInputStream datainputstream) {
        this.tag = a(datainputstream, 16);
        this.length = datainputstream.readShort();
        if (this.length > 0 && this.length < 32767) {
            this.data = new byte[this.length];
            datainputstream.read(this.data);
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.tag, dataoutputstream);
        dataoutputstream.writeShort((short) this.length);
        if (this.data != null) {
            dataoutputstream.write(this.data);
        }
    }

    public void handle(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 2 + this.tag.length() * 2 + 2 + this.length;
    }
}
