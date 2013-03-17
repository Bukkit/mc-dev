package net.minecraft.server;

public class ScoreboardObjective {

    private final Scoreboard a;
    private final String b;
    private final IScoreboardCriteria c;
    private String d;

    public ScoreboardObjective(Scoreboard scoreboard, String s, IScoreboardCriteria iscoreboardcriteria) {
        this.a = scoreboard;
        this.b = s;
        this.c = iscoreboardcriteria;
        this.d = s;
    }

    public String getName() {
        return this.b;
    }

    public IScoreboardCriteria getCriteria() {
        return this.c;
    }

    public String getDisplayName() {
        return this.d;
    }

    public void setDisplayName(String s) {
        this.d = s;
        this.a.handleObjectiveChanged(this);
    }
}
