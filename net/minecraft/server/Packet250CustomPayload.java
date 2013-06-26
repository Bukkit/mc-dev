package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

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

    public void a(DataInput datainput) {
        this.tag = a(datainput, 20);
        this.length = datainput.readShort();
        if (this.length > 0 && this.length < 32767) {
            this.data = new byte[this.length];
            datainput.readFully(this.data);
        }
    }

    public void a(DataOutput dataoutput) {
        a(this.tag, dataoutput);
        dataoutput.writeShort((short) this.length);
        if (this.data != null) {
            dataoutput.write(this.data);
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2 + this.tag.length() * 2 + 2 + this.length;
    }
}
