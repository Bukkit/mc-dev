package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet4UpdateTime extends Packet {

    public long a;
    public long b;

    public Packet4UpdateTime() {}

    public Packet4UpdateTime(long i, long j, boolean flag) {
        this.a = i;
        this.b = j;
        if (!flag) {
            this.b = -this.b;
            if (this.b == 0L) {
                this.b = -1L;
            }
        }
    }

    public void a(DataInput datainput) {
        this.a = datainput.readLong();
        this.b = datainput.readLong();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeLong(this.a);
        dataoutput.writeLong(this.b);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 16;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        return true;
    }

    public boolean a_() {
        return true;
    }
}
