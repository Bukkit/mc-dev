package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class AchievementList {

    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static List e = new ArrayList();
    public static Achievement f = (new Achievement("achievement.openInventory", "openInventory", 0, 0, Items.BOOK, (Achievement) null)).a().c();
    public static Achievement g = (new Achievement("achievement.mineWood", "mineWood", 2, 1, Blocks.LOG, f)).c();
    public static Achievement h = (new Achievement("achievement.buildWorkBench", "buildWorkBench", 4, -1, Blocks.WORKBENCH, g)).c();
    public static Achievement i = (new Achievement("achievement.buildPickaxe", "buildPickaxe", 4, 2, Items.WOOD_PICKAXE, h)).c();
    public static Achievement j = (new Achievement("achievement.buildFurnace", "buildFurnace", 3, 4, Blocks.FURNACE, i)).c();
    public static Achievement k = (new Achievement("achievement.acquireIron", "acquireIron", 1, 4, Items.IRON_INGOT, j)).c();
    public static Achievement l = (new Achievement("achievement.buildHoe", "buildHoe", 2, -3, Items.WOOD_HOE, h)).c();
    public static Achievement m = (new Achievement("achievement.makeBread", "makeBread", -1, -3, Items.BREAD, l)).c();
    public static Achievement n = (new Achievement("achievement.bakeCake", "bakeCake", 0, -5, Items.CAKE, l)).c();
    public static Achievement o = (new Achievement("achievement.buildBetterPickaxe", "buildBetterPickaxe", 6, 2, Items.STONE_PICKAXE, i)).c();
    public static Achievement p = (new Achievement("achievement.cookFish", "cookFish", 2, 6, Items.COOKED_FISH, j)).c();
    public static Achievement q = (new Achievement("achievement.onARail", "onARail", 2, 3, Blocks.RAILS, k)).b().c();
    public static Achievement r = (new Achievement("achievement.buildSword", "buildSword", 6, -1, Items.WOOD_SWORD, h)).c();
    public static Achievement s = (new Achievement("achievement.killEnemy", "killEnemy", 8, -1, Items.BONE, r)).c();
    public static Achievement t = (new Achievement("achievement.killCow", "killCow", 7, -3, Items.LEATHER, r)).c();
    public static Achievement u = (new Achievement("achievement.flyPig", "flyPig", 9, -3, Items.SADDLE, t)).b().c();
    public static Achievement v = (new Achievement("achievement.snipeSkeleton", "snipeSkeleton", 7, 0, Items.BOW, s)).b().c();
    public static Achievement w = (new Achievement("achievement.diamonds", "diamonds", -1, 5, Blocks.DIAMOND_ORE, k)).c();
    public static Achievement x = (new Achievement("achievement.diamondsToYou", "diamondsToYou", -1, 2, Items.DIAMOND, w)).c();
    public static Achievement y = (new Achievement("achievement.portal", "portal", -1, 7, Blocks.OBSIDIAN, w)).c();
    public static Achievement z = (new Achievement("achievement.ghast", "ghast", -4, 8, Items.GHAST_TEAR, y)).b().c();
    public static Achievement A = (new Achievement("achievement.blazeRod", "blazeRod", 0, 9, Items.BLAZE_ROD, y)).c();
    public static Achievement B = (new Achievement("achievement.potion", "potion", 2, 8, Items.POTION, A)).c();
    public static Achievement C = (new Achievement("achievement.theEnd", "theEnd", 3, 10, Items.EYE_OF_ENDER, A)).b().c();
    public static Achievement D = (new Achievement("achievement.theEnd2", "theEnd2", 4, 13, Blocks.DRAGON_EGG, C)).b().c();
    public static Achievement E = (new Achievement("achievement.enchantments", "enchantments", -4, 4, Blocks.ENCHANTMENT_TABLE, w)).c();
    public static Achievement F = (new Achievement("achievement.overkill", "overkill", -4, 1, Items.DIAMOND_SWORD, E)).b().c();
    public static Achievement G = (new Achievement("achievement.bookcase", "bookcase", -3, 6, Blocks.BOOKSHELF, E)).c();
    public static Achievement H = (new Achievement("achievement.breedCow", "breedCow", 7, -5, Items.WHEAT, t)).c();
    public static Achievement I = (new Achievement("achievement.spawnWither", "spawnWither", 7, 12, new ItemStack(Items.SKULL, 1, 1), D)).c();
    public static Achievement J = (new Achievement("achievement.killWither", "killWither", 7, 10, Items.NETHER_STAR, I)).c();
    public static Achievement K = (new Achievement("achievement.fullBeacon", "fullBeacon", 7, 8, Blocks.BEACON, J)).b().c();
    public static Achievement L = (new Achievement("achievement.exploreAllBiomes", "exploreAllBiomes", 4, 8, Items.DIAMOND_BOOTS, C)).a(AchievementSet.class).b().c();

    public static void a() {}
}
