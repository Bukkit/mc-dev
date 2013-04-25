package net.minecraft.server;

import java.util.Random;

public class Item {

    private CreativeModeTab a = null;
    protected static Random e = new Random();
    public static Item[] byId = new Item[32000];
    public static Item IRON_SPADE = (new ItemSpade(0, EnumToolMaterial.IRON)).b("shovelIron");
    public static Item IRON_PICKAXE = (new ItemPickaxe(1, EnumToolMaterial.IRON)).b("pickaxeIron");
    public static Item IRON_AXE = (new ItemAxe(2, EnumToolMaterial.IRON)).b("hatchetIron");
    public static Item FLINT_AND_STEEL = (new ItemFlintAndSteel(3)).b("flintAndSteel");
    public static Item APPLE = (new ItemFood(4, 4, 0.3F, false)).b("apple");
    public static ItemBow BOW = (ItemBow) (new ItemBow(5)).b("bow");
    public static Item ARROW = (new Item(6)).b("arrow").a(CreativeModeTab.j);
    public static Item COAL = (new ItemCoal(7)).b("coal");
    public static Item DIAMOND = (new Item(8)).b("diamond").a(CreativeModeTab.l);
    public static Item IRON_INGOT = (new Item(9)).b("ingotIron").a(CreativeModeTab.l);
    public static Item GOLD_INGOT = (new Item(10)).b("ingotGold").a(CreativeModeTab.l);
    public static Item IRON_SWORD = (new ItemSword(11, EnumToolMaterial.IRON)).b("swordIron");
    public static Item WOOD_SWORD = (new ItemSword(12, EnumToolMaterial.WOOD)).b("swordWood");
    public static Item WOOD_SPADE = (new ItemSpade(13, EnumToolMaterial.WOOD)).b("shovelWood");
    public static Item WOOD_PICKAXE = (new ItemPickaxe(14, EnumToolMaterial.WOOD)).b("pickaxeWood");
    public static Item WOOD_AXE = (new ItemAxe(15, EnumToolMaterial.WOOD)).b("hatchetWood");
    public static Item STONE_SWORD = (new ItemSword(16, EnumToolMaterial.STONE)).b("swordStone");
    public static Item STONE_SPADE = (new ItemSpade(17, EnumToolMaterial.STONE)).b("shovelStone");
    public static Item STONE_PICKAXE = (new ItemPickaxe(18, EnumToolMaterial.STONE)).b("pickaxeStone");
    public static Item STONE_AXE = (new ItemAxe(19, EnumToolMaterial.STONE)).b("hatchetStone");
    public static Item DIAMOND_SWORD = (new ItemSword(20, EnumToolMaterial.DIAMOND)).b("swordDiamond");
    public static Item DIAMOND_SPADE = (new ItemSpade(21, EnumToolMaterial.DIAMOND)).b("shovelDiamond");
    public static Item DIAMOND_PICKAXE = (new ItemPickaxe(22, EnumToolMaterial.DIAMOND)).b("pickaxeDiamond");
    public static Item DIAMOND_AXE = (new ItemAxe(23, EnumToolMaterial.DIAMOND)).b("hatchetDiamond");
    public static Item STICK = (new Item(24)).p().b("stick").a(CreativeModeTab.l);
    public static Item BOWL = (new Item(25)).b("bowl").a(CreativeModeTab.l);
    public static Item MUSHROOM_SOUP = (new ItemSoup(26, 6)).b("mushroomStew");
    public static Item GOLD_SWORD = (new ItemSword(27, EnumToolMaterial.GOLD)).b("swordGold");
    public static Item GOLD_SPADE = (new ItemSpade(28, EnumToolMaterial.GOLD)).b("shovelGold");
    public static Item GOLD_PICKAXE = (new ItemPickaxe(29, EnumToolMaterial.GOLD)).b("pickaxeGold");
    public static Item GOLD_AXE = (new ItemAxe(30, EnumToolMaterial.GOLD)).b("hatchetGold");
    public static Item STRING = (new ItemReed(31, Block.TRIPWIRE)).b("string").a(CreativeModeTab.l);
    public static Item FEATHER = (new Item(32)).b("feather").a(CreativeModeTab.l);
    public static Item SULPHUR = (new Item(33)).b("sulphur").c(PotionBrewer.k).a(CreativeModeTab.l);
    public static Item WOOD_HOE = (new ItemHoe(34, EnumToolMaterial.WOOD)).b("hoeWood");
    public static Item STONE_HOE = (new ItemHoe(35, EnumToolMaterial.STONE)).b("hoeStone");
    public static Item IRON_HOE = (new ItemHoe(36, EnumToolMaterial.IRON)).b("hoeIron");
    public static Item DIAMOND_HOE = (new ItemHoe(37, EnumToolMaterial.DIAMOND)).b("hoeDiamond");
    public static Item GOLD_HOE = (new ItemHoe(38, EnumToolMaterial.GOLD)).b("hoeGold");
    public static Item SEEDS = (new ItemSeeds(39, Block.CROPS.id, Block.SOIL.id)).b("seeds");
    public static Item WHEAT = (new Item(40)).b("wheat").a(CreativeModeTab.l);
    public static Item BREAD = (new ItemFood(41, 5, 0.6F, false)).b("bread");
    public static ItemArmor LEATHER_HELMET = (ItemArmor) (new ItemArmor(42, EnumArmorMaterial.CLOTH, 0, 0)).b("helmetCloth");
    public static ItemArmor LEATHER_CHESTPLATE = (ItemArmor) (new ItemArmor(43, EnumArmorMaterial.CLOTH, 0, 1)).b("chestplateCloth");
    public static ItemArmor LEATHER_LEGGINGS = (ItemArmor) (new ItemArmor(44, EnumArmorMaterial.CLOTH, 0, 2)).b("leggingsCloth");
    public static ItemArmor LEATHER_BOOTS = (ItemArmor) (new ItemArmor(45, EnumArmorMaterial.CLOTH, 0, 3)).b("bootsCloth");
    public static ItemArmor CHAINMAIL_HELMET = (ItemArmor) (new ItemArmor(46, EnumArmorMaterial.CHAIN, 1, 0)).b("helmetChain");
    public static ItemArmor CHAINMAIL_CHESTPLATE = (ItemArmor) (new ItemArmor(47, EnumArmorMaterial.CHAIN, 1, 1)).b("chestplateChain");
    public static ItemArmor CHAINMAIL_LEGGINGS = (ItemArmor) (new ItemArmor(48, EnumArmorMaterial.CHAIN, 1, 2)).b("leggingsChain");
    public static ItemArmor CHAINMAIL_BOOTS = (ItemArmor) (new ItemArmor(49, EnumArmorMaterial.CHAIN, 1, 3)).b("bootsChain");
    public static ItemArmor IRON_HELMET = (ItemArmor) (new ItemArmor(50, EnumArmorMaterial.IRON, 2, 0)).b("helmetIron");
    public static ItemArmor IRON_CHESTPLATE = (ItemArmor) (new ItemArmor(51, EnumArmorMaterial.IRON, 2, 1)).b("chestplateIron");
    public static ItemArmor IRON_LEGGINGS = (ItemArmor) (new ItemArmor(52, EnumArmorMaterial.IRON, 2, 2)).b("leggingsIron");
    public static ItemArmor IRON_BOOTS = (ItemArmor) (new ItemArmor(53, EnumArmorMaterial.IRON, 2, 3)).b("bootsIron");
    public static ItemArmor DIAMOND_HELMET = (ItemArmor) (new ItemArmor(54, EnumArmorMaterial.DIAMOND, 3, 0)).b("helmetDiamond");
    public static ItemArmor DIAMOND_CHESTPLATE = (ItemArmor) (new ItemArmor(55, EnumArmorMaterial.DIAMOND, 3, 1)).b("chestplateDiamond");
    public static ItemArmor DIAMOND_LEGGINGS = (ItemArmor) (new ItemArmor(56, EnumArmorMaterial.DIAMOND, 3, 2)).b("leggingsDiamond");
    public static ItemArmor DIAMOND_BOOTS = (ItemArmor) (new ItemArmor(57, EnumArmorMaterial.DIAMOND, 3, 3)).b("bootsDiamond");
    public static ItemArmor GOLD_HELMET = (ItemArmor) (new ItemArmor(58, EnumArmorMaterial.GOLD, 4, 0)).b("helmetGold");
    public static ItemArmor GOLD_CHESTPLATE = (ItemArmor) (new ItemArmor(59, EnumArmorMaterial.GOLD, 4, 1)).b("chestplateGold");
    public static ItemArmor GOLD_LEGGINGS = (ItemArmor) (new ItemArmor(60, EnumArmorMaterial.GOLD, 4, 2)).b("leggingsGold");
    public static ItemArmor GOLD_BOOTS = (ItemArmor) (new ItemArmor(61, EnumArmorMaterial.GOLD, 4, 3)).b("bootsGold");
    public static Item FLINT = (new Item(62)).b("flint").a(CreativeModeTab.l);
    public static Item PORK = (new ItemFood(63, 3, 0.3F, true)).b("porkchopRaw");
    public static Item GRILLED_PORK = (new ItemFood(64, 8, 0.8F, true)).b("porkchopCooked");
    public static Item PAINTING = (new ItemHanging(65, EntityPainting.class)).b("painting");
    public static Item GOLDEN_APPLE = (new ItemGoldenApple(66, 4, 1.2F, false)).j().a(MobEffectList.REGENERATION.id, 5, 0, 1.0F).b("appleGold");
    public static Item SIGN = (new ItemSign(67)).b("sign");
    public static Item WOOD_DOOR = (new ItemDoor(68, Material.WOOD)).b("doorWood");
    public static Item BUCKET = (new ItemBucket(69, 0)).b("bucket").d(16);
    public static Item WATER_BUCKET = (new ItemBucket(70, Block.WATER.id)).b("bucketWater").a(BUCKET);
    public static Item LAVA_BUCKET = (new ItemBucket(71, Block.LAVA.id)).b("bucketLava").a(BUCKET);
    public static Item MINECART = (new ItemMinecart(72, 0)).b("minecart");
    public static Item SADDLE = (new ItemSaddle(73)).b("saddle");
    public static Item IRON_DOOR = (new ItemDoor(74, Material.ORE)).b("doorIron");
    public static Item REDSTONE = (new ItemRedstone(75)).b("redstone").c(PotionBrewer.i);
    public static Item SNOW_BALL = (new ItemSnowball(76)).b("snowball");
    public static Item BOAT = (new ItemBoat(77)).b("boat");
    public static Item LEATHER = (new Item(78)).b("leather").a(CreativeModeTab.l);
    public static Item MILK_BUCKET = (new ItemMilkBucket(79)).b("milk").a(BUCKET);
    public static Item CLAY_BRICK = (new Item(80)).b("brick").a(CreativeModeTab.l);
    public static Item CLAY_BALL = (new Item(81)).b("clay").a(CreativeModeTab.l);
    public static Item SUGAR_CANE = (new ItemReed(82, Block.SUGAR_CANE_BLOCK)).b("reeds").a(CreativeModeTab.l);
    public static Item PAPER = (new Item(83)).b("paper").a(CreativeModeTab.f);
    public static Item BOOK = (new ItemBook(84)).b("book").a(CreativeModeTab.f);
    public static Item SLIME_BALL = (new Item(85)).b("slimeball").a(CreativeModeTab.f);
    public static Item STORAGE_MINECART = (new ItemMinecart(86, 1)).b("minecartChest");
    public static Item POWERED_MINECART = (new ItemMinecart(87, 2)).b("minecartFurnace");
    public static Item EGG = (new ItemEgg(88)).b("egg");
    public static Item COMPASS = (new Item(89)).b("compass").a(CreativeModeTab.i);
    public static ItemFishingRod FISHING_ROD = (ItemFishingRod) (new ItemFishingRod(90)).b("fishingRod");
    public static Item WATCH = (new Item(91)).b("clock").a(CreativeModeTab.i);
    public static Item GLOWSTONE_DUST = (new Item(92)).b("yellowDust").c(PotionBrewer.j).a(CreativeModeTab.l);
    public static Item RAW_FISH = (new ItemFood(93, 2, 0.3F, false)).b("fishRaw");
    public static Item COOKED_FISH = (new ItemFood(94, 5, 0.6F, false)).b("fishCooked");
    public static Item INK_SACK = (new ItemDye(95)).b("dyePowder");
    public static Item BONE = (new Item(96)).b("bone").p().a(CreativeModeTab.f);
    public static Item SUGAR = (new Item(97)).b("sugar").c(PotionBrewer.b).a(CreativeModeTab.l);
    public static Item CAKE = (new ItemReed(98, Block.CAKE_BLOCK)).d(1).b("cake").a(CreativeModeTab.h);
    public static Item BED = (new ItemBed(99)).d(1).b("bed");
    public static Item DIODE = (new ItemReed(100, Block.DIODE_OFF)).b("diode").a(CreativeModeTab.d);
    public static Item COOKIE = (new ItemFood(101, 2, 0.1F, false)).b("cookie");
    public static ItemWorldMap MAP = (ItemWorldMap) (new ItemWorldMap(102)).b("map");
    public static ItemShears SHEARS = (ItemShears) (new ItemShears(103)).b("shears");
    public static Item MELON = (new ItemFood(104, 2, 0.3F, false)).b("melon");
    public static Item PUMPKIN_SEEDS = (new ItemSeeds(105, Block.PUMPKIN_STEM.id, Block.SOIL.id)).b("seeds_pumpkin");
    public static Item MELON_SEEDS = (new ItemSeeds(106, Block.MELON_STEM.id, Block.SOIL.id)).b("seeds_melon");
    public static Item RAW_BEEF = (new ItemFood(107, 3, 0.3F, true)).b("beefRaw");
    public static Item COOKED_BEEF = (new ItemFood(108, 8, 0.8F, true)).b("beefCooked");
    public static Item RAW_CHICKEN = (new ItemFood(109, 2, 0.3F, true)).a(MobEffectList.HUNGER.id, 30, 0, 0.3F).b("chickenRaw");
    public static Item COOKED_CHICKEN = (new ItemFood(110, 6, 0.6F, true)).b("chickenCooked");
    public static Item ROTTEN_FLESH = (new ItemFood(111, 4, 0.1F, true)).a(MobEffectList.HUNGER.id, 30, 0, 0.8F).b("rottenFlesh");
    public static Item ENDER_PEARL = (new ItemEnderPearl(112)).b("enderPearl");
    public static Item BLAZE_ROD = (new Item(113)).b("blazeRod").a(CreativeModeTab.l);
    public static Item GHAST_TEAR = (new Item(114)).b("ghastTear").c(PotionBrewer.c).a(CreativeModeTab.k);
    public static Item GOLD_NUGGET = (new Item(115)).b("goldNugget").a(CreativeModeTab.l);
    public static Item NETHER_STALK = (new ItemSeeds(116, Block.NETHER_WART.id, Block.SOUL_SAND.id)).b("netherStalkSeeds").c("+4");
    public static ItemPotion POTION = (ItemPotion) (new ItemPotion(117)).b("potion");
    public static Item GLASS_BOTTLE = (new ItemGlassBottle(118)).b("glassBottle");
    public static Item SPIDER_EYE = (new ItemFood(119, 2, 0.8F, false)).a(MobEffectList.POISON.id, 5, 0, 1.0F).b("spiderEye").c(PotionBrewer.d);
    public static Item FERMENTED_SPIDER_EYE = (new Item(120)).b("fermentedSpiderEye").c(PotionBrewer.e).a(CreativeModeTab.k);
    public static Item BLAZE_POWDER = (new Item(121)).b("blazePowder").c(PotionBrewer.g).a(CreativeModeTab.k);
    public static Item MAGMA_CREAM = (new Item(122)).b("magmaCream").c(PotionBrewer.h).a(CreativeModeTab.k);
    public static Item BREWING_STAND = (new ItemReed(123, Block.BREWING_STAND)).b("brewingStand").a(CreativeModeTab.k);
    public static Item CAULDRON = (new ItemReed(124, Block.CAULDRON)).b("cauldron").a(CreativeModeTab.k);
    public static Item EYE_OF_ENDER = (new ItemEnderEye(125)).b("eyeOfEnder");
    public static Item SPECKLED_MELON = (new Item(126)).b("speckledMelon").c(PotionBrewer.f).a(CreativeModeTab.k);
    public static Item MONSTER_EGG = (new ItemMonsterEgg(127)).b("monsterPlacer");
    public static Item EXP_BOTTLE = (new ItemExpBottle(128)).b("expBottle");
    public static Item FIREBALL = (new ItemFireball(129)).b("fireball");
    public static Item BOOK_AND_QUILL = (new ItemBookAndQuill(130)).b("writingBook").a(CreativeModeTab.f);
    public static Item WRITTEN_BOOK = (new ItemWrittenBook(131)).b("writtenBook");
    public static Item EMERALD = (new Item(132)).b("emerald").a(CreativeModeTab.l);
    public static Item ITEM_FRAME = (new ItemHanging(133, EntityItemFrame.class)).b("frame");
    public static Item FLOWER_POT = (new ItemReed(134, Block.FLOWER_POT)).b("flowerPot").a(CreativeModeTab.c);
    public static Item CARROT = (new ItemSeedFood(135, 4, 0.6F, Block.CARROTS.id, Block.SOIL.id)).b("carrots");
    public static Item POTATO = (new ItemSeedFood(136, 1, 0.3F, Block.POTATOES.id, Block.SOIL.id)).b("potato");
    public static Item POTATO_BAKED = (new ItemFood(137, 6, 0.6F, false)).b("potatoBaked");
    public static Item POTATO_POISON = (new ItemFood(138, 2, 0.3F, false)).a(MobEffectList.POISON.id, 5, 0, 0.6F).b("potatoPoisonous");
    public static ItemMapEmpty MAP_EMPTY = (ItemMapEmpty) (new ItemMapEmpty(139)).b("emptyMap");
    public static Item CARROT_GOLDEN = (new ItemFood(140, 6, 1.2F, false)).b("carrotGolden").c(PotionBrewer.l);
    public static Item SKULL = (new ItemSkull(141)).b("skull");
    public static Item CARROT_STICK = (new ItemCarrotStick(142)).b("carrotOnAStick");
    public static Item NETHER_STAR = (new ItemNetherStar(143)).b("netherStar").a(CreativeModeTab.l);
    public static Item PUMPKIN_PIE = (new ItemFood(144, 8, 0.3F, false)).b("pumpkinPie").a(CreativeModeTab.h);
    public static Item FIREWORKS = (new ItemFireworks(145)).b("fireworks");
    public static Item FIREWORKS_CHARGE = (new ItemFireworksCharge(146)).b("fireworksCharge").a(CreativeModeTab.f);
    public static ItemEnchantedBook ENCHANTED_BOOK = (ItemEnchantedBook) (new ItemEnchantedBook(147)).d(1).b("enchantedBook");
    public static Item REDSTONE_COMPARATOR = (new ItemReed(148, Block.REDSTONE_COMPARATOR_OFF)).b("comparator").a(CreativeModeTab.d);
    public static Item NETHER_BRICK = (new Item(149)).b("netherbrick").a(CreativeModeTab.l);
    public static Item QUARTZ = (new Item(150)).b("netherquartz").a(CreativeModeTab.l);
    public static Item MINECART_TNT = (new ItemMinecart(151, 3)).b("minecartTnt");
    public static Item MINECART_HOPPER = (new ItemMinecart(152, 5)).b("minecartHopper");
    public static Item RECORD_1 = (new ItemRecord(2000, "13")).b("record");
    public static Item RECORD_2 = (new ItemRecord(2001, "cat")).b("record");
    public static Item RECORD_3 = (new ItemRecord(2002, "blocks")).b("record");
    public static Item RECORD_4 = (new ItemRecord(2003, "chirp")).b("record");
    public static Item RECORD_5 = (new ItemRecord(2004, "far")).b("record");
    public static Item RECORD_6 = (new ItemRecord(2005, "mall")).b("record");
    public static Item RECORD_7 = (new ItemRecord(2006, "mellohi")).b("record");
    public static Item RECORD_8 = (new ItemRecord(2007, "stal")).b("record");
    public static Item RECORD_9 = (new ItemRecord(2008, "strad")).b("record");
    public static Item RECORD_10 = (new ItemRecord(2009, "ward")).b("record");
    public static Item RECORD_11 = (new ItemRecord(2010, "11")).b("record");
    public static Item RECORD_12 = (new ItemRecord(2011, "wait")).b("record");
    public final int id;
    protected int maxStackSize = 64;
    private int durability = 0;
    protected boolean cr = false;
    protected boolean cs = false;
    private Item craftingResult = null;
    private String d = null;
    private String name;

