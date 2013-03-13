package net.minecraft.server;

public class ScoreboardObjective {

    private final Scoreboard a;
    private final String b;
    private final IObjective c;
    private String d;

    public ScoreboardObjective(Scoreboard scoreboard, String s, IObjective iobjective) {
        this.a = scoreboard;
        this.b = s;
        this.c = iobjective;
        this.d = s;
    }

    public String b() {
        return this.b;
    }

    public IObjective c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public void a(String s) {
        this.d = s;
        this.a.b(this);
    }
}
