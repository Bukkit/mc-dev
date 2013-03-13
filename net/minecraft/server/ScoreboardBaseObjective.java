package net.minecraft.server;

import java.util.List;

public class ScoreboardBaseObjective implements IObjective {

    private final String g;

    public ScoreboardBaseObjective(String s) {
        this.g = s;
        IObjective.a.put(s, this);
    }

    public String a() {
        return this.g;
    }

    public int a(List list) {
        return 0;
    }

    public boolean b() {
        return false;
    }
}
