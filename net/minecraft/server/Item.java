package net.minecraft.server;

import java.util.Random;

public class Item {

    protected static Random c = new Random();
    public static Item[] byId = new Item[32000];
    public static Item IRON_SPADE = (new ItemSpade(0, EnumToolMaterial.IRON)).a(2, 5).a("shovelIron");
    public static Item IRON_PICKAXE = (new ItemPickaxe(1, EnumToolMaterial.IRON)).a(2, 6).a("pickaxeIron");
    public static Item IRON_AXE = (new ItemAxe(2, EnumToolMaterial.IRON)).a(2, 7).a("hatchetIron");
    public static Item FLINT_AND_STEEL = (new ItemFlintAndSteel(3)).a(5, 0).a("flintAndSteel");
    public static Item APPLE = (new ItemFood(4, 4, 0.3F, false)).a(10, 0).a("apple");
    public static Item BOW = (new ItemBow(5)).a(5, 1).a("bow");
    public static Item ARROW = (new Item(6)).a(5, 2).a("arrow");
    public static Item COAL = (new ItemCoal(7)).a(7, 0).a("coal");
    public static Item DIAMOND = (new Item(8)).a(7, 3).a("emerald");
    public static Item IRON_INGOT = (new Item(9)).a(7, 1).a("ingotIron");
    public static Item GOLD_INGOT = (new Item(10)).a(7, 2).a("ingotGold");
    public static Item IRON_SWORD = (new ItemSword(11, EnumToolMaterial.IRON)).a(2, 4).a("swordIron");
    public static Item WOOD_SWORD = (new ItemSword(12, EnumToolMaterial.WOOD)).a(0, 4).a("swordWood");
    public static Item WOOD_SPADE = (new ItemSpade(13, EnumToolMaterial.WOOD)).a(0, 5).a("shovelWood");
    public static Item WOOD_PICKAXE = (new ItemPickaxe(14, EnumToolMaterial.WOOD)).a(0, 6).a("pickaxeWood");
    public static Item WOOD_AXE = (new ItemAxe(15, EnumToolMaterial.WOOD)).a(0, 7).a("hatchetWood");
    public static Item STONE_SWORD = (new ItemSword(16, EnumToolMaterial.STONE)).a(1, 4).a("swordStone");
    public static Item STONE_SPADE = (new ItemSpade(17, EnumToolMaterial.STONE)).a(1, 5).a("shovelStone");
    public static Item STONE_PICKAXE = (new ItemPickaxe(18, EnumToolMaterial.STONE)).a(1, 6).a("pickaxeStone");
    public static Item STONE_AXE = (new ItemAxe(19, EnumToolMaterial.STONE)).a(1, 7).a("hatchetStone");
    public static Item DIAMOND_SWORD = (new ItemSword(20, EnumToolMaterial.DIAMOND)).a(3, 4).a("swordDiamond");
    public static Item DIAMOND_SPADE = (new ItemSpade(21, EnumToolMaterial.DIAMOND)).a(3, 5).a("shovelDiamond");
    public static Item DIAMOND_PICKAXE = (new ItemPickaxe(22, EnumToolMaterial.DIAMOND)).a(3, 6).a("pickaxeDiamond");
    public static Item DIAMOND_AXE = (new ItemAxe(23, EnumToolMaterial.DIAMOND)).a(3, 7).a("hatchetDiamond");
    public static Item STICK = (new Item(24)).a(5, 3).h().a("stick");
    public static Item BOWL = (new Item(25)).a(7, 4).a("bowl");
    public static Item MUSHROOM_SOUP = (new ItemSoup(26, 8)).a(8, 4).a("mushroomStew");
    public static Item GOLD_SWORD = (new ItemSword(27, EnumToolMaterial.GOLD)).a(4, 4).a("swordGold");
    public static Item GOLD_SPADE = (new ItemSpade(28, EnumToolMaterial.GOLD)).a(4, 5).a("shovelGold");
    public static Item GOLD_PICKAXE = (new ItemPickaxe(29, EnumToolMaterial.GOLD)).a(4, 6).a("pickaxeGold");
    public static Item GOLD_AXE = (new ItemAxe(30, EnumToolMaterial.GOLD)).a(4, 7).a("hatchetGold");
    public static Item STRING = (new Item(31)).a(8, 0).a("string");
    public static Item FEATHER = (new Item(32)).a(8, 1).a("feather");
    public static Item SULPHUR = (new Item(33)).a(8, 2).a("sulphur").b(PotionBrewer.k);
    public static Item WOOD_HOE = (new ItemHoe(34, EnumToolMaterial.WOOD)).a(0, 8).a("hoeWood");
    public static Item STONE_HOE = (new ItemHoe(35, EnumToolMaterial.STONE)).a(1, 8).a("hoeStone");
    public static Item IRON_HOE = (new ItemHoe(36, EnumToolMaterial.IRON)).a(2, 8).a("hoeIron");
    public static Item DIAMOND_HOE = (new ItemHoe(37, EnumToolMaterial.DIAMOND)).a(3, 8).a("hoeDiamond");
    public static Item GOLD_HOE = (new ItemHoe(38, EnumToolMaterial.GOLD)).a(4, 8).a("hoeGold");
    public static Item SEEDS = (new ItemSeeds(39, Block.CROPS.id, Block.SOIL.id)).a(9, 0).a("seeds");
    public static Item WHEAT = (new Item(40)).a(9, 1).a("wheat");
    public static Item BREAD = (new ItemFood(41, 5, 0.6F, false)).a(9, 2).a("bread");
    public static Item LEATHER_HELMET = (new ItemArmor(42, EnumArmorMaterial.CLOTH, 0, 0)).a(0, 0).a("helmetCloth");
    public static Item LEATHER_CHESTPLATE = (new ItemArmor(43, EnumArmorMaterial.CLOTH, 0, 1)).a(0, 1).a("chestplateCloth");
    public static Item LEATHER_LEGGINGS = (new ItemArmor(44, EnumArmorMaterial.CLOTH, 0, 2)).a(0, 2).a("leggingsCloth");
    public static Item LEATHER_BOOTS = (new ItemArmor(45, EnumArmorMaterial.CLOTH, 0, 3)).a(0, 3).a("bootsCloth");
    public static Item CHAINMAIL_HELMET = (new ItemArmor(46, EnumArmorMaterial.IRON, 1, 0)).a(1, 0).a("helmetChain");
    public static Item CHAINMAIL_CHESTPLATE = (new ItemArmor(47, EnumArmorMaterial.IRON, 1, 1)).a(1, 1).a("chestplateChain");
    public static Item CHAINMAIL_LEGGINGS = (new ItemArmor(48, EnumArmorMaterial.IRON, 1, 2)).a(1, 2).a("leggingsChain");
    public static Item CHAINMAIL_BOOTS = (new ItemArmor(49, EnumArmorMaterial.IRON, 1, 3)).a(1, 3).a("bootsChain");
    public static Item IRON_HELMET = (new ItemArmor(50, EnumArmorMaterial.CHAIN, 2, 0)).a(2, 0).a("helmetIron");
    public static Item IRON_CHESTPLATE = (new ItemArmor(51, EnumArmorMaterial.CHAIN, 2, 1)).a(2, 1).a("chestplateIron");
    public static Item IRON_LEGGINGS = (new ItemArmor(52, EnumArmorMaterial.CHAIN, 2, 2)).a(2, 2).a("leggingsIron");
    public static Item IRON_BOOTS = (new ItemArmor(53, EnumArmorMaterial.CHAIN, 2, 3)).a(2, 3).a("bootsIron");
    public static Item DIAMOND_HELMET = (new ItemArmor(54, EnumArmorMaterial.DIAMOND, 3, 0)).a(3, 0).a("helmetDiamond");
    public static Item DIAMOND_CHESTPLATE = (new ItemArmor(55, EnumArmorMaterial.DIAMOND, 3, 1)).a(3, 1).a("chestplateDiamond");
    public static Item DIAMOND_LEGGINGS = (new ItemArmor(56, EnumArmorMaterial.DIAMOND, 3, 2)).a(3, 2).a("leggingsDiamond");
    public static Item DIAMOND_BOOTS = (new ItemArmor(57, EnumArmorMaterial.DIAMOND, 3, 3)).a(3, 3).a("bootsDiamond");
    public static Item GOLD_HELMET = (new ItemArmor(58, EnumArmorMaterial.GOLD, 4, 0)).a(4, 0).a("helmetGold");
    public static Item GOLD_CHESTPLATE = (new ItemArmor(59, EnumArmorMaterial.GOLD, 4, 1)).a(4, 1).a("chestplateGold");
    public static Item GOLD_LEGGINGS = (new ItemArmor(60, EnumArmorMaterial.GOLD, 4, 2)).a(4, 2).a("leggingsGold");
    public static Item GOLD_BOOTS = (new ItemArmor(61, EnumArmorMaterial.GOLD, 4, 3)).a(4, 3).a("bootsGold");
    public static Item FLINT = (new Item(62)).a(6, 0).a("flint");
    public static Item PORK = (new ItemFood(63, 3, 0.3F, true)).a(7, 5).a("porkchopRaw");
    public static Item GRILLED_PORK = (new ItemFood(64, 8, 0.8F, true)).a(8, 5).a("porkchopCooked");
    public static Item PAINTING = (new ItemPainting(65)).a(10, 1).a("painting");
    public static Item GOLDEN_APPLE = (new ItemGoldenApple(66, 10, 1.2F, false)).q().a(MobEffectList.REGENERATION.id, 30, 0, 1.0F).a(11, 0).a("appleGold");
    public static Item SIGN = (new ItemSign(67)).a(10, 2).a("sign");
    public static Item WOOD_DOOR = (new ItemDoor(68, Material.WOOD)).a(11, 2).a("doorWood");
    public static Item BUCKET = (new ItemBucket(69, 0)).a(10, 4).a("bucket");
    public static Item WATER_BUCKET = (new ItemBucket(70, Block.WATER.id)).a(11, 4).a("bucketWater").a(BUCKET);
    public static Item LAVA_BUCKET = (new ItemBucket(71, Block.LAVA.id)).a(12, 4).a("bucketLava").a(BUCKET);
    public static Item MINECART = (new ItemMinecart(72, 0)).a(7, 8).a("minecart");
    public static Item SADDLE = (new ItemSaddle(73)).a(8, 6).a("saddle");
    public static Item IRON_DOOR = (new ItemDoor(74, Material.ORE)).a(12, 2).a("doorIron");
    public static Item REDSTONE = (new ItemRedstone(75)).a(8, 3).a("redstone").b(PotionBrewer.i);
    public static Item SNOW_BALL = (new ItemSnowball(76)).a(14, 0).a("snowball");
    public static Item BOAT = (new ItemBoat(77)).a(8, 8).a("boat");
    public static Item LEATHER = (new Item(78)).a(7, 6).a("leather");
    public static Item MILK_BUCKET = (new ItemMilkBucket(79)).a(13, 4).a("milk").a(BUCKET);
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
    public static Item GLOWSTONE_DUST = (new Item(92)).a(9, 4).a("yellowDust").b(PotionBrewer.j);
    public static Item RAW_FISH = (new ItemFood(93, 2, 0.3F, false)).a(9, 5).a("fishRaw");
    public static Item COOKED_FISH = (new ItemFood(94, 5, 0.6F, false)).a(10, 5).a("fishCooked");
    public static Item INK_SACK = (new ItemDye(95)).a(14, 4).a("dyePowder");
    public static Item BONE = (new Item(96)).a(12, 1).a("bone").h();
    public static Item SUGAR = (new Item(97)).a(13, 0).a("sugar").b(PotionBrewer.b);
    public static Item CAKE = (new ItemReed(98, Block.CAKE_BLOCK)).e(1).a(13, 1).a("cake");
    public static Item BED = (new ItemBed(99)).e(1).a(13, 2).a("bed");
    public static Item DIODE = (new ItemReed(100, Block.DIODE_OFF)).a(6, 5).a("diode");
    public static Item COOKIE = (new ItemFood(101, 1, 0.1F, false)).a(12, 5).a("cookie");
    public static ItemWorldMap MAP = (ItemWorldMap) (new ItemWorldMap(102)).a(12, 3).a("map");
    public static ItemShears SHEARS = (ItemShears) (new ItemShears(103)).a(13, 5).a("shears");
    public static Item MELON = (new ItemFood(104, 2, 0.3F, false)).a(13, 6).a("melon");
    public static Item PUMPKIN_SEEDS = (new ItemSeeds(105, Block.PUMPKIN_STEM.id, Block.SOIL.id)).a(13, 3).a("seeds_pumpkin");
    public static Item MELON_SEEDS = (new ItemSeeds(106, Block.MELON_STEM.id, Block.SOIL.id)).a(14, 3).a("seeds_melon");
    public static Item RAW_BEEF = (new ItemFood(107, 3, 0.3F, true)).a(9, 6).a("beefRaw");
    public static Item COOKED_BEEF = (new ItemFood(108, 8, 0.8F, true)).a(10, 6).a("beefCooked");
    public static Item RAW_CHICKEN = (new ItemFood(109, 2, 0.3F, true)).a(MobEffectList.HUNGER.id, 30, 0, 0.3F).a(9, 7).a("chickenRaw");
    public static Item COOKED_CHICKEN = (new ItemFood(110, 6, 0.6F, true)).a(10, 7).a("chickenCooked");
    public static Item ROTTEN_FLESH = (new ItemFood(111, 4, 0.1F, true)).a(MobEffectList.HUNGER.id, 30, 0, 0.8F).a(11, 5).a("rottenFlesh");
    public static Item ENDER_PEARL = (new ItemEnderPearl(112)).a(11, 6).a("enderPearl");
    public static Item BLAZE_ROD = (new Item(113)).a(12, 6).a("blazeRod");
    public static Item GHAST_TEAR = (new Item(114)).a(11, 7).a("ghastTear").b(PotionBrewer.c);
    public static Item GOLD_NUGGET = (new Item(115)).a(12, 7).a("goldNugget");
    public static Item NETHER_STALK = (new ItemSeeds(116, Block.NETHER_WART.id, Block.SOUL_SAND.id)).a(13, 7).a("netherStalkSeeds").b("+4");
    public static ItemPotion POTION = (ItemPotion) (new ItemPotion(117)).a(13, 8).a("potion");
    public static Item GLASS_BOTTLE = (new ItemGlassBottle(118)).a(12, 8).a("glassBottle");
    public static Item SPIDER_EYE = (new ItemFood(119, 2, 0.8F, false)).a(MobEffectList.POISON.id, 5, 0, 1.0F).a(11, 8).a("spiderEye").b(PotionBrewer.d);
    public static Item FERMENTED_SPIDER_EYE = (new Item(120)).a(10, 8).a("fermentedSpiderEye").b(PotionBrewer.e);
    public static Item BLAZE_POWDER = (new Item(121)).a(13, 9).a("blazePowder").b(PotionBrewer.g);
    public static Item MAGMA_CREAM = (new Item(122)).a(13, 10).a("magmaCream").b(PotionBrewer.h);
    public static Item BREWING_STAND = (new ItemReed(123, Block.BREWING_STAND)).a(12, 10).a("brewingStand");
    public static Item CAULDRON = (new ItemReed(124, Block.CAULDRON)).a(12, 9).a("cauldron");
    public static Item EYE_OF_ENDER = (new ItemEnderEye(125)).a(11, 9).a("eyeOfEnder");
    public static Item SPECKLED_MELON = (new Item(126)).a(9, 8).a("speckledMelon").b(PotionBrewer.f);
    public static Item RECORD_1 = (new ItemRecord(2000, "13")).a(0, 15).a("record");
    public static Item RECORD_2 = (new ItemRecord(2001, "cat")).a(1, 15).a("record");
    public static Item RECORD_3 = (new ItemRecord(2002, "blocks")).a(2, 15).a("record");
    public static Item RECORD_4 = (new ItemRecord(2003, "chirp")).a(3, 15).a("record");
    public static Item RECORD_5 = (new ItemRecord(2004, "far")).a(4, 15).a("record");
    public static Item RECORD_6 = (new ItemRecord(2005, "mall")).a(5, 15).a("record");
    public static Item RECORD_7 = (new ItemRecord(2006, "mellohi")).a(6, 15).a("record");
    public static Item RECORD_8 = (new ItemRecord(2007, "stal")).a(7, 15).a("record");
    public static Item RECORD_9 = (new ItemRecord(2008, "strad")).a(8, 15).a("record");
    public static Item RECORD_10 = (new ItemRecord(2009, "ward")).a(9, 15).a("record");
    public static Item RECORD_11 = (new ItemRecord(2010, "11")).a(10, 15).a("record");
    public final int id;
    protected int maxStackSize = 64;
    private int durability = 0;
    protected int textureId;
    protected boolean bP = false;
    protected boolean bQ = false;
    private Item craftingResult = null;
    private String bR = null;
    private String name;

