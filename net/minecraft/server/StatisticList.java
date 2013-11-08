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
    public static Statistic f = (new CounterStatistic("stat.leaveGame", new ChatMessage("stat.leaveGame", new Object[0]))).i().h();
    public static Statistic g = (new CounterStatistic("stat.playOneMinute", new ChatMessage("stat.playOneMinute", new Object[0]), Statistic.h)).i().h();
    public static Statistic h = (new CounterStatistic("stat.walkOneCm", new ChatMessage("stat.walkOneCm", new Object[0]), Statistic.i)).i().h();
    public static Statistic i = (new CounterStatistic("stat.swimOneCm", new ChatMessage("stat.swimOneCm", new Object[0]), Statistic.i)).i().h();
    public static Statistic j = (new CounterStatistic("stat.fallOneCm", new ChatMessage("stat.fallOneCm", new Object[0]), Statistic.i)).i().h();
    public static Statistic k = (new CounterStatistic("stat.climbOneCm", new ChatMessage("stat.climbOneCm", new Object[0]), Statistic.i)).i().h();
    public static Statistic l = (new CounterStatistic("stat.flyOneCm", new ChatMessage("stat.flyOneCm", new Object[0]), Statistic.i)).i().h();
    public static Statistic m = (new CounterStatistic("stat.diveOneCm", new ChatMessage("stat.diveOneCm", new Object[0]), Statistic.i)).i().h();
    public static Statistic n = (new CounterStatistic("stat.minecartOneCm", new ChatMessage("stat.minecartOneCm", new Object[0]), Statistic.i)).i().h();
    public static Statistic o = (new CounterStatistic("stat.boatOneCm", new ChatMessage("stat.boatOneCm", new Object[0]), Statistic.i)).i().h();
    public static Statistic p = (new CounterStatistic("stat.pigOneCm", new ChatMessage("stat.pigOneCm", new Object[0]), Statistic.i)).i().h();
    public static Statistic q = (new CounterStatistic("stat.horseOneCm", new ChatMessage("stat.horseOneCm", new Object[0]), Statistic.i)).i().h();
    public static Statistic r = (new CounterStatistic("stat.jump", new ChatMessage("stat.jump", new Object[0]))).i().h();
    public static Statistic s = (new CounterStatistic("stat.drop", new ChatMessage("stat.drop", new Object[0]))).i().h();
    public static Statistic t = (new CounterStatistic("stat.damageDealt", new ChatMessage("stat.damageDealt", new Object[0]), Statistic.j)).h();
    public static Statistic u = (new CounterStatistic("stat.damageTaken", new ChatMessage("stat.damageTaken", new Object[0]), Statistic.j)).h();
    public static Statistic v = (new CounterStatistic("stat.deaths", new ChatMessage("stat.deaths", new Object[0]))).h();
    public static Statistic w = (new CounterStatistic("stat.mobKills", new ChatMessage("stat.mobKills", new Object[0]))).h();
    public static Statistic x = (new CounterStatistic("stat.animalsBred", new ChatMessage("stat.animalsBred", new Object[0]))).h();
    public static Statistic y = (new CounterStatistic("stat.playerKills", new ChatMessage("stat.playerKills", new Object[0]))).h();
    public static Statistic z = (new CounterStatistic("stat.fishCaught", new ChatMessage("stat.fishCaught", new Object[0]))).h();
    public static Statistic A = (new CounterStatistic("stat.junkFished", new ChatMessage("stat.junkFished", new Object[0]))).h();
    public static Statistic B = (new CounterStatistic("stat.treasureFished", new ChatMessage("stat.treasureFished", new Object[0]))).h();
    public static final Statistic[] C = new Statistic[4096];
    public static final Statistic[] D = new Statistic[32000];
    public static final Statistic[] E = new Statistic[32000];
    public static final Statistic[] F = new Statistic[32000];

    public static void a() {
        c();
        d();
        e();
        b();
        AchievementList.a();
        EntityTypes.a();
    }

    private static void b() {
        HashSet hashset = new HashSet();
        Iterator iterator = CraftingManager.getInstance().getRecipes().iterator();

        while (iterator.hasNext()) {
            IRecipe irecipe = (IRecipe) iterator.next();

            if (irecipe.b() != null) {
                hashset.add(irecipe.b().getItem());
            }
        }

        iterator = RecipesFurnace.getInstance().getRecipes().values().iterator();

        while (iterator.hasNext()) {
            ItemStack itemstack = (ItemStack) iterator.next();

            hashset.add(itemstack.getItem());
        }

        iterator = hashset.iterator();

        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();

            if (item != null) {
                int i = Item.b(item);

                D[i] = (new CraftingStatistic("stat.craftItem." + i, new ChatMessage("stat.craftItem", new Object[] { (new ItemStack(item)).E()}), item)).h();
            }
        }

        a(D);
    }

    private static void c() {
        Iterator iterator = Block.REGISTRY.iterator();

        while (iterator.hasNext()) {
            Block block = (Block) iterator.next();

            if (Item.getItemOf(block) != null) {
                int i = Block.b(block);

                if (block.G()) {
                    C[i] = (new CraftingStatistic("stat.mineBlock." + i, new ChatMessage("stat.mineBlock", new Object[] { (new ItemStack(block)).E()}), Item.getItemOf(block))).h();
                    e.add((CraftingStatistic) C[i]);
                }
            }
        }

        a(C);
    }

    private static void d() {
        Iterator iterator = Item.REGISTRY.iterator();

        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();

            if (item != null) {
                int i = Item.b(item);

                E[i] = (new CraftingStatistic("stat.useItem." + i, new ChatMessage("stat.useItem", new Object[] { (new ItemStack(item)).E()}), item)).h();
                if (!(item instanceof ItemBlock)) {
                    d.add((CraftingStatistic) E[i]);
                }
            }
        }

        a(E);
    }

    private static void e() {
        Iterator iterator = Item.REGISTRY.iterator();

        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();

            if (item != null) {
                int i = Item.b(item);

                if (item.usesDurability()) {
                    F[i] = (new CraftingStatistic("stat.breakItem." + i, new ChatMessage("stat.breakItem", new Object[] { (new ItemStack(item)).E()}), item)).h();
                }
            }
        }

        a(F);
    }

    private static void a(Statistic[] astatistic) {
        a(astatistic, Blocks.STATIONARY_WATER, Blocks.WATER);
        a(astatistic, Blocks.STATIONARY_LAVA, Blocks.LAVA);
        a(astatistic, Blocks.JACK_O_LANTERN, Blocks.PUMPKIN);
        a(astatistic, Blocks.BURNING_FURNACE, Blocks.FURNACE);
        a(astatistic, Blocks.GLOWING_REDSTONE_ORE, Blocks.REDSTONE_ORE);
        a(astatistic, Blocks.DIODE_ON, Blocks.DIODE_OFF);
        a(astatistic, Blocks.REDSTONE_COMPARATOR_ON, Blocks.REDSTONE_COMPARATOR_OFF);
        a(astatistic, Blocks.REDSTONE_TORCH_ON, Blocks.REDSTONE_TORCH_OFF);
        a(astatistic, Blocks.REDSTONE_LAMP_ON, Blocks.REDSTONE_LAMP_OFF);
        a(astatistic, Blocks.RED_MUSHROOM, Blocks.BROWN_MUSHROOM);
        a(astatistic, Blocks.DOUBLE_STEP, Blocks.STEP);
        a(astatistic, Blocks.WOOD_DOUBLE_STEP, Blocks.WOOD_STEP);
        a(astatistic, Blocks.GRASS, Blocks.DIRT);
        a(astatistic, Blocks.SOIL, Blocks.DIRT);
    }

    private static void a(Statistic[] astatistic, Block block, Block block1) {
        int i = Block.b(block);
        int j = Block.b(block1);

        if (astatistic[i] != null && astatistic[j] == null) {
            astatistic[j] = astatistic[i];
        } else {
            b.remove(astatistic[i]);
            e.remove(astatistic[i]);
            c.remove(astatistic[i]);
            astatistic[i] = astatistic[j];
        }
    }

    public static Statistic a(MonsterEggInfo monsteregginfo) {
        String s = EntityTypes.b(monsteregginfo.a);

        return s == null ? null : (new Statistic("stat.killEntity." + s, new ChatMessage("stat.entityKill", new Object[] { new ChatMessage("entity." + s + ".name", new Object[0])}))).h();
    }

    public static Statistic b(MonsterEggInfo monsteregginfo) {
        String s = EntityTypes.b(monsteregginfo.a);

        return s == null ? null : (new Statistic("stat.entityKilledBy." + s, new ChatMessage("stat.entityKilledBy", new Object[] { new ChatMessage("entity." + s + ".name", new Object[0])}))).h();
    }

    public static Statistic a(String s) {
        return (Statistic) a.get(s);
    }
}
