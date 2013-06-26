package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet6SpawnPosition extends Packet {

    public int x;
    public int y;
    public int z;

    public Packet6SpawnPosition() {}

    public Packet6SpawnPosition(int i, int j, int k) {
        this.x = i;
        this.y = j;
        this.z = k;
    }

    public void a(DataInput datainput) {
        this.x = datainput.readInt();
        this.y = datainput.readInt();
        this.z = datainput.readInt();
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.x);
        dataoutput.writeInt(this.y);
        dataoutput.writeInt(this.z);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 12;
    }

    public boolean e() {
        return true;
    }

    public boolean a(Packet packet) {
        return true;
    }

    public boolean a_() {
        return false;
    }
}
