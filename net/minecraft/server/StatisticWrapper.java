package net.minecraft.server;

public class StatisticWrapper {

    private int a;
    private IJsonStatistic b;

    public StatisticWrapper() {}

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public IJsonStatistic b() {
        return this.b;
    }

    public void a(IJsonStatistic ijsonstatistic) {
        this.b = ijsonstatistic;
    }
}
