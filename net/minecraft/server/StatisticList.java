package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StatisticList {

    protected static Map a = new HashMap();
    public static List b = new ArrayList();
    public static List c = new ArrayList();
    public static List d = new ArrayList();
    public static List e = new ArrayList();
    public static Statistic f = (new CounterStatistic(1000, "stat.startGame")).e().d();
    public static Statistic g = (new CounterStatistic(1001, "stat.createWorld")).e().d();
    public static Statistic h = (new CounterStatistic(1002, "stat.loadWorld")).e().d();
    public static Statistic i = (new CounterStatistic(1003, "stat.joinMultiplayer")).e().d();
    public static Statistic j = (new CounterStatistic(1004, "stat.leaveGame")).e().d();
    public static Statistic k = (new CounterStatistic(1100, "stat.playOneMinute", Statistic.i)).e().d();
    public static Statistic l = (new CounterStatistic(2000, "stat.walkOneCm", Statistic.j)).e().d();
    public static Statistic m = (new CounterStatistic(2001, "stat.swimOneCm", Statistic.j)).e().d();
    public static Statistic n = (new CounterStatistic(2002, "stat.fallOneCm", Statistic.j)).e().d();
    public static Statistic o = (new CounterStatistic(2003, "stat.climbOneCm", Statistic.j)).e().d();
    public static Statistic p = (new CounterStatistic(2004, "stat.flyOneCm", Statistic.j)).e().d();
    public static Statistic q = (new CounterStatistic(2005, "stat.diveOneCm", Statistic.j)).e().d();
    public static Statistic r = (new CounterStatistic(2006, "stat.minecartOneCm", Statistic.j)).e().d();
    public static Statistic s = (new CounterStatistic(2007, "stat.boatOneCm", Statistic.j)).e().d();
    public static Statistic t = (new CounterStatistic(2008, "stat.pigOneCm", Statistic.j)).e().d();
    public static Statistic u = (new CounterStatistic(2010, "stat.jump")).e().d();
    public static Statistic v = (new CounterStatistic(2011, "stat.drop")).e().d();
    public static Statistic w = (new CounterStatistic(2020, "stat.damageDealt")).d();
    public static Statistic x = (new CounterStatistic(2021, "stat.damageTaken")).d();
    public static Statistic y = (new CounterStatistic(2022, "stat.deaths")).d();
    public static Statistic z = (new CounterStatistic(2023, "stat.mobKills")).d();
    public static Statistic A = (new CounterStatistic(2024, "stat.playerKills")).d();
    public static Statistic B = (new CounterStatistic(2025, "stat.fishCaught")).d();
    public static Statistic[] C = a("stat.mineBlock", 16777216);
    public static Statistic[] D;
    public static Statistic[] E;
    public static Statistic[] F;
    private static boolean G;
    private static boolean H;

    public StatisticList() {}

    public static void a() {}

    public static void b() {
        E = a(E, "stat.useItem", 16908288, 0, 256);
        F = b(F, "stat.breakItem", 16973824, 0, 256);
        G = true;
        d();
    }

    public static void c() {
        E = a(E, "stat.useItem", 16908288, 256, 32000);
        F = b(F, "stat.breakItem", 16973824, 256, 32000);
        H = true;
        d();
    }

    public static void d() {
        if (G && H) {
            HashSet hashset = new HashSet();
            Iterator iterator = CraftingManager.getInstance().getRecipies().iterator();

            while (iterator.hasNext()) {
                CraftingRecipe craftingrecipe = (CraftingRecipe) iterator.next();

                hashset.add(Integer.valueOf(craftingrecipe.b().id));
            }

            iterator = FurnaceRecipes.getInstance().getRecipies().values().iterator();

            while (iterator.hasNext()) {
                ItemStack itemstack = (ItemStack) iterator.next();

                hashset.add(Integer.valueOf(itemstack.id));
            }

            D = new Statistic[32000];
            iterator = hashset.iterator();

            while (iterator.hasNext()) {
                Integer integer = (Integer) iterator.next();

                if (Item.byId[integer.intValue()] != null) {
                    String s = LocaleI18n.get("stat.craftItem", new Object[] { Item.byId[integer.intValue()].l()});

                    D[integer.intValue()] = (new CraftingStatistic(16842752 + integer.intValue(), s, integer.intValue())).d();
                }
            }

            a(D);
        }
    }

    private static Statistic[] a(String s, int i) {
        Statistic[] astatistic = new Statistic[256];

        for (int j = 0; j < 256; ++j) {
            if (Block.byId[j] != null && Block.byId[j].r()) {
                String s1 = LocaleI18n.get(s, new Object[] { Block.byId[j].getName()});

                astatistic[j] = (new CraftingStatistic(i + j, s1, j)).d();
                e.add((CraftingStatistic) astatistic[j]);
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
                String s1 = LocaleI18n.get(s, new Object[] { Item.byId[l].l()});

                astatistic[l] = (new CraftingStatistic(i + l, s1, l)).d();
                if (l >= 256) {
                    d.add((CraftingStatistic) astatistic[l]);
                }
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
            if (Item.byId[l] != null && Item.byId[l].g()) {
                String s1 = LocaleI18n.get(s, new Object[] { Item.byId[l].l()});

                astatistic[l] = (new CraftingStatistic(i + l, s1, l)).d();
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
        a(astatistic, Block.DOUBLE_STEP.id, Block.STEP.id);
        a(astatistic, Block.GRASS.id, Block.DIRT.id);
        a(astatistic, Block.SOIL.id, Block.DIRT.id);
    }

    private static void a(Statistic[] astatistic, int i, int j) {
        if (astatistic[i] != null && astatistic[j] == null) {
            astatistic[j] = astatistic[i];
        } else {
            b.remove(astatistic[i]);
            e.remove(astatistic[i]);
            c.remove(astatistic[i]);
            astatistic[i] = astatistic[j];
        }
    }

    static {
        AchievementList.a();
        G = false;
        H = false;
    }
}
