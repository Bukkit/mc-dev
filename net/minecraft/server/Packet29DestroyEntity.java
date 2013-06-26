package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet29DestroyEntity extends Packet {

    public int[] a;

    public Packet29DestroyEntity() {}

    public Packet29DestroyEntity(int... aint) {
        this.a = aint;
    }

    public void a(DataInput datainput) {
        this.a = new int[datainput.readByte()];

        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = datainput.readInt();
        }
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeByte(this.a.length);

        for (int i = 0; i < this.a.length; ++i) {
            dataoutput.writeInt(this.a[i]);
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 1 + this.a.length * 4;
    }
}
