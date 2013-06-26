package net.minecraft.server;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Random;
import java.util.UUID;

public class Item {

    protected static final UUID e = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private CreativeModeTab a;
    protected static Random f = new Random();
    public static Item[] byId = new Item[32000];
    public static Item IRON_SPADE = (new ItemSpade(0, EnumToolMaterial.IRON)).b("shovelIron").d("iron_shovel");
    public static Item IRON_PICKAXE = (new ItemPickaxe(1, EnumToolMaterial.IRON)).b("pickaxeIron").d("iron_pickaxe");
    public static Item IRON_AXE = (new ItemAxe(2, EnumToolMaterial.IRON)).b("hatchetIron").d("iron_axe");
    public static Item FLINT_AND_STEEL = (new ItemFlintAndSteel(3)).b("flintAndSteel").d("flint_and_steel");
    public static Item APPLE = (new ItemFood(4, 4, 0.3F, false)).b("apple").d("apple");
    public static ItemBow BOW = (ItemBow) (new ItemBow(5)).b("bow").d("bow");
    public static Item ARROW = (new Item(6)).b("arrow").a(CreativeModeTab.j).d("arrow");
    public static Item COAL = (new ItemCoal(7)).b("coal").d("coal");
    public static Item DIAMOND = (new Item(8)).b("diamond").a(CreativeModeTab.l).d("diamond");
    public static Item IRON_INGOT = (new Item(9)).b("ingotIron").a(CreativeModeTab.l).d("iron_ingot");
    public static Item GOLD_INGOT = (new Item(10)).b("ingotGold").a(CreativeModeTab.l).d("gold_ingot");
    public static Item IRON_SWORD = (new ItemSword(11, EnumToolMaterial.IRON)).b("swordIron").d("iron_sword");
    public static Item WOOD_SWORD = (new ItemSword(12, EnumToolMaterial.WOOD)).b("swordWood").d("wood_sword");
    public static Item WOOD_SPADE = (new ItemSpade(13, EnumToolMaterial.WOOD)).b("shovelWood").d("wood_shovel");
    public static Item WOOD_PICKAXE = (new ItemPickaxe(14, EnumToolMaterial.WOOD)).b("pickaxeWood").d("wood_pickaxe");
    public static Item WOOD_AXE = (new ItemAxe(15, EnumToolMaterial.WOOD)).b("hatchetWood").d("wood_axe");
    public static Item STONE_SWORD = (new ItemSword(16, EnumToolMaterial.STONE)).b("swordStone").d("stone_sword");
    public static Item STONE_SPADE = (new ItemSpade(17, EnumToolMaterial.STONE)).b("shovelStone").d("stone_shovel");
    public static Item STONE_PICKAXE = (new ItemPickaxe(18, EnumToolMaterial.STONE)).b("pickaxeStone").d("stone_pickaxe");
    public static Item STONE_AXE = (new ItemAxe(19, EnumToolMaterial.STONE)).b("hatchetStone").d("stone_axe");
    public static Item DIAMOND_SWORD = (new ItemSword(20, EnumToolMaterial.DIAMOND)).b("swordDiamond").d("diamond_sword");
    public static Item DIAMOND_SPADE = (new ItemSpade(21, EnumToolMaterial.DIAMOND)).b("shovelDiamond").d("diamond_shovel");
    public static Item DIAMOND_PICKAXE = (new ItemPickaxe(22, EnumToolMaterial.DIAMOND)).b("pickaxeDiamond").d("diamond_pickaxe");
    public static Item DIAMOND_AXE = (new ItemAxe(23, EnumToolMaterial.DIAMOND)).b("hatchetDiamond").d("diamond_axe");
    public static Item STICK = (new Item(24)).q().b("stick").a(CreativeModeTab.l).d("stick");
    public static Item BOWL = (new Item(25)).b("bowl").a(CreativeModeTab.l).d("bowl");
    public static Item MUSHROOM_SOUP = (new ItemSoup(26, 6)).b("mushroomStew").d("mushroom_stew");
    public static Item GOLD_SWORD = (new ItemSword(27, EnumToolMaterial.GOLD)).b("swordGold").d("gold_sword");
    public static Item GOLD_SPADE = (new ItemSpade(28, EnumToolMaterial.GOLD)).b("shovelGold").d("gold_shovel");
    public static Item GOLD_PICKAXE = (new ItemPickaxe(29, EnumToolMaterial.GOLD)).b("pickaxeGold").d("gold_pickaxe");
    public static Item GOLD_AXE = (new ItemAxe(30, EnumToolMaterial.GOLD)).b("hatchetGold").d("gold_axe");
    public static Item STRING = (new ItemReed(31, Block.TRIPWIRE)).b("string").a(CreativeModeTab.l).d("string");
    public static Item FEATHER = (new Item(32)).b("feather").a(CreativeModeTab.l).d("feather");
    public static Item SULPHUR = (new Item(33)).b("sulphur").c(PotionBrewer.k).a(CreativeModeTab.l).d("gunpowder");
    public static Item WOOD_HOE = (new ItemHoe(34, EnumToolMaterial.WOOD)).b("hoeWood").d("wood_hoe");
    public static Item STONE_HOE = (new ItemHoe(35, EnumToolMaterial.STONE)).b("hoeStone").d("stone_hoe");
    public static Item IRON_HOE = (new ItemHoe(36, EnumToolMaterial.IRON)).b("hoeIron").d("iron_hoe");
    public static Item DIAMOND_HOE = (new ItemHoe(37, EnumToolMaterial.DIAMOND)).b("hoeDiamond").d("diamond_hoe");
    public static Item GOLD_HOE = (new ItemHoe(38, EnumToolMaterial.GOLD)).b("hoeGold").d("gold_hoe");
    public static Item SEEDS = (new ItemSeeds(39, Block.CROPS.id, Block.SOIL.id)).b("seeds").d("seeds_wheat");
    public static Item WHEAT = (new Item(40)).b("wheat").a(CreativeModeTab.l).d("wheat");
    public static Item BREAD = (new ItemFood(41, 5, 0.6F, false)).b("bread").d("bread");
    public static ItemArmor LEATHER_HELMET = (ItemArmor) (new ItemArmor(42, EnumArmorMaterial.CLOTH, 0, 0)).b("helmetCloth").d("leather_helmet");
    public static ItemArmor LEATHER_CHESTPLATE = (ItemArmor) (new ItemArmor(43, EnumArmorMaterial.CLOTH, 0, 1)).b("chestplateCloth").d("leather_chestplate");
    public static ItemArmor LEATHER_LEGGINGS = (ItemArmor) (new ItemArmor(44, EnumArmorMaterial.CLOTH, 0, 2)).b("leggingsCloth").d("leather_leggings");
    public static ItemArmor LEATHER_BOOTS = (ItemArmor) (new ItemArmor(45, EnumArmorMaterial.CLOTH, 0, 3)).b("bootsCloth").d("leather_boots");
    public static ItemArmor CHAINMAIL_HELMET = (ItemArmor) (new ItemArmor(46, EnumArmorMaterial.CHAIN, 1, 0)).b("helmetChain").d("chainmail_helmet");
    public static ItemArmor CHAINMAIL_CHESTPLATE = (ItemArmor) (new ItemArmor(47, EnumArmorMaterial.CHAIN, 1, 1)).b("chestplateChain").d("chainmail_chestplate");
    public static ItemArmor CHAINMAIL_LEGGINGS = (ItemArmor) (new ItemArmor(48, EnumArmorMaterial.CHAIN, 1, 2)).b("leggingsChain").d("chainmail_leggings");
    public static ItemArmor CHAINMAIL_BOOTS = (ItemArmor) (new ItemArmor(49, EnumArmorMaterial.CHAIN, 1, 3)).b("bootsChain").d("chainmail_boots");
    public static ItemArmor IRON_HELMET = (ItemArmor) (new ItemArmor(50, EnumArmorMaterial.IRON, 2, 0)).b("helmetIron").d("iron_helmet");
    public static ItemArmor IRON_CHESTPLATE = (ItemArmor) (new ItemArmor(51, EnumArmorMaterial.IRON, 2, 1)).b("chestplateIron").d("iron_chestplate");
    public static ItemArmor IRON_LEGGINGS = (ItemArmor) (new ItemArmor(52, EnumArmorMaterial.IRON, 2, 2)).b("leggingsIron").d("iron_leggings");
    public static ItemArmor IRON_BOOTS = (ItemArmor) (new ItemArmor(53, EnumArmorMaterial.IRON, 2, 3)).b("bootsIron").d("iron_boots");
    public static ItemArmor DIAMOND_HELMET = (ItemArmor) (new ItemArmor(54, EnumArmorMaterial.DIAMOND, 3, 0)).b("helmetDiamond").d("diamond_helmet");
    public static ItemArmor DIAMOND_CHESTPLATE = (ItemArmor) (new ItemArmor(55, EnumArmorMaterial.DIAMOND, 3, 1)).b("chestplateDiamond").d("diamond_chestplate");
    public static ItemArmor DIAMOND_LEGGINGS = (ItemArmor) (new ItemArmor(56, EnumArmorMaterial.DIAMOND, 3, 2)).b("leggingsDiamond").d("diamond_leggings");
    public static ItemArmor DIAMOND_BOOTS = (ItemArmor) (new ItemArmor(57, EnumArmorMaterial.DIAMOND, 3, 3)).b("bootsDiamond").d("diamond_boots");
    public static ItemArmor GOLD_HELMET = (ItemArmor) (new ItemArmor(58, EnumArmorMaterial.GOLD, 4, 0)).b("helmetGold").d("gold_helmet");
    public static ItemArmor GOLD_CHESTPLATE = (ItemArmor) (new ItemArmor(59, EnumArmorMaterial.GOLD, 4, 1)).b("chestplateGold").d("gold_chestplate");
    public static ItemArmor GOLD_LEGGINGS = (ItemArmor) (new ItemArmor(60, EnumArmorMaterial.GOLD, 4, 2)).b("leggingsGold").d("gold_leggings");
    public static ItemArmor GOLD_BOOTS = (ItemArmor) (new ItemArmor(61, EnumArmorMaterial.GOLD, 4, 3)).b("bootsGold").d("gold_boots");
    public static Item FLINT = (new Item(62)).b("flint").a(CreativeModeTab.l).d("flint");
    public static Item PORK = (new ItemFood(63, 3, 0.3F, true)).b("porkchopRaw").d("porkchop_raw");
    public static Item GRILLED_PORK = (new ItemFood(64, 8, 0.8F, true)).b("porkchopCooked").d("porkchop_cooked");
    public static Item PAINTING = (new ItemHanging(65, EntityPainting.class)).b("painting").d("painting");
    public static Item GOLDEN_APPLE = (new ItemGoldenApple(66, 4, 1.2F, false)).k().a(MobEffectList.REGENERATION.id, 5, 1, 1.0F).b("appleGold").d("apple_golden");
    public static Item SIGN = (new ItemSign(67)).b("sign").d("sign");
    public static Item WOOD_DOOR = (new ItemDoor(68, Material.WOOD)).b("doorWood").d("door_wood");
    public static Item BUCKET = (new ItemBucket(69, 0)).b("bucket").d(16).d("bucket_empty");
    public static Item WATER_BUCKET = (new ItemBucket(70, Block.WATER.id)).b("bucketWater").a(BUCKET).d("bucket_water");
    public static Item LAVA_BUCKET = (new ItemBucket(71, Block.LAVA.id)).b("bucketLava").a(BUCKET).d("bucket_lava");
    public static Item MINECART = (new ItemMinecart(72, 0)).b("minecart").d("minecart_normal");
    public static Item SADDLE = (new ItemSaddle(73)).b("saddle").d("saddle");
    public static Item IRON_DOOR = (new ItemDoor(74, Material.ORE)).b("doorIron").d("door_iron");
    public static Item REDSTONE = (new ItemRedstone(75)).b("redstone").c(PotionBrewer.i).d("redstone_dust");
    public static Item SNOW_BALL = (new ItemSnowball(76)).b("snowball").d("snowball");
    public static Item BOAT = (new ItemBoat(77)).b("boat").d("boat");
    public static Item LEATHER = (new Item(78)).b("leather").a(CreativeModeTab.l).d("leather");
    public static Item MILK_BUCKET = (new ItemMilkBucket(79)).b("milk").a(BUCKET).d("bucket_milk");
    public static Item CLAY_BRICK = (new Item(80)).b("brick").a(CreativeModeTab.l).d("brick");
    public static Item CLAY_BALL = (new Item(81)).b("clay").a(CreativeModeTab.l).d("clay_ball");
    public static Item SUGAR_CANE = (new ItemReed(82, Block.SUGAR_CANE_BLOCK)).b("reeds").a(CreativeModeTab.l).d("reeds");
    public static Item PAPER = (new Item(83)).b("paper").a(CreativeModeTab.f).d("paper");
    public static Item BOOK = (new ItemBook(84)).b("book").a(CreativeModeTab.f).d("book_normal");
    public static Item SLIME_BALL = (new Item(85)).b("slimeball").a(CreativeModeTab.f).d("slimeball");
    public static Item STORAGE_MINECART = (new ItemMinecart(86, 1)).b("minecartChest").d("minecart_chest");
    public static Item POWERED_MINECART = (new ItemMinecart(87, 2)).b("minecartFurnace").d("minecart_furnace");
    public static Item EGG = (new ItemEgg(88)).b("egg").d("egg");
    public static Item COMPASS = (new Item(89)).b("compass").a(CreativeModeTab.i).d("compass");
    public static ItemFishingRod FISHING_ROD = (ItemFishingRod) (new ItemFishingRod(90)).b("fishingRod").d("fishing_rod");
    public static Item WATCH = (new Item(91)).b("clock").a(CreativeModeTab.i).d("clock");
    public static Item GLOWSTONE_DUST = (new Item(92)).b("yellowDust").c(PotionBrewer.j).a(CreativeModeTab.l).d("glowstone_dust");
    public static Item RAW_FISH = (new ItemFood(93, 2, 0.3F, false)).b("fishRaw").d("fish_raw");
    public static Item COOKED_FISH = (new ItemFood(94, 5, 0.6F, false)).b("fishCooked").d("fish_cooked");
    public static Item INK_SACK = (new ItemDye(95)).b("dyePowder").d("dye_powder");
    public static Item BONE = (new Item(96)).b("bone").q().a(CreativeModeTab.f).d("bone");
    public static Item SUGAR = (new Item(97)).b("sugar").c(PotionBrewer.b).a(CreativeModeTab.l).d("sugar");
    public static Item CAKE = (new ItemReed(98, Block.CAKE_BLOCK)).d(1).b("cake").a(CreativeModeTab.h).d("cake");
    public static Item BED = (new ItemBed(99)).d(1).b("bed").d("bed");
    public static Item DIODE = (new ItemReed(100, Block.DIODE_OFF)).b("diode").a(CreativeModeTab.d).d("repeater");
    public static Item COOKIE = (new ItemFood(101, 2, 0.1F, false)).b("cookie").d("cookie");
    public static ItemWorldMap MAP = (ItemWorldMap) (new ItemWorldMap(102)).b("map").d("map_filled");
    public static ItemShears SHEARS = (ItemShears) (new ItemShears(103)).b("shears").d("shears");
    public static Item MELON = (new ItemFood(104, 2, 0.3F, false)).b("melon").d("melon");
    public static Item PUMPKIN_SEEDS = (new ItemSeeds(105, Block.PUMPKIN_STEM.id, Block.SOIL.id)).b("seeds_pumpkin").d("seeds_pumpkin");
    public static Item MELON_SEEDS = (new ItemSeeds(106, Block.MELON_STEM.id, Block.SOIL.id)).b("seeds_melon").d("seeds_melon");
    public static Item RAW_BEEF = (new ItemFood(107, 3, 0.3F, true)).b("beefRaw").d("beef_raw");
    public static Item COOKED_BEEF = (new ItemFood(108, 8, 0.8F, true)).b("beefCooked").d("beef_cooked");
    public static Item RAW_CHICKEN = (new ItemFood(109, 2, 0.3F, true)).a(MobEffectList.HUNGER.id, 30, 0, 0.3F).b("chickenRaw").d("chicken_raw");
    public static Item COOKED_CHICKEN = (new ItemFood(110, 6, 0.6F, true)).b("chickenCooked").d("chicken_cooked");
    public static Item ROTTEN_FLESH = (new ItemFood(111, 4, 0.1F, true)).a(MobEffectList.HUNGER.id, 30, 0, 0.8F).b("rottenFlesh").d("rotten_flesh");
    public static Item ENDER_PEARL = (new ItemEnderPearl(112)).b("enderPearl").d("ender_pearl");
    public static Item BLAZE_ROD = (new Item(113)).b("blazeRod").a(CreativeModeTab.l).d("blaze_rod");
    public static Item GHAST_TEAR = (new Item(114)).b("ghastTear").c(PotionBrewer.c).a(CreativeModeTab.k).d("ghast_tear");
    public static Item GOLD_NUGGET = (new Item(115)).b("goldNugget").a(CreativeModeTab.l).d("gold_nugget");
    public static Item NETHER_STALK = (new ItemSeeds(116, Block.NETHER_WART.id, Block.SOUL_SAND.id)).b("netherStalkSeeds").c("+4").d("nether_wart");
    public static ItemPotion POTION = (ItemPotion) (new ItemPotion(117)).b("potion").d("potion");
    public static Item GLASS_BOTTLE = (new ItemGlassBottle(118)).b("glassBottle").d("potion_bottle_empty");
    public static Item SPIDER_EYE = (new ItemFood(119, 2, 0.8F, false)).a(MobEffectList.POISON.id, 5, 0, 1.0F).b("spiderEye").c(PotionBrewer.d).d("spider_eye");
    public static Item FERMENTED_SPIDER_EYE = (new Item(120)).b("fermentedSpiderEye").c(PotionBrewer.e).a(CreativeModeTab.k).d("spider_eye_fermented");
    public static Item BLAZE_POWDER = (new Item(121)).b("blazePowder").c(PotionBrewer.g).a(CreativeModeTab.k).d("blaze_powder");
    public static Item MAGMA_CREAM = (new Item(122)).b("magmaCream").c(PotionBrewer.h).a(CreativeModeTab.k).d("magma_cream");
    public static Item BREWING_STAND = (new ItemReed(123, Block.BREWING_STAND)).b("brewingStand").a(CreativeModeTab.k).d("brewing_stand");
    public static Item CAULDRON = (new ItemReed(124, Block.CAULDRON)).b("cauldron").a(CreativeModeTab.k).d("cauldron");
    public static Item EYE_OF_ENDER = (new ItemEnderEye(125)).b("eyeOfEnder").d("ender_eye");
    public static Item SPECKLED_MELON = (new Item(126)).b("speckledMelon").c(PotionBrewer.f).a(CreativeModeTab.k).d("melon_speckled");
    public static Item MONSTER_EGG = (new ItemMonsterEgg(127)).b("monsterPlacer").d("spawn_egg");
    public static Item EXP_BOTTLE = (new ItemExpBottle(128)).b("expBottle").d("experience_bottle");
    public static Item FIREBALL = (new ItemFireball(129)).b("fireball").d("fireball");
    public static Item BOOK_AND_QUILL = (new ItemBookAndQuill(130)).b("writingBook").a(CreativeModeTab.f).d("book_writable");
    public static Item WRITTEN_BOOK = (new ItemWrittenBook(131)).b("writtenBook").d("book_written");
    public static Item EMERALD = (new Item(132)).b("emerald").a(CreativeModeTab.l).d("emerald");
    public static Item ITEM_FRAME = (new ItemHanging(133, EntityItemFrame.class)).b("frame").d("item_frame");
    public static Item FLOWER_POT = (new ItemReed(134, Block.FLOWER_POT)).b("flowerPot").a(CreativeModeTab.c).d("flower_pot");
    public static Item CARROT = (new ItemSeedFood(135, 4, 0.6F, Block.CARROTS.id, Block.SOIL.id)).b("carrots").d("carrot");
    public static Item POTATO = (new ItemSeedFood(136, 1, 0.3F, Block.POTATOES.id, Block.SOIL.id)).b("potato").d("potato");
    public static Item POTATO_BAKED = (new ItemFood(137, 6, 0.6F, false)).b("potatoBaked").d("potato_baked");
    public static Item POTATO_POISON = (new ItemFood(138, 2, 0.3F, false)).a(MobEffectList.POISON.id, 5, 0, 0.6F).b("potatoPoisonous").d("potato_poisonous");
    public static ItemMapEmpty MAP_EMPTY = (ItemMapEmpty) (new ItemMapEmpty(139)).b("emptyMap").d("map_empty");
    public static Item CARROT_GOLDEN = (new ItemFood(140, 6, 1.2F, false)).b("carrotGolden").c(PotionBrewer.l).d("carrot_golden");
    public static Item SKULL = (new ItemSkull(141)).b("skull").d("skull");
    public static Item CARROT_STICK = (new ItemCarrotStick(142)).b("carrotOnAStick").d("carrot_on_a_stick");
    public static Item NETHER_STAR = (new ItemNetherStar(143)).b("netherStar").a(CreativeModeTab.l).d("nether_star");
    public static Item PUMPKIN_PIE = (new ItemFood(144, 8, 0.3F, false)).b("pumpkinPie").a(CreativeModeTab.h).d("pumpkin_pie");
    public static Item FIREWORKS = (new ItemFireworks(145)).b("fireworks").d("fireworks");
    public static Item FIREWORKS_CHARGE = (new ItemFireworksCharge(146)).b("fireworksCharge").a(CreativeModeTab.f).d("fireworks_charge");
    public static ItemEnchantedBook ENCHANTED_BOOK = (ItemEnchantedBook) (new ItemEnchantedBook(147)).d(1).b("enchantedBook").d("book_enchanted");
    public static Item REDSTONE_COMPARATOR = (new ItemReed(148, Block.REDSTONE_COMPARATOR_OFF)).b("comparator").a(CreativeModeTab.d).d("comparator");
    public static Item NETHER_BRICK = (new Item(149)).b("netherbrick").a(CreativeModeTab.l).d("netherbrick");
    public static Item QUARTZ = (new Item(150)).b("netherquartz").a(CreativeModeTab.l).d("quartz");
    public static Item MINECART_TNT = (new ItemMinecart(151, 3)).b("minecartTnt").d("minecart_tnt");
    public static Item MINECART_HOPPER = (new ItemMinecart(152, 5)).b("minecartHopper").d("minecart_hopper");
    public static Item HORSE_ARMOR_IRON = (new Item(161)).b("horsearmormetal").d(1).a(CreativeModeTab.f).d("iron_horse_armor");
    public static Item HORSE_ARMOR_GOLD = (new Item(162)).b("horsearmorgold").d(1).a(CreativeModeTab.f).d("gold_horse_armor");
    public static Item HORSE_ARMOR_DIAMOND = (new Item(163)).b("horsearmordiamond").d(1).a(CreativeModeTab.f).d("diamond_horse_armor");
    public static Item LEASH = (new ItemLeash(164)).b("leash").d("lead");
    public static Item NAME_TAG = (new ItemNameTag(165)).b("nameTag").d("name_tag");
    public static Item RECORD_1 = (new ItemRecord(2000, "13")).b("record").d("record_13");
    public static Item RECORD_2 = (new ItemRecord(2001, "cat")).b("record").d("record_cat");
    public static Item RECORD_3 = (new ItemRecord(2002, "blocks")).b("record").d("record_blocks");
    public static Item RECORD_4 = (new ItemRecord(2003, "chirp")).b("record").d("record_chirp");
    public static Item RECORD_5 = (new ItemRecord(2004, "far")).b("record").d("record_far");
    public static Item RECORD_6 = (new ItemRecord(2005, "mall")).b("record").d("record_mall");
    public static Item RECORD_7 = (new ItemRecord(2006, "mellohi")).b("record").d("record_mellohi");
    public static Item RECORD_8 = (new ItemRecord(2007, "stal")).b("record").d("record_stal");
    public static Item RECORD_9 = (new ItemRecord(2008, "strad")).b("record").d("record_strad");
    public static Item RECORD_10 = (new ItemRecord(2009, "ward")).b("record").d("record_ward");
    public static Item RECORD_11 = (new ItemRecord(2010, "11")).b("record").d("record_11");
    public static Item RECORD_12 = (new ItemRecord(2011, "wait")).b("record").d("record_wait");
    public final int id;
    protected int maxStackSize = 64;
    private int durability;
    protected boolean cx;
    protected boolean cy;
    private Item craftingResult;
    private String d;
    private String name;
    protected String cA;

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

