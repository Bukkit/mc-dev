package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet250CustomPayload extends Packet {

    public String tag;
    public int length;
    public byte[] data;

    public Packet250CustomPayload() {}

    public Packet250CustomPayload(String s, byte[] abyte) {
        this.tag = s;
        this.data = abyte;
        if (abyte != null) {
            this.length = abyte.length;
            if (this.length > 32767) {
                throw new IllegalArgumentException("Payload may not be larger than 32k");
            }
        }
    }

    public void a(DataInputStream datainputstream) {
        this.tag = a(datainputstream, 20);
        this.length = datainputstream.readShort();
        if (this.length > 0 && this.length < 32767) {
            this.data = new byte[this.length];
            datainputstream.readFully(this.data);
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.tag, dataoutputstream);
        dataoutputstream.writeShort((short) this.length);
        if (this.data != null) {
            dataoutputstream.write(this.data);
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2 + this.tag.length() * 2 + 2 + this.length;
    }
}