    protected Item(int i) {
        this.id = 256 + i;
        if (byId[256 + i] != null) {
            System.out.println("CONFLICT @ " + i);
        }

        byId[256 + i] = this;
    }

    public Item d(int i) {
        this.textureId = i;
        return this;
    }

    public Item e(int i) {
        this.maxStackSize = i;
        return this;
    }

    public Item a(int i, int j) {
        this.textureId = i + j * 16;
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

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman) {
        return itemstack;
    }

    public int getMaxStackSize() {
        return this.maxStackSize;
    }

    public int filterData(int i) {
        return 0;
    }

    public boolean e() {
        return this.bQ;
    }

    protected Item a(boolean flag) {
        this.bQ = flag;
        return this;
    }

    public int getMaxDurability() {
        return this.durability;
    }

    protected Item f(int i) {
        this.durability = i;
        return this;
    }

    public boolean g() {
        return this.durability > 0 && !this.bQ;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        return false;
    }

    public boolean a(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
        return false;
    }

    public int a(Entity entity) {
        return 1;
    }

    public boolean a(Block block) {
        return false;
    }

    public void a(ItemStack itemstack, EntityLiving entityliving) {}

    public Item h() {
        this.bP = true;
        return this;
    }

    public Item a(String s) {
        this.name = "item." + s;
        return this;
    }

