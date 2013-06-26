package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet206SetScoreboardObjective extends Packet {

    public String a;
    public String b;
    public int c;

    public Packet206SetScoreboardObjective() {}

    public Packet206SetScoreboardObjective(ScoreboardObjective scoreboardobjective, int i) {
        this.a = scoreboardobjective.getName();
        this.b = scoreboardobjective.getDisplayName();
        this.c = i;
    }

    public void a(DataInput datainput) {
        this.a = a(datainput, 16);
        this.b = a(datainput, 32);
        this.c = datainput.readByte();
    }

    public void a(DataOutput dataoutput) {
        a(this.a, dataoutput);
        a(this.b, dataoutput);
        dataoutput.writeByte(this.c);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2 + this.a.length() + 2 + this.b.length() + 1;
    }
}
