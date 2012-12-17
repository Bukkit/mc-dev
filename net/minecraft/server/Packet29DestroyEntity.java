package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet29DestroyEntity extends Packet {

    public int[] a;

    public Packet29DestroyEntity() {}

    public Packet29DestroyEntity(int... aint) {
        this.a = aint;
    }

    public void a(DataInputStream datainputstream) {
        this.a = new int[datainputstream.readByte()];

        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = datainputstream.readInt();
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a.length);

        for (int i = 0; i < this.a.length; ++i) {
            dataoutputstream.writeInt(this.a[i]);
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 1 + this.a.length * 4;
    }
}
