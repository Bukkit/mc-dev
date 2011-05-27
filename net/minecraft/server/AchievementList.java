package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class AchievementList {

    public static List a = new ArrayList();
    public static Achievement b = new Achievement(5242880, StatisticCollector.a("achievement.openInventory"), 0, 0, (Achievement) null);
    public static Achievement c = new Achievement(5242881, StatisticCollector.a("achievement.mineWood"), 4, 1, b);
    public static Achievement d = new Achievement(5242881, StatisticCollector.a("achievement.buildWorkBench"), 8, -1, c);

    public AchievementList() {}
}
