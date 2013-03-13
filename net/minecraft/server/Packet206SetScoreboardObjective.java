package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet206SetScoreboardObjective extends Packet {

    public String a;
    public String b;
    public int c;

    public Packet206SetScoreboardObjective() {}

    public Packet206SetScoreboardObjective(ScoreboardObjective scoreboardobjective, int i) {
        this.a = scoreboardobjective.b();
        this.b = scoreboardobjective.d();
        this.c = i;
    }

    public void a(DataInputStream datainputstream) {
        this.a = a(datainputstream, 16);
        this.b = a(datainputstream, 32);
        this.c = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.a, dataoutputstream);
        a(this.b, dataoutputstream);
        dataoutputstream.writeByte(this.c);
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 2 + this.a.length() + 2 + this.b.length() + 1;
    }
}
