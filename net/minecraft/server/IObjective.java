package net.minecraft.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IObjective {

    Map a = new HashMap();
    IObjective b = new ScoreboardBaseObjective("dummy");
    IObjective c = new ScoreboardBaseObjective("deathCount");
    IObjective d = new ScoreboardBaseObjective("playerKillCount");
    IObjective e = new ScoreboardBaseObjective("totalKillCount");
    IObjective f = new ScoreboardHealthObjective("health");

    String a();

    int a(List list);

    boolean b();

}
