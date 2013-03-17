package net.minecraft.server;

import java.util.Comparator;
import java.util.List;

public class ScoreboardScore {

    public static final Comparator a = new ScoreboardComparator();
    private final Scoreboard b;
    private final ScoreboardObjective c;
    private final String playerName;
    private int score;

    public ScoreboardScore(Scoreboard scoreboard, ScoreboardObjective scoreboardobjective, String s) {
        this.b = scoreboard;
        this.c = scoreboardobjective;
        this.playerName = s;
    }

    public void addScore(int i) {
        if (this.c.getCriteria().isReadOnly()) {
            throw new IllegalStateException("Cannot modify read-only score");
        } else {
            this.setScore(this.getScore() + i);
        }
    }

    public void removeScore(int i) {
        if (this.c.getCriteria().isReadOnly()) {
            throw new IllegalStateException("Cannot modify read-only score");
        } else {
            this.setScore(this.getScore() - i);
        }
    }

    public void incrementScore() {
        if (this.c.getCriteria().isReadOnly()) {
            throw new IllegalStateException("Cannot modify read-only score");
        } else {
            this.addScore(1);
        }
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        int j = this.score;

        this.score = i;
        if (j != i) {
            this.f().handleScoreChanged(this);
        }
    }

    public ScoreboardObjective getObjective() {
        return this.c;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public Scoreboard f() {
        return this.b;
    }

    public void updateForList(List list) {
        this.setScore(this.c.getCriteria().getScoreModifier(list));
    }
}
