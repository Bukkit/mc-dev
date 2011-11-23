package net.minecraft.server;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class RemoteStatusReply {

    private ByteArrayOutputStream buffer;
    private DataOutputStream stream;

    public RemoteStatusReply(int i) {
        this.buffer = new ByteArrayOutputStream(i);
        this.stream = new DataOutputStream(this.buffer);
    }

    public void write(byte[] abyte) {
        this.stream.write(abyte, 0, abyte.length);
    }

    public void write(String s) {
        this.stream.writeBytes(s);
        this.stream.write(0);
    }

    public void write(int i) {
        this.stream.write(i);
    }

    public void write(short short1) {
        this.stream.writeShort(Short.reverseBytes(short1));
    }

    public byte[] getBytes() {
        return this.buffer.toByteArray();
    }

    public void reset() {
        this.buffer.reset();
    }
}
