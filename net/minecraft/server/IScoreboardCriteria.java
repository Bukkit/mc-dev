package net.minecraft.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IScoreboardCriteria {

    Map a = new HashMap();
    IScoreboardCriteria b = new ScoreboardBaseCriteria("dummy");
    IScoreboardCriteria c = new ScoreboardBaseCriteria("deathCount");
    IScoreboardCriteria d = new ScoreboardBaseCriteria("playerKillCount");
    IScoreboardCriteria e = new ScoreboardBaseCriteria("totalKillCount");
    IScoreboardCriteria f = new ScoreboardHealthCriteria("health");

    String getName();

    int getScoreModifier(List list);

    boolean isReadOnly();

}
