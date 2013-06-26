package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet130UpdateSign extends Packet {

    public int x;
    public int y;
    public int z;
    public String[] lines;

    public Packet130UpdateSign() {
        this.lowPriority = true;
    }

    public Packet130UpdateSign(int i, int j, int k, String[] astring) {
        this.lowPriority = true;
        this.x = i;
        this.y = j;
        this.z = k;
        this.lines = new String[] { astring[0], astring[1], astring[2], astring[3]};
    }

    public void a(DataInput datainput) {
        this.x = datainput.readInt();
        this.y = datainput.readShort();
        this.z = datainput.readInt();
        this.lines = new String[4];

        for (int i = 0; i < 4; ++i) {
            this.lines[i] = a(datainput, 15);
        }
    }

    public void a(DataOutput dataoutput) {
        dataoutput.writeInt(this.x);
        dataoutput.writeShort(this.y);
        dataoutput.writeInt(this.z);

        for (int i = 0; i < 4; ++i) {
            a(this.lines[i], dataoutput);
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        int i = 0;

        for (int j = 0; j < 4; ++j) {
            i += this.lines[j].length();
        }

        return i;
    }
}