    protected Item(int i) {
        this.id = 256 + i;
        if (byId[256 + i] != null) {
            System.out.println("CONFLICT @ " + i);
        }

        byId[256 + i] = this;
    }

    public Item d(int i) {
        this.maxStackSize = i;
        return this;
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        return false;
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
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

    public boolean m() {
        return this.cs;
    }

    protected Item a(boolean flag) {
        this.cs = flag;
        return this;
    }

    public int getMaxDurability() {
        return this.durability;
    }

    protected Item setMaxDurability(int i) {
        this.durability = i;
        return this;
    }

    public boolean usesDurability() {
        return this.durability > 0 && !this.cs;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        return false;
    }

    public boolean a(ItemStack itemstack, World world, int i, int j, int k, int l, EntityLiving entityliving) {
        return false;
    }

    public int a(Entity entity) {
        return 1;
    }

    public boolean canDestroySpecialBlock(Block block) {
        return false;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving) {
        return false;
    }

    public Item p() {
        this.cr = true;
        return this;
    }

    public Item b(String s) {
        this.name = s;
        return this;
    }

    public String i(ItemStack itemstack) {
        String s = this.d(itemstack);

        return s == null ? "" : LocaleI18n.get(s);
    }

    public String getName() {
        return "item." + this.name;
    }

    public String d(ItemStack itemstack) {
        return "item." + this.name;
    }

    public Item a(Item item) {
        this.craftingResult = item;
        return this;
    }

    public boolean j(ItemStack itemstack) {
        return true;
    }

    public boolean r() {
        return true;
    }

    public Item s() {
        return this.craftingResult;
    }

    public boolean t() {
        return this.craftingResult != null;
    }

    public String u() {
        return LocaleI18n.get(this.getName() + ".name");
    }

    public String k(ItemStack itemstack) {
        return LocaleI18n.get(this.d(itemstack) + ".name");
    }

    public void a(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {}

    public void d(ItemStack itemstack, World world, EntityHuman entityhuman) {}

    public boolean f() {
        return false;
    }

    public EnumAnimation b_(ItemStack itemstack) {
        return EnumAnimation.NONE;
    }

    public int c_(ItemStack itemstack) {
        return 0;
    }

    public void a(ItemStack itemstack, World world, EntityHuman entityhuman, int i) {}

    protected Item c(String s) {
        this.d = s;
        return this;
    }

    public String v() {
        return this.d;
    }

    public boolean w() {
        return this.d != null;
    }

    public String l(ItemStack itemstack) {
        return ("" + LocaleLanguage.a().c(this.i(itemstack))).trim();
    }

    public boolean d_(ItemStack itemstack) {
        return this.getMaxStackSize() == 1 && this.usesDurability();
    }

    protected MovingObjectPosition a(World world, EntityHuman entityhuman, boolean flag) {
        float f = 1.0F;
        float f1 = entityhuman.lastPitch + (entityhuman.pitch - entityhuman.lastPitch) * f;
        float f2 = entityhuman.lastYaw + (entityhuman.yaw - entityhuman.lastYaw) * f;
        double d0 = entityhuman.lastX + (entityhuman.locX - entityhuman.lastX) * (double) f;
        double d1 = entityhuman.lastY + (entityhuman.locY - entityhuman.lastY) * (double) f + 1.62D - (double) entityhuman.height;
        double d2 = entityhuman.lastZ + (entityhuman.locZ - entityhuman.lastZ) * (double) f;
        Vec3D vec3d = world.getVec3DPool().create(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3D vec3d1 = vec3d.add((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);

        return world.rayTrace(vec3d, vec3d1, flag, !flag);
    }

    public int c() {
        return 0;
    }

    public Item a(CreativeModeTab creativemodetab) {
        this.a = creativemodetab;
        return this;
    }

    public boolean y() {
        return true;
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return false;
    }

    static {
        StatisticList.c();
    }
}
