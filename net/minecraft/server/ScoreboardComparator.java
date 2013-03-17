package net.minecraft.server;

import java.util.Comparator;

final class ScoreboardComparator implements Comparator {

    ScoreboardComparator() {}

    public int a(ScoreboardScore scoreboardscore, ScoreboardScore scoreboardscore1) {
        return scoreboardscore.getScore() > scoreboardscore1.getScore() ? 1 : (scoreboardscore.getScore() < scoreboardscore1.getScore() ? -1 : 0);
    }

    public int compare(Object object, Object object1) {
        return this.a((ScoreboardScore) object, (ScoreboardScore) object1);
    }
}
