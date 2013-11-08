package net.minecraft.server;

public class MonsterEggInfo {

    public final int a;
    public final int b;
    public final int c;
    public final Statistic d;
    public final Statistic e;

    public MonsterEggInfo(int i, int j, int k) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = StatisticList.a(this);
        this.e = StatisticList.b(this);
    }
}
