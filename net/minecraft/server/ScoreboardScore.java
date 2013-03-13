package net.minecraft.server;

import java.util.Comparator;
import java.util.List;

public class ScoreboardScore {

    public static final Comparator a = new ScoreboardComparator();
    private final Scoreboard b;
    private final ScoreboardObjective c;
    private final String d;
    private int e;

    public ScoreboardScore(Scoreboard scoreboard, ScoreboardObjective scoreboardobjective, String s) {
        this.b = scoreboard;
        this.c = scoreboardobjective;
        this.d = s;
    }

    public void a(int i) {
        if (this.c.c().b()) {
            throw new IllegalStateException("Cannot modify read-only score");
        } else {
            this.c(this.c() + i);
        }
    }

    public void b(int i) {
        if (this.c.c().b()) {
            throw new IllegalStateException("Cannot modify read-only score");
        } else {
            this.c(this.c() - i);
        }
    }

    public void a() {
        if (this.c.c().b()) {
            throw new IllegalStateException("Cannot modify read-only score");
        } else {
            this.a(1);
        }
    }

    public int c() {
        return this.e;
    }

    public void c(int i) {
        int j = this.e;

        this.e = i;
        if (j != i) {
            this.f().a(this);
        }
    }

    public ScoreboardObjective d() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public Scoreboard f() {
        return this.b;
    }

    public void a(List list) {
        this.c(this.c.c().a(list));
    }
}
