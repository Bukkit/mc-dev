package net.minecraft.server;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Statistic {

    public final int e;
    public final String f;
    public boolean g;
    public String h;
    private final Counter a;
    private static NumberFormat b = NumberFormat.getIntegerInstance(Locale.US);
    public static Counter i = new UnknownCounter();
    private static DecimalFormat c = new DecimalFormat("########0.00");
    public static Counter j = new TimeCounter();
    public static Counter k = new DistancesCounter();

    public Statistic(int i, String s, Counter counter) {
        this.g = false;
        this.e = i;
        this.f = s;
        this.a = counter;
    }

    public Statistic(int i, String s) {
        this(i, s, i);
    }

    public Statistic e() {
        this.g = true;
        return this;
    }

    public Statistic d() {
        if (StatisticList.a.containsKey(Integer.valueOf(this.e))) {
            throw new RuntimeException("Duplicate stat id: \"" + ((Statistic) StatisticList.a.get(Integer.valueOf(this.e))).f + "\" and \"" + this.f + "\" at id " + this.e);
        } else {
            StatisticList.b.add(this);
            StatisticList.a.put(Integer.valueOf(this.e), this);
            this.h = AchievementMap.a(this.e);
            return this;
        }
    }

    public String toString() {
        return this.f;
    }
}
