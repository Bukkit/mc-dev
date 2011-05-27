package net.minecraft.server;

import java.util.Random;

public class Item {

    protected static Random b = new Random();
    public static Item[] c = new Item[32000];
    public static Item IRON_SPADE = (new ItemSpade(0, 2)).a(82);
    public static Item IRON_PICKAXE = (new ItemPickaxe(1, 2)).a(98);
    public static Item IRON_AXE = (new ItemAxe(2, 2)).a(114);
    public static Item FLINT_AND_STEEL = (new ItemFlintAndSteel(3)).a(5);
    public static Item APPLE = (new ItemFood(4, 4)).a(10);
    public static Item BOW = (new ItemBow(5)).a(21);
    public static Item ARROW = (new Item(6)).a(37);
    public static Item COAL = (new Item(7)).a(7);
    public static Item DIAMOND = (new Item(8)).a(55);
    public static Item IRON_INGOT = (new Item(9)).a(23);
    public static Item GOLD_INGOT = (new Item(10)).a(39);
    public static Item IRON_SWORD = (new ItemSword(11, 2)).a(66);
    public static Item WOOD_SWORD = (new ItemSword(12, 0)).a(64);
    public static Item WOOD_SPADE = (new ItemSpade(13, 0)).a(80);
    public static Item WOOD_PICKAXE = (new ItemPickaxe(14, 0)).a(96);
    public static Item WOOD_AXE = (new ItemAxe(15, 0)).a(112);
    public static Item STONE_SWORD = (new ItemSword(16, 1)).a(65);
    public static Item STONE_SPADE = (new ItemSpade(17, 1)).a(81);
    public static Item STONE_PICKAXE = (new ItemPickaxe(18, 1)).a(97);
    public static Item STONE_AXE = (new ItemAxe(19, 1)).a(113);
    public static Item DIAMOND_SWORD = (new ItemSword(20, 3)).a(67);
    public static Item DIAMOND_SPADE = (new ItemSpade(21, 3)).a(83);
    public static Item DIAMOND_PICKAXE = (new ItemPickaxe(22, 3)).a(99);
    public static Item DIAMOND_AXE = (new ItemAxe(23, 3)).a(115);
    public static Item STICK = (new Item(24)).a(53).c();
    public static Item BOWL = (new Item(25)).a(71);
    public static Item MUSHROOM_SOUP = (new ItemSoup(26, 10)).a(72);
    public static Item GOLD_SWORD = (new ItemSword(27, 0)).a(68);
    public static Item GOLD_SPADE = (new ItemSpade(28, 0)).a(84);
    public static Item GOLD_PICKAXE = (new ItemPickaxe(29, 0)).a(100);
    public static Item GOLD_AXE = (new ItemAxe(30, 0)).a(116);
    public static Item STRING = (new Item(31)).a(8);
    public static Item FEATHER = (new Item(32)).a(24);
    public static Item SULPHUR = (new Item(33)).a(40);
    public static Item WOOD_HOE = (new ItemHoe(34, 0)).a(128);
    public static Item STONE_HOE = (new ItemHoe(35, 1)).a(129);
    public static Item IRON_HOE = (new ItemHoe(36, 2)).a(130);
    public static Item DIAMOND_HOE = (new ItemHoe(37, 3)).a(131);
    public static Item GOLD_HOE = (new ItemHoe(38, 1)).a(132);
    public static Item SEEDS = (new ItemSeeds(39, Block.CROPS.bi)).a(9);
    public static Item WHEAT = (new Item(40)).a(25);
    public static Item BREAD = (new ItemFood(41, 5)).a(41);
    public static Item LEATHER_HELMET = (new ItemArmor(42, 0, 0, 0)).a(0);
    public static Item LEATHER_CHESTPLATE = (new ItemArmor(43, 0, 0, 1)).a(16);
    public static Item LEATHER_LEGGINGS = (new ItemArmor(44, 0, 0, 2)).a(32);
    public static Item LEATHER_BOOTS = (new ItemArmor(45, 0, 0, 3)).a(48);
    public static Item CHAINMAIL_HELMET = (new ItemArmor(46, 1, 1, 0)).a(1);
    public static Item CHAINMAIL_CHESTPLATE = (new ItemArmor(47, 1, 1, 1)).a(17);
    public static Item CHAINMAIL_LEGGINGS = (new ItemArmor(48, 1, 1, 2)).a(33);
    public static Item CHAINMAIL_BOOTS = (new ItemArmor(49, 1, 1, 3)).a(49);
    public static Item IRON_HELMET = (new ItemArmor(50, 2, 2, 0)).a(2);
    public static Item IRON_CHESTPLATE = (new ItemArmor(51, 2, 2, 1)).a(18);
    public static Item IRON_LEGGINGS = (new ItemArmor(52, 2, 2, 2)).a(34);
    public static Item IRON_BOOTS = (new ItemArmor(53, 2, 2, 3)).a(50);
    public static Item DIAMOND_HELMET = (new ItemArmor(54, 3, 3, 0)).a(3);
    public static Item DIAMOND_CHESTPLATE = (new ItemArmor(55, 3, 3, 1)).a(19);
    public static Item DIAMOND_LEGGINGS = (new ItemArmor(56, 3, 3, 2)).a(35);
    public static Item DIAMOND_BOOTS = (new ItemArmor(57, 3, 3, 3)).a(51);
    public static Item GOLD_HELMET = (new ItemArmor(58, 1, 4, 0)).a(4);
    public static Item GOLD_CHESTPLATE = (new ItemArmor(59, 1, 4, 1)).a(20);
    public static Item GOLD_LEGGINGS = (new ItemArmor(60, 1, 4, 2)).a(36);
    public static Item GOLD_BOOTS = (new ItemArmor(61, 1, 4, 3)).a(52);
    public static Item FLINT = (new Item(62)).a(6);
    public static Item PORK = (new ItemFood(63, 3)).a(87);
    public static Item GRILLED_PORK = (new ItemFood(64, 8)).a(88);
    public static Item PAINTING = (new ItemPainting(65)).a(26);
    public static Item GOLDEN_APPLE = (new ItemFood(66, 42)).a(11);
    public static Item SIGN = (new ItemSign(67)).a(42);
    public static Item WOOD_DOOR = (new ItemDoor(68, Material.c)).a(43);
    public static Item BUCKET = (new ItemBucket(69, 0)).a(74);
    public static Item WATER_BUCKET = (new ItemBucket(70, Block.WATER.bi)).a(75);
    public static Item LAVA_BUCKET = (new ItemBucket(71, Block.LAVA.bi)).a(76);
    public static Item MINECART = (new ItemMinecart(72, 0)).a(135);
    public static Item SADDLE = (new ItemSaddle(73)).a(104);
    public static Item IRON_DOOR = (new ItemDoor(74, Material.e)).a(44);
    public static Item REDSTONE = (new ItemRedstone(75)).a(56);
    public static Item SNOW_BALL = (new ItemSnowball(76)).a(14);
    public static Item BOAT = (new ItemBoat(77)).a(136);
    public static Item LEATHER = (new Item(78)).a(103);
    public static Item MILK_BUCKET = (new ItemBucket(79, -1)).a(77);
    public static Item CLAY_BRICK = (new Item(80)).a(22);
    public static Item CLAY_BALL = (new Item(81)).a(57);
    public static Item SUGAR_CANE = (new ItemReed(82, Block.SUGAR_CANE_BLOCK)).a(27);
    public static Item PAPER = (new Item(83)).a(58);
    public static Item BOOK = (new Item(84)).a(59);
    public static Item SLIME_BALL = (new Item(85)).a(30);
    public static Item STORAGE_MINECART = (new ItemMinecart(86, 1)).a(151);
    public static Item POWERED_MINECART = (new ItemMinecart(87, 2)).a(167);
    public static Item EGG = (new Item(88)).a(12);
    public static Item COMPASS = (new Item(89)).a(54);
    public static Item FISHING_ROD = (new ItemFishingRod(90)).a(69);
    public static Item WATCH = (new Item(91)).a(70);
    public static Item GLOWSTONE_DUST = (new Item(92)).a(73);
    public static Item RAW_FISH = (new ItemFood(93, 2)).a(89);
    public static Item COOKED_FISH = (new ItemFood(94, 5)).a(90);
    public static Item GOLD_RECORD = (new ItemRecord(2000, "13")).a(240);
    public static Item GREEN_RECORD = (new ItemRecord(2001, "cat")).a(241);
    public final int aW;
    protected int aX = 64;
    protected int aY = 32;
    protected int aZ;
    protected boolean ba = false;

    protected Item(int i) {
        this.aW = 256 + i;
        if (c[256 + i] != null) {
            System.out.println("CONFLICT @ " + i);
        }

        c[256 + i] = this;
    }

    public Item a(int i) {
        this.aZ = i;
        return this;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        return false;
    }

    public float a(ItemStack itemstack, Block block) {
        return 1.0F;
    }

    public int a() {
        return this.aX;
    }

    public int b() {
        return this.aY;
    }

    public void a(ItemStack itemstack, int i, int j, int k, int l) {}

    public boolean a(Block block) {
        return false;
    }

    public Item c() {
        this.ba = true;
        return this;
    }
}
