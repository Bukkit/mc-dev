package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

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

    public void a(DataInputStream datainputstream) {
        this.x = datainputstream.readInt();
        this.y = datainputstream.readInt();
        this.z = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeInt(this.x);
        dataoutputstream.writeInt(this.y);
        dataoutputstream.writeInt(this.z);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 12;
    }
}
