package net.minecraft.server;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Statistic {

    public final int e;
    private final String a;
    public boolean f;
    public String g;
    private final Counter b;
    private static NumberFormat c = NumberFormat.getIntegerInstance(Locale.US);
    public static Counter h = new UnknownCounter();
    private static DecimalFormat d = new DecimalFormat("########0.00");
    public static Counter i = new TimeCounter();
    public static Counter j = new DistancesCounter();

    public Statistic(int i, String s, Counter counter) {
        this.f = false;
        this.e = i;
        this.a = s;
        this.b = counter;
    }

    public Statistic(int i, String s) {
        this(i, s, h);
    }

    public Statistic h() {
        this.f = true;
        return this;
    }

    public Statistic g() {
        if (StatisticList.a.containsKey(Integer.valueOf(this.e))) {
            throw new RuntimeException("Duplicate stat id: \"" + ((Statistic) StatisticList.a.get(Integer.valueOf(this.e))).a + "\" and \"" + this.a + "\" at id " + this.e);
        } else {
            StatisticList.b.add(this);
            StatisticList.a.put(Integer.valueOf(this.e), this);
            this.g = AchievementMap.a(this.e);
            return this;
        }
    }

    public String toString() {
        return LocaleI18n.get(this.a);
    }
}