    public boolean n() {
        return this.cy;
    }

    protected Item a(boolean flag) {
        this.cy = flag;
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
        return this.durability > 0 && !this.cy;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        return false;
    }

    public boolean a(ItemStack itemstack, World world, int i, int j, int k, int l, EntityLiving entityliving) {
        return false;
    }

    public boolean canDestroySpecialBlock(Block block) {
        return false;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, EntityLiving entityliving) {
        return false;
    }

    public Item q() {
        this.cx = true;
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

    public boolean s() {
        return true;
    }

    public Item t() {
        return this.craftingResult;
    }

    public boolean u() {
        return this.craftingResult != null;
    }

    public String v() {
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

    public EnumAnimation c_(ItemStack itemstack) {
        return EnumAnimation.NONE;
    }

    public int d_(ItemStack itemstack) {
        return 0;
    }

    public void a(ItemStack itemstack, World world, EntityHuman entityhuman, int i) {}

    protected Item c(String s) {
        this.d = s;
        return this;
    }

    public String w() {
        return this.d;
    }

    public boolean x() {
        return this.d != null;
    }

    public String l(ItemStack itemstack) {
        return ("" + LocaleI18n.get(this.i(itemstack) + ".name")).trim();
    }

    public boolean e_(ItemStack itemstack) {
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

    public boolean z() {
        return true;
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return false;
    }

    public Multimap h() {
        return HashMultimap.create();
    }

    protected Item d(String s) {
        this.cA = s;
        return this;
    }

    static {
        StatisticList.c();
    }
}
