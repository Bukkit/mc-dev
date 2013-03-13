package net.minecraft.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ScoreboardTeam {

    private final Scoreboard a;
    private final String b;
    private final Set c = new HashSet();
    private String d;
    private String e = "";
    private String f = "";
    private boolean g = true;
    private boolean h = true;

    public ScoreboardTeam(Scoreboard scoreboard, String s) {
        this.a = scoreboard;
        this.b = s;
        this.d = s;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public void a(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Name cannot be null");
        } else {
            this.d = s;
            this.a.b(this);
        }
    }

    public Collection d() {
        return this.c;
    }

    public String e() {
        return this.e;
    }

    public void b(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Prefix cannot be null");
        } else {
            this.e = s;
            this.a.b(this);
        }
    }

    public String f() {
        return this.f;
    }

    public void c(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Suffix cannot be null");
        } else {
            this.f = s;
            this.a.b(this);
        }
    }

    public static String a(ScoreboardTeam scoreboardteam, String s) {
        return scoreboardteam == null ? s : scoreboardteam.e() + s + scoreboardteam.f();
    }

    public boolean g() {
        return this.g;
    }

    public void a(boolean flag) {
        this.g = flag;
        this.a.b(this);
    }

    public boolean h() {
        return this.h;
    }

    public void b(boolean flag) {
        this.h = flag;
        this.a.b(this);
    }

    public int i() {
        int i = 0;
        int j = 0;

        if (this.g()) {
            i |= 1 << j++;
        }

        if (this.h()) {
            i |= 1 << j++;
        }

        return i;
    }
}
