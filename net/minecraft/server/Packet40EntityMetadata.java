package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.List;

public class Packet40EntityMetadata extends Packet {

    public int a;
    private List b;

    public Packet40EntityMetadata() {}

    public Packet40EntityMetadata(int i, DataWatcher datawatcher, boolean flag) {
        this.a = i;
        if (flag) {
            this.b = datawatcher.c();
        } else {
            this.b = datawatcher.b();
        }
    }

    public void a(DataInput datainput) {
        this.a = datainput.readInt();
        this.b = DataWatcher.a(datainput);
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.a);
        DataWatcher.a(this.b, dataoutput);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 5;
    }
}