    public String b() {
        return this.name;
    }

    public String a(ItemStack itemstack) {
        return this.name;
    }

    public Item a(Item item) {
        if (this.maxStackSize > 1) {
            throw new IllegalArgumentException("Max stack size must be 1 for items with crafting results");
        } else {
            this.craftingResult = item;
            return this;
        }
    }

    public Item i() {
        return this.craftingResult;
    }

    public boolean j() {
        return this.craftingResult != null;
    }

    public String k() {
        return LocaleI18n.a(this.b() + ".name");
    }

    public void a(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {}

    public void d(ItemStack itemstack, World world, EntityHuman entityhuman) {}

    public boolean n_() {
        return false;
    }

    public EnumAnimation d(ItemStack itemstack) {
        return EnumAnimation.a;
    }

    public int c(ItemStack itemstack) {
        return 0;
    }

    public void a(ItemStack itemstack, World world, EntityHuman entityhuman, int i) {}

    protected Item b(String s) {
        this.bR = s;
        return this;
    }

    public String l() {
        return this.bR;
    }

    public boolean m() {
        return this.bR != null;
    }

    public boolean e(ItemStack itemstack) {
        return this.getMaxStackSize() == 1 && this.g();
    }

    protected MovingObjectPosition a(World world, EntityHuman entityhuman, boolean flag) {
        float f = 1.0F;
        float f1 = entityhuman.lastPitch + (entityhuman.pitch - entityhuman.lastPitch) * f;
        float f2 = entityhuman.lastYaw + (entityhuman.yaw - entityhuman.lastYaw) * f;
        double d0 = entityhuman.lastX + (entityhuman.locX - entityhuman.lastX) * (double) f;
        double d1 = entityhuman.lastY + (entityhuman.locY - entityhuman.lastY) * (double) f + 1.62D - (double) entityhuman.height;
        double d2 = entityhuman.lastZ + (entityhuman.locZ - entityhuman.lastZ) * (double) f;
        Vec3D vec3d = Vec3D.create(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3D vec3d1 = vec3d.add((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
        MovingObjectPosition movingobjectposition = world.rayTrace(vec3d, vec3d1, flag, !flag);

        return movingobjectposition;
    }

    public int c() {
        return 0;
    }

    static {
        StatisticList.c();
    }
}
