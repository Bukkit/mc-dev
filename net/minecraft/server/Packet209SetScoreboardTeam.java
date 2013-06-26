package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Packet209SetScoreboardTeam extends Packet {

    public String a = "";
    public String b = "";
    public String c = "";
    public String d = "";
    public Collection e = new ArrayList();
    public int f;
    public int g;

    public Packet209SetScoreboardTeam() {}

    public Packet209SetScoreboardTeam(ScoreboardTeam scoreboardteam, int i) {
        this.a = scoreboardteam.getName();
        this.f = i;
        if (i == 0 || i == 2) {
            this.b = scoreboardteam.getDisplayName();
            this.c = scoreboardteam.getPrefix();
            this.d = scoreboardteam.getSuffix();
            this.g = scoreboardteam.packOptionData();
        }

        if (i == 0) {
            this.e.addAll(scoreboardteam.getPlayerNameSet());
        }
    }

    public Packet209SetScoreboardTeam(ScoreboardTeam scoreboardteam, Collection collection, int i) {
        if (i != 3 && i != 4) {
            throw new IllegalArgumentException("Method must be join or leave for player constructor");
        } else if (collection != null && !collection.isEmpty()) {
            this.f = i;
            this.a = scoreboardteam.getName();
            this.e.addAll(collection);
        } else {
            throw new IllegalArgumentException("Players cannot be null/empty");
        }
    }

    public void a(DataInput datainput) {
        this.a = a(datainput, 16);
        this.f = datainput.readByte();
        if (this.f == 0 || this.f == 2) {
            this.b = a(datainput, 32);
            this.c = a(datainput, 16);
            this.d = a(datainput, 16);
            this.g = datainput.readByte();
        }

        if (this.f == 0 || this.f == 3 || this.f == 4) {
            short short1 = datainput.readShort();

            for (int i = 0; i < short1; ++i) {
                this.e.add(a(datainput, 16));
            }
        }
    }

    public void a(DataOutput dataoutput) {
        a(this.a, dataoutput);
        dataoutput.writeByte(this.f);
        if (this.f == 0 || this.f == 2) {
            a(this.b, dataoutput);
            a(this.c, dataoutput);
            a(this.d, dataoutput);
            dataoutput.writeByte(this.g);
        }

        if (this.f == 0 || this.f == 3 || this.f == 4) {
            dataoutput.writeShort(this.e.size());
            Iterator iterator = this.e.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                a(s, dataoutput);
            }
        }
    }

    public void handle(Connection connection) {
        connection.a(this);
    }

    public int a() {
        return 3 + this.a.length();
    }
}
