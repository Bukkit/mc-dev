package net.minecraft.server;

public class CounterStatistic extends Statistic {

    public CounterStatistic(int i, String s, Counter counter) {
        super(i, s, counter);
    }

    public CounterStatistic(int i, String s) {
        super(i, s);
    }

    public Statistic d() {
        super.d();
        StatisticList.c.add(this);
        return this;
    }
}
