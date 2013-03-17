package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet207SetScoreboardScore extends Packet {

    public String a = "";
    public String b = "";
    public int c = 0;
    public int d = 0;

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

    public void a(DataInputStream datainputstream) {
        this.a = a(datainputstream, 16);
        this.d = datainputstream.readByte();
        if (this.d != 1) {
            this.b = a(datainputstream, 16);
            this.c = datainputstream.readInt();
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.a, dataoutputstream);
        dataoutputstream.writeByte(this.d);
        if (this.d != 1) {
            a(this.b, dataoutputstream);
            dataoutputstream.writeInt(this.c);
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2 + this.a.length() + 2 + this.b.length() + 4 + 1;
    }
}
