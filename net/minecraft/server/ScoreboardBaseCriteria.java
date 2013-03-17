package net.minecraft.server;

import java.util.List;

public class ScoreboardBaseCriteria implements IScoreboardCriteria {

    private final String g;

    public ScoreboardBaseCriteria(String s) {
        this.g = s;
        IScoreboardCriteria.a.put(s, this);
    }

    public String getName() {
        return this.g;
    }

    public int getScoreModifier(List list) {
        return 0;
    }

    public boolean isReadOnly() {
        return false;
    }
}
