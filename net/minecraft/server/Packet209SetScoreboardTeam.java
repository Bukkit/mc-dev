package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Packet209SetScoreboardTeam extends Packet {

    public String a = "";
    public String b = "";
    public String c = "";
    public String d = "";
    public Collection e = new ArrayList();
    public int f = 0;
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

    public void a(DataInputStream datainputstream) {
        this.a = a(datainputstream, 16);
        this.f = datainputstream.readByte();
        if (this.f == 0 || this.f == 2) {
            this.b = a(datainputstream, 32);
            this.c = a(datainputstream, 16);
            this.d = a(datainputstream, 16);
            this.g = datainputstream.readByte();
        }

        if (this.f == 0 || this.f == 3 || this.f == 4) {
            short short1 = datainputstream.readShort();

            for (int i = 0; i < short1; ++i) {
                this.e.add(a(datainputstream, 16));
            }
        }
    }

    public void a(DataOutputStream dataoutputstream) {
        a(this.a, dataoutputstream);
        dataoutputstream.writeByte(this.f);
        if (this.f == 0 || this.f == 2) {
            a(this.b, dataoutputstream);
            a(this.c, dataoutputstream);
            a(this.d, dataoutputstream);
            dataoutputstream.writeByte(this.g);
        }

        if (this.f == 0 || this.f == 3 || this.f == 4) {
            dataoutputstream.writeShort(this.e.size());
            Iterator iterator = this.e.iterator();

            while (iterator.hasNext()) {
                String s = (String) iterator.next();

                a(s, dataoutputstream);
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
