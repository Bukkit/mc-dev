package net.minecraft.server;

public class ScoreboardStatisticCriteria extends ScoreboardBaseCriteria {

    private final Statistic g;

    public ScoreboardStatisticCriteria(Statistic statistic) {
        super(statistic.e);
        this.g = statistic;
    }
}
