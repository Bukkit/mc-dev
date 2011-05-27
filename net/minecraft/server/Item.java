package net.minecraft.server;

import java.util.Random;

public class Item {

    protected static Random b = new Random();
    public static Item[] c = new Item[32000];
    public static Item IRON_SPADE = (new ItemSpade(0, 2)).a(2, 5).a("shovelIron");
    public static Item IRON_PICKAXE = (new ItemPickaxe(1, 2)).a(2, 6).a("pickaxeIron");
    public static Item IRON_AXE = (new ItemAxe(2, 2)).a(2, 7).a("hatchetIron");
    public static Item FLINT_AND_STEEL = (new ItemFlintAndSteel(3)).a(5, 0).a("flintAndSteel");
    public static Item APPLE = (new ItemFood(4, 4)).a(10, 0).a("apple");
    public static Item BOW = (new ItemBow(5)).a(5, 1).a("bow");
    public static Item ARROW = (new Item(6)).a(5, 2).a("arrow");
    public static Item COAL = (new Item(7)).a(7, 0).a("coal");
    public static Item DIAMOND = (new Item(8)).a(7, 3).a("emerald");
    public static Item IRON_INGOT = (new Item(9)).a(7, 1).a("ingotIron");
    public static Item GOLD_INGOT = (new Item(10)).a(7, 2).a("ingotGold");
    public static Item IRON_SWORD = (new ItemSword(11, 2)).a(2, 4).a("swordIron");
    public static Item WOOD_SWORD = (new ItemSword(12, 0)).a(0, 4).a("swordWood");
    public static Item WOOD_SPADE = (new ItemSpade(13, 0)).a(0, 5).a("shovelWood");
    public static Item WOOD_PICKAXE = (new ItemPickaxe(14, 0)).a(0, 6).a("pickaxeWood");
    public static Item WOOD_AXE = (new ItemAxe(15, 0)).a(0, 7).a("hatchetWood");
    public static Item STONE_SWORD = (new ItemSword(16, 1)).a(1, 4).a("swordStone");
    public static Item STONE_SPADE = (new ItemSpade(17, 1)).a(1, 5).a("shovelStone");
    public static Item STONE_PICKAXE = (new ItemPickaxe(18, 1)).a(1, 6).a("pickaxeStone");
    public static Item STONE_AXE = (new ItemAxe(19, 1)).a(1, 7).a("hatchetStone");
    public static Item DIAMOND_SWORD = (new ItemSword(20, 3)).a(3, 4).a("swordDiamond");
    public static Item DIAMOND_SPADE = (new ItemSpade(21, 3)).a(3, 5).a("shovelDiamond");
    public static Item DIAMOND_PICKAXE = (new ItemPickaxe(22, 3)).a(3, 6).a("pickaxeDiamond");
    public static Item DIAMOND_AXE = (new ItemAxe(23, 3)).a(3, 7).a("hatchetDiamond");
    public static Item STICK = (new Item(24)).a(5, 3).d().a("stick");
    public static Item BOWL = (new Item(25)).a(7, 4).a("bowl");
    public static Item MUSHROOM_SOUP = (new ItemSoup(26, 10)).a(8, 4).a("mushroomStew");
    public static Item GOLD_SWORD = (new ItemSword(27, 0)).a(4, 4).a("swordGold");
    public static Item GOLD_SPADE = (new ItemSpade(28, 0)).a(4, 5).a("shovelGold");
    public static Item GOLD_PICKAXE = (new ItemPickaxe(29, 0)).a(4, 6).a("pickaxeGold");
    public static Item GOLD_AXE = (new ItemAxe(30, 0)).a(4, 7).a("hatchetGold");
    public static Item STRING = (new Item(31)).a(8, 0).a("string");
    public static Item FEATHER = (new Item(32)).a(8, 1).a("feather");
    public static Item SULPHUR = (new Item(33)).a(8, 2).a("sulphur");
    public static Item WOOD_HOE = (new ItemHoe(34, 0)).a(0, 8).a("hoeWood");
    public static Item STONE_HOE = (new ItemHoe(35, 1)).a(1, 8).a("hoeStone");
    public static Item IRON_HOE = (new ItemHoe(36, 2)).a(2, 8).a("hoeIron");
    public static Item DIAMOND_HOE = (new ItemHoe(37, 3)).a(3, 8).a("hoeDiamond");
    public static Item GOLD_HOE = (new ItemHoe(38, 1)).a(4, 8).a("hoeGold");
    public static Item SEEDS = (new ItemSeeds(39, Block.CROPS.bh)).a(9, 0).a("seeds");
    public static Item WHEAT = (new Item(40)).a(9, 1).a("wheat");
    public static Item BREAD = (new ItemFood(41, 5)).a(9, 2).a("bread");
    public static Item LEATHER_HELMET = (new ItemArmor(42, 0, 0, 0)).a(0, 0).a("helmetCloth");
    public static Item LEATHER_CHESTPLATE = (new ItemArmor(43, 0, 0, 1)).a(0, 1).a("chestplateCloth");
    public static Item LEATHER_LEGGINGS = (new ItemArmor(44, 0, 0, 2)).a(0, 2).a("leggingsCloth");
    public static Item LEATHER_BOOTS = (new ItemArmor(45, 0, 0, 3)).a(0, 3).a("bootsCloth");
    public static Item CHAINMAIL_HELMET = (new ItemArmor(46, 1, 1, 0)).a(1, 0).a("helmetChain");
    public static Item CHAINMAIL_CHESTPLATE = (new ItemArmor(47, 1, 1, 1)).a(1, 1).a("chestplateChain");
    public static Item CHAINMAIL_LEGGINGS = (new ItemArmor(48, 1, 1, 2)).a(1, 2).a("leggingsChain");
    public static Item CHAINMAIL_BOOTS = (new ItemArmor(49, 1, 1, 3)).a(1, 3).a("bootsChain");
    public static Item IRON_HELMET = (new ItemArmor(50, 2, 2, 0)).a(2, 0).a("helmetIron");
    public static Item IRON_CHESTPLATE = (new ItemArmor(51, 2, 2, 1)).a(2, 1).a("chestplateIron");
    public static Item IRON_LEGGINGS = (new ItemArmor(52, 2, 2, 2)).a(2, 2).a("leggingsIron");
    public static Item IRON_BOOTS = (new ItemArmor(53, 2, 2, 3)).a(2, 3).a("bootsIron");
    public static Item DIAMOND_HELMET = (new ItemArmor(54, 3, 3, 0)).a(3, 0).a("helmetDiamond");
    public static Item DIAMOND_CHESTPLATE = (new ItemArmor(55, 3, 3, 1)).a(3, 1).a("chestplateDiamond");
    public static Item DIAMOND_LEGGINGS = (new ItemArmor(56, 3, 3, 2)).a(3, 2).a("leggingsDiamond");
    public static Item DIAMOND_BOOTS = (new ItemArmor(57, 3, 3, 3)).a(3, 3).a("bootsDiamond");
    public static Item GOLD_HELMET = (new ItemArmor(58, 1, 4, 0)).a(4, 0).a("helmetGold");
    public static Item GOLD_CHESTPLATE = (new ItemArmor(59, 1, 4, 1)).a(4, 1).a("chestplateGold");
    public static Item GOLD_LEGGINGS = (new ItemArmor(60, 1, 4, 2)).a(4, 2).a("leggingsGold");
    public static Item GOLD_BOOTS = (new ItemArmor(61, 1, 4, 3)).a(4, 3).a("bootsGold");
    public static Item FLINT = (new Item(62)).a(6, 0).a("flint");
    public static Item PORK = (new ItemFood(63, 3)).a(7, 5).a("porkchopRaw");
    public static Item GRILLED_PORK = (new ItemFood(64, 8)).a(8, 5).a("porkchopCooked");
    public static Item PAINTING = (new ItemPainting(65)).a(10, 1).a("painting");
    public static Item GOLDEN_APPLE = (new ItemFood(66, 42)).a(11, 0).a("appleGold");
    public static Item SIGN = (new ItemSign(67)).a(10, 2).a("sign");
    public static Item WOOD_DOOR = (new ItemDoor(68, Material.c)).a(11, 2).a("doorWood");
    public static Item BUCKET = (new ItemBucket(69, 0)).a(10, 4).a("bucket");
    public static Item WATER_BUCKET = (new ItemBucket(70, Block.WATER.bh)).a(11, 4).a("bucketWater");
    public static Item LAVA_BUCKET = (new ItemBucket(71, Block.LAVA.bh)).a(12, 4).a("bucketLava");
    public static Item MINECART = (new ItemMinecart(72, 0)).a(7, 8).a("minecart");
    public static Item SADDLE = (new ItemSaddle(73)).a(8, 6).a("saddle");
    public static Item IRON_DOOR = (new ItemDoor(74, Material.e)).a(12, 2).a("doorIron");
    public static Item REDSTONE = (new ItemRedstone(75)).a(8, 3).a("redstone");
    public static Item SNOW_BALL = (new ItemSnowball(76)).a(14, 0).a("snowball");
    public static Item BOAT = (new ItemBoat(77)).a(8, 8).a("boat");
    public static Item LEATHER = (new Item(78)).a(7, 6).a("leather");
    public static Item MILK_BUCKET = (new ItemBucket(79, -1)).a(13, 4).a("milk");
    public static Item CLAY_BRICK = (new Item(80)).a(6, 1).a("brick");
    public static Item CLAY_BALL = (new Item(81)).a(9, 3).a("clay");
    public static Item SUGAR_CANE = (new ItemReed(82, Block.SUGAR_CANE_BLOCK)).a(11, 1).a("reeds");
    public static Item PAPER = (new Item(83)).a(10, 3).a("paper");
    public static Item BOOK = (new Item(84)).a(11, 3).a("book");
    public static Item SLIME_BALL = (new Item(85)).a(14, 1).a("slimeball");
    public static Item STORAGE_MINECART = (new ItemMinecart(86, 1)).a(7, 9).a("minecartChest");
    public static Item POWERED_MINECART = (new ItemMinecart(87, 2)).a(7, 10).a("minecartFurnace");
    public static Item EGG = (new ItemEgg(88)).a(12, 0).a("egg");
    public static Item COMPASS = (new Item(89)).a(6, 3).a("compass");
    public static Item FISHING_ROD = (new ItemFishingRod(90)).a(5, 4).a("fishingRod");
    public static Item WATCH = (new Item(91)).a(6, 4).a("clock");
    public static Item GLOWSTONE_DUST = (new Item(92)).a(9, 4).a("yellowDust");
    public static Item RAW_FISH = (new ItemFood(93, 2)).a(9, 5).a("fishRaw");
    public static Item COOKED_FISH = (new ItemFood(94, 5)).a(10, 5).a("fishCooked");
    public static Item GOLD_RECORD = (new ItemRecord(2000, "13")).a(0, 15).a("record");
    public static Item GREEN_RECORD = (new ItemRecord(2001, "cat")).a(1, 15).a("record");
    public final int aW;
    protected int aX = 64;
    protected int aY = 32;
    protected int aZ;
    protected boolean ba = false;
    private String a;

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

    public Item a(int i, int j) {
        this.aZ = i + j * 16;
        return this;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        return false;
    }

    public float a(ItemStack itemstack, Block block) {
        return 1.0F;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        return itemstack;
    }

    public int b() {
        return this.aX;
    }

    public int c() {
        return this.aY;
    }

    public void a(ItemStack itemstack, EntityLiving entityliving) {}

    public void a(ItemStack itemstack, int i, int j, int k, int l) {}

    public int a(Entity entity) {
        return 1;
    }

    public boolean a(Block block) {
        return false;
    }

    public void b(ItemStack itemstack, EntityLiving entityliving) {}

    public Item d() {
        this.ba = true;
        return this;
    }

    public Item a(String s) {
        this.a = "item." + s;
        return this;
    }

    public String a() {
        return this.a;
    }
}
