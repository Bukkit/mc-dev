package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StatisticList {

    private static Map C = new HashMap();
    public static List a = new ArrayList();
    public static List b = new ArrayList();
    public static List c = new ArrayList();
    public static List d = new ArrayList();
    public static Statistic e = (new Statistic(1000, StatisticCollector.a("stat.startGame"))).c();
    public static Statistic f = (new Statistic(1001, StatisticCollector.a("stat.createWorld"))).c();
    public static Statistic g = (new Statistic(1002, StatisticCollector.a("stat.loadWorld"))).c();
    public static Statistic h = (new Statistic(1003, StatisticCollector.a("stat.joinMultiplayer"))).c();
    public static Statistic i = (new Statistic(1004, StatisticCollector.a("stat.leaveGame"))).c();
    public static Statistic j = (new TimeStatistic(1100, StatisticCollector.a("stat.playOneMinute"))).c();
    public static Statistic k = (new DistanceStatistic(2000, StatisticCollector.a("stat.walkOneCm"))).c();
    public static Statistic l = (new DistanceStatistic(2001, StatisticCollector.a("stat.swimOneCm"))).c();
    public static Statistic m = (new DistanceStatistic(2002, StatisticCollector.a("stat.fallOneCm"))).c();
    public static Statistic n = (new DistanceStatistic(2003, StatisticCollector.a("stat.climbOneCm"))).c();
    public static Statistic o = (new DistanceStatistic(2004, StatisticCollector.a("stat.flyOneCm"))).c();
    public static Statistic p = (new DistanceStatistic(2005, StatisticCollector.a("stat.diveOneCm"))).c();
    public static Statistic q = (new Statistic(2010, StatisticCollector.a("stat.jump"))).c();
    public static Statistic r = (new Statistic(2011, StatisticCollector.a("stat.drop"))).c();
    public static Statistic s = (new Statistic(2020, StatisticCollector.a("stat.damageDealt"))).c();
    public static Statistic t = (new Statistic(2021, StatisticCollector.a("stat.damageTaken"))).c();
    public static Statistic u = (new Statistic(2022, StatisticCollector.a("stat.deaths"))).c();
    public static Statistic v = (new Statistic(2023, StatisticCollector.a("stat.mobKills"))).c();
    public static Statistic w = (new Statistic(2024, StatisticCollector.a("stat.playerKills"))).c();
    public static Statistic x = (new Statistic(2025, StatisticCollector.a("stat.fishCaught"))).c();
    public static Statistic[] y = a("stat.mineBlock", 16777216);
    public static Statistic[] z = null;
    public static Statistic[] A = null;
    public static Statistic[] B = null;
    private static boolean D = false;
    private static boolean E = false;

    public StatisticList() {
        System.out.println("Stats: " + a.size());
    }

    public static void a(Statistic statistic) {
        if (C.containsKey(Integer.valueOf(statistic.d))) {
            throw new RuntimeException("Duplicate stat id: " + ((Statistic) C.get(Integer.valueOf(statistic.d))).e + " and " + statistic.e + " at id " + statistic.d);
        } else {
            statistic.f = AchievementMap.a(statistic.d);
            a.add(statistic);
            if (statistic.a()) {
                AchievementList.a.add((Achievement) statistic);
            } else if (statistic instanceof CraftingStatistic) {
                CraftingStatistic craftingstatistic = (CraftingStatistic) statistic;

                if (craftingstatistic.b() < Block.byId.length) {
                    c.add(craftingstatistic);
                } else {
                    d.add(craftingstatistic);
                }
            } else {
                b.add(statistic);
            }
        }
    }

    public static void a() {
        A = a(A, "stat.useItem", 16908288, 0, Block.byId.length);
        B = b(B, "stat.breakItem", 16973824, 0, Block.byId.length);
        D = true;
        c();
    }

    public static void b() {
        A = a(A, "stat.useItem", 16908288, Block.byId.length, 32000);
        B = b(B, "stat.breakItem", 16973824, Block.byId.length, 32000);
        E = true;
        c();
    }

    public static void c() {
        if (D && E) {
            HashSet hashset = new HashSet();
            Iterator iterator = CraftingManager.a().b().iterator();

            while (iterator.hasNext()) {
                CraftingRecipe craftingrecipe = (CraftingRecipe) iterator.next();

                hashset.add(Integer.valueOf(craftingrecipe.b().id));
            }

            iterator = FurnaceRecipes.a().b().values().iterator();

            while (iterator.hasNext()) {
                ItemStack itemstack = (ItemStack) iterator.next();

                hashset.add(Integer.valueOf(itemstack.id));
            }

            z = new Statistic[32000];
            iterator = hashset.iterator();

            while (iterator.hasNext()) {
                Integer integer = (Integer) iterator.next();

                if (Item.byId[integer.intValue()] != null) {
                    String s = StatisticCollector.a("stat.craftItem", new Object[] { Item.byId[integer.intValue()].i()});

                    z[integer.intValue()] = (new CraftingStatistic(16842752 + integer.intValue(), s, integer.intValue())).c();
                }
            }

            a(z);
        }
    }

    private static Statistic[] a(String s, int i) {
        Statistic[] astatistic = new Statistic[256];

        for (int j = 0; j < 256; ++j) {
            if (Block.byId[j] != null) {
                String s1 = StatisticCollector.a(s, new Object[] { Block.byId[j].e()});

                astatistic[j] = (new CraftingStatistic(i + j, s1, j)).c();
            }
        }

        a(astatistic);
        return astatistic;
    }

    private static Statistic[] a(Statistic[] astatistic, String s, int i, int j, int k) {
        if (astatistic == null) {
            astatistic = new Statistic[32000];
        }

        for (int l = j; l < k; ++l) {
            if (Item.byId[l] != null) {
                String s1 = StatisticCollector.a(s, new Object[] { Item.byId[l].i()});

                astatistic[l] = (new CraftingStatistic(i + l, s1, l)).c();
            }
        }

        a(astatistic);
        return astatistic;
    }

    private static Statistic[] b(Statistic[] astatistic, String s, int i, int j, int k) {
        if (astatistic == null) {
            astatistic = new Statistic[32000];
        }

        for (int l = j; l < k; ++l) {
            if (Item.byId[l] != null && Item.byId[l].e()) {
                String s1 = StatisticCollector.a(s, new Object[] { Item.byId[l].i()});

                astatistic[l] = (new CraftingStatistic(i + l, s1, l)).c();
            }
        }

        a(astatistic);
        return astatistic;
    }

    private static void a(Statistic[] astatistic) {
        a(astatistic, Block.STATIONARY_WATER.id, Block.WATER.id);
        a(astatistic, Block.STATIONARY_LAVA.id, Block.STATIONARY_LAVA.id);
        a(astatistic, Block.JACK_O_LANTERN.id, Block.PUMPKIN.id);
        a(astatistic, Block.BURNING_FURNACE.id, Block.FURNACE.id);
        a(astatistic, Block.GLOWING_REDSTONE_ORE.id, Block.REDSTONE_ORE.id);
        a(astatistic, Block.DIODE_ON.id, Block.DIODE_OFF.id);
        a(astatistic, Block.REDSTONE_TORCH_ON.id, Block.REDSTONE_TORCH_OFF.id);
        a(astatistic, Block.RED_MUSHROOM.id, Block.BROWN_MUSHROOM.id);
        a(astatistic, Block.STEP.id, Block.DOUBLE_STEP.id);
    }

    private static void a(Statistic[] astatistic, int i, int j) {
        astatistic[i] = astatistic[j];
        a.remove(astatistic[i]);
        b.remove(astatistic[i]);
    }
}
