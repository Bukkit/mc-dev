package net.minecraft.server;

public class StatisticCollector {

    private static StatisticStorage a = StatisticStorage.a();

    public StatisticCollector() {}

    public static String a(String s) {
        return a.a(s);
    }

    public static String a(String s, Object... aobject) {
        return a.a(s, aobject);
    }
}
