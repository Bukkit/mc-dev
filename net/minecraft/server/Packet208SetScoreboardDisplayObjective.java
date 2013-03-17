package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet208SetScoreboardDisplayObjective extends Packet {

    public int a;
    public String b;

    public Packet208SetScoreboardDisplayObjective() {}

    public Packet208SetScoreboardDisplayObjective(int i, ScoreboardObjective scoreboardobjective) {
        this.a = i;
        if (scoreboardobjective == null) {
            this.b = "";
        } else {
            this.b = scoreboardobjective.getName();
        }
    }

    public void a(DataInputStream datainputstream) {
        this.a = datainputstream.readByte();
        this.b = a(datainputstream, 16);
    }

    public void a(DataOutputStream dataoutputstream) {
        dataoutputstream.writeByte(this.a);
        a(this.b, dataoutputstream);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 3 + this.b.length();
    }
}
