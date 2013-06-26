package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class Packet207SetScoreboardScore extends Packet {

    public String a = "";
    public String b = "";
    public int c;
    public int d;

    public Packet207SetScoreboardScore() {}

    public Packet207SetScoreboardScore(ScoreboardScore scoreboardscore, int i) {
        this.a = scoreboardscore.getPlayerName();
        this.b = scoreboardscore.getObjective().getName();
        this.c = scoreboardscore.getScore();
        this.d = i;
    }

    public Packet207SetScoreboardScore(String s) {
        this.a = s;
        this.b = "";
        this.c = 0;
        this.d = 1;
    }

    public void a(DataInput datainput) {
        this.a = a(datainput, 16);
        this.d = datainput.readByte();
        if (this.d != 1) {
            this.b = a(datainput, 16);
            this.c = datainput.readInt();
        }
    }

    public void a(DataOutput dataoutput) {
        a(this.a, dataoutput);
        dataoutput.writeByte(this.d);
        if (this.d != 1) {
            a(this.b, dataoutput);
            dataoutput.writeInt(this.c);
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2 + (this.a == null ? 0 : this.a.length()) + 2 + (this.b == null ? 0 : this.b.length()) + 4 + 1;
    }
}
