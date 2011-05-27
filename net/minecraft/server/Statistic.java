package net.minecraft.server;

public class Statistic {

    public final int d;
    public final String e;
    public String f;

    public Statistic(int i, String s) {
        this.d = i;
        this.e = s;
    }

    public Statistic c() {
        StatisticList.a(this);
        return this;
    }

    public boolean a() {
        return false;
    }
}
