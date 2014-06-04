package net.minecraft.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class EntityVillager extends EntityAgeable implements IMerchant, NPC {

    private int profession;
    private boolean br;
    private boolean bs;
    Village village;
    private EntityHuman tradingPlayer;
    private MerchantRecipeList bu;
    private int bv;
    private boolean bw;
    private int riches;
    private String by;
    private boolean bz;
    private float bA;
    private static final Map bB = new HashMap();
    private static final Map bC = new HashMap();

    public EntityVillager(World world) {
        this(world, 0);
    }

    public EntityVillager(World world, int i) {
        super(world);
        this.setProfession(i);
        this.a(0.6F, 1.8F);
        this.getNavigation().b(true);
        this.getNavigation().a(true);
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalAvoidPlayer(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.goalSelector.a(1, new PathfinderGoalTradeWithPlayer(this));
        this.goalSelector.a(1, new PathfinderGoalLookAtTradingPlayer(this));
        this.goalSelector.a(2, new PathfinderGoalMoveIndoors(this));
        this.goalSelector.a(3, new PathfinderGoalRestrictOpenDoor(this));
        this.goalSelector.a(4, new PathfinderGoalOpenDoor(this, true));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.6D));
        this.goalSelector.a(6, new PathfinderGoalMakeLove(this));
        this.goalSelector.a(7, new PathfinderGoalTakeFlower(this));
        this.goalSelector.a(8, new PathfinderGoalPlay(this, 0.32D));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0F, 1.0F));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityVillager.class, 5.0F, 0.02F));
        this.goalSelector.a(9, new PathfinderGoalRandomStroll(this, 0.6D));
        this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityInsentient.class, 8.0F));
    }

    protected void aD() {
        super.aD();
        this.getAttributeInstance(GenericAttributes.d).setValue(0.5D);
    }

    public boolean bk() {
        return true;
    }

    protected void bp() {
        if (--this.profession <= 0) {
            this.world.villages.a(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
            this.profession = 70 + this.random.nextInt(50);
            this.village = this.world.villages.getClosestVillage(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ), 32);
            if (this.village == null) {
                this.bX();
            } else {
                ChunkCoordinates chunkcoordinates = this.village.getCenter();

                this.a(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, (int) ((float) this.village.getSize() * 0.6F));
                if (this.bz) {
                    this.bz = false;
                    this.village.b(5);
                }
            }
        }

        if (!this.cc() && this.bv > 0) {
            --this.bv;
            if (this.bv <= 0) {
                if (this.bw) {
                    if (this.bu.size() > 1) {
                        Iterator iterator = this.bu.iterator();

                        while (iterator.hasNext()) {
                            MerchantRecipe merchantrecipe = (MerchantRecipe) iterator.next();

                            if (merchantrecipe.g()) {
                                merchantrecipe.a(this.random.nextInt(6) + this.random.nextInt(6) + 2);
                            }
                        }
                    }

                    this.t(1);
                    this.bw = false;
                    if (this.village != null && this.by != null) {
                        this.world.broadcastEntityEffect(this, (byte) 14);
                        this.village.a(this.by, 1);
                    }
                }

                this.addEffect(new MobEffect(MobEffectList.REGENERATION.id, 200, 0));
            }
        }

        super.bp();
    }

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();
        boolean flag = itemstack != null && itemstack.getItem() == Items.MONSTER_EGG;

        if (!flag && this.isAlive() && !this.cc() && !this.isBaby()) {
            if (!this.world.isStatic) {
                this.a_(entityhuman);
                entityhuman.openTrade(this, this.getCustomName());
            }

            return true;
        } else {
            return super.a(entityhuman);
        }
    }

    protected void c() {
        super.c();
        this.datawatcher.a(16, Integer.valueOf(0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Profession", this.getProfession());
        nbttagcompound.setInt("Riches", this.riches);
        if (this.bu != null) {
            nbttagcompound.set("Offers", this.bu.a());
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setProfession(nbttagcompound.getInt("Profession"));
        this.riches = nbttagcompound.getInt("Riches");
        if (nbttagcompound.hasKeyOfType("Offers", 10)) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Offers");

            this.bu = new MerchantRecipeList(nbttagcompound1);
        }
    }

    protected boolean isTypeNotPersistent() {
        return false;
    }

    protected String t() {
        return this.cc() ? "mob.villager.haggle" : "mob.villager.idle";
    }

    protected String aT() {
        return "mob.villager.hit";
    }

    protected String aU() {
        return "mob.villager.death";
    }

    public void setProfession(int i) {
        this.datawatcher.watch(16, Integer.valueOf(i));
    }

    public int getProfession() {
        return this.datawatcher.getInt(16);
    }

    public boolean ca() {
        return this.br;
    }

    public void i(boolean flag) {
        this.br = flag;
    }

    public void j(boolean flag) {
        this.bs = flag;
    }

    public boolean cb() {
        return this.bs;
    }

    public void b(EntityLiving entityliving) {
        super.b(entityliving);
        if (this.village != null && entityliving != null) {
            this.village.a(entityliving);
            if (entityliving instanceof EntityHuman) {
                byte b0 = -1;

                if (this.isBaby()) {
                    b0 = -3;
                }

                this.village.a(entityliving.getName(), b0);
                if (this.isAlive()) {
                    this.world.broadcastEntityEffect(this, (byte) 13);
                }
            }
        }
    }

    public void die(DamageSource damagesource) {
        if (this.village != null) {
            Entity entity = damagesource.getEntity();

            if (entity != null) {
                if (entity instanceof EntityHuman) {
                    this.village.a(entity.getName(), -2);
                } else if (entity instanceof IMonster) {
                    this.village.h();
                }
            } else if (entity == null) {
                EntityHuman entityhuman = this.world.findNearbyPlayer(this, 16.0D);

                if (entityhuman != null) {
                    this.village.h();
                }
            }
        }

        super.die(damagesource);
    }

    public void a_(EntityHuman entityhuman) {
        this.tradingPlayer = entityhuman;
    }

    public EntityHuman b() {
        return this.tradingPlayer;
    }

    public boolean cc() {
        return this.tradingPlayer != null;
    }

    public void a(MerchantRecipe merchantrecipe) {
        merchantrecipe.f();
        this.a_ = -this.q();
        this.makeSound("mob.villager.yes", this.bf(), this.bg());
        if (merchantrecipe.a((MerchantRecipe) this.bu.get(this.bu.size() - 1))) {
            this.bv = 40;
            this.bw = true;
            if (this.tradingPlayer != null) {
                this.by = this.tradingPlayer.getName();
            } else {
                this.by = null;
            }
        }

        if (merchantrecipe.getBuyItem1().getItem() == Items.EMERALD) {
            this.riches += merchantrecipe.getBuyItem1().count;
        }
    }

    public void a_(ItemStack itemstack) {
        if (!this.world.isStatic && this.a_ > -this.q() + 20) {
            this.a_ = -this.q();
            if (itemstack != null) {
                this.makeSound("mob.villager.yes", this.bf(), this.bg());
            } else {
                this.makeSound("mob.villager.no", this.bf(), this.bg());
            }
        }
    }

    public MerchantRecipeList getOffers(EntityHuman entityhuman) {
        if (this.bu == null) {
            this.t(1);
        }

        return this.bu;
    }

    private float p(float f) {
        float f1 = f + this.bA;

        return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
    }

    private void t(int i) {
        if (this.bu != null) {
            this.bA = MathHelper.c((float) this.bu.size()) * 0.2F;
        } else {
            this.bA = 0.0F;
        }

        MerchantRecipeList merchantrecipelist;

        merchantrecipelist = new MerchantRecipeList();
        int j;

        label50:
        switch (this.getProfession()) {
        case 0:
            a(merchantrecipelist, Items.WHEAT, this.random, this.p(0.9F));
            a(merchantrecipelist, Item.getItemOf(Blocks.WOOL), this.random, this.p(0.5F));
            a(merchantrecipelist, Items.RAW_CHICKEN, this.random, this.p(0.5F));
            a(merchantrecipelist, Items.COOKED_FISH, this.random, this.p(0.4F));
            b(merchantrecipelist, Items.BREAD, this.random, this.p(0.9F));
            b(merchantrecipelist, Items.MELON, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.APPLE, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.COOKIE, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.SHEARS, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.FLINT_AND_STEEL, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.COOKED_CHICKEN, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.ARROW, this.random, this.p(0.5F));
            if (this.random.nextFloat() < this.p(0.5F)) {
                merchantrecipelist.add(new MerchantRecipe(new ItemStack(Blocks.GRAVEL, 10), new ItemStack(Items.EMERALD), new ItemStack(Items.FLINT, 4 + this.random.nextInt(2), 0)));
            }
            break;

        case 1:
            a(merchantrecipelist, Items.PAPER, this.random, this.p(0.8F));
            a(merchantrecipelist, Items.BOOK, this.random, this.p(0.8F));
            a(merchantrecipelist, Items.WRITTEN_BOOK, this.random, this.p(0.3F));
            b(merchantrecipelist, Item.getItemOf(Blocks.BOOKSHELF), this.random, this.p(0.8F));
            b(merchantrecipelist, Item.getItemOf(Blocks.GLASS), this.random, this.p(0.2F));
            b(merchantrecipelist, Items.COMPASS, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.WATCH, this.random, this.p(0.2F));
            if (this.random.nextFloat() < this.p(0.07F)) {
                Enchantment enchantment = Enchantment.c[this.random.nextInt(Enchantment.c.length)];
                int k = MathHelper.nextInt(this.random, enchantment.getStartLevel(), enchantment.getMaxLevel());
                ItemStack itemstack = Items.ENCHANTED_BOOK.a(new EnchantmentInstance(enchantment, k));

                j = 2 + this.random.nextInt(5 + k * 10) + 3 * k;
                merchantrecipelist.add(new MerchantRecipe(new ItemStack(Items.BOOK), new ItemStack(Items.EMERALD, j), itemstack));
            }
            break;

        case 2:
            b(merchantrecipelist, Items.EYE_OF_ENDER, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.EXP_BOTTLE, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.REDSTONE, this.random, this.p(0.4F));
            b(merchantrecipelist, Item.getItemOf(Blocks.GLOWSTONE), this.random, this.p(0.3F));
            Item[] aitem = new Item[] { Items.IRON_SWORD, Items.DIAMOND_SWORD, Items.IRON_CHESTPLATE, Items.DIAMOND_CHESTPLATE, Items.IRON_AXE, Items.DIAMOND_AXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE};
            Item[] aitem1 = aitem;
            int l = aitem.length;

            j = 0;

            while (true) {
                if (j >= l) {
                    break label50;
                }

                Item item = aitem1[j];

                if (this.random.nextFloat() < this.p(0.05F)) {
                    merchantrecipelist.add(new MerchantRecipe(new ItemStack(item, 1, 0), new ItemStack(Items.EMERALD, 2 + this.random.nextInt(3), 0), EnchantmentManager.a(this.random, new ItemStack(item, 1, 0), 5 + this.random.nextInt(15))));
                }

                ++j;
            }

        case 3:
            a(merchantrecipelist, Items.COAL, this.random, this.p(0.7F));
            a(merchantrecipelist, Items.IRON_INGOT, this.random, this.p(0.5F));
            a(merchantrecipelist, Items.GOLD_INGOT, this.random, this.p(0.5F));
            a(merchantrecipelist, Items.DIAMOND, this.random, this.p(0.5F));
            b(merchantrecipelist, Items.IRON_SWORD, this.random, this.p(0.5F));
            b(merchantrecipelist, Items.DIAMOND_SWORD, this.random, this.p(0.5F));
            b(merchantrecipelist, Items.IRON_AXE, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.DIAMOND_AXE, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.IRON_PICKAXE, this.random, this.p(0.5F));
            b(merchantrecipelist, Items.DIAMOND_PICKAXE, this.random, this.p(0.5F));
            b(merchantrecipelist, Items.IRON_SPADE, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.DIAMOND_SPADE, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.IRON_HOE, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.DIAMOND_HOE, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.IRON_BOOTS, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.DIAMOND_BOOTS, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.IRON_HELMET, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.DIAMOND_HELMET, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.IRON_CHESTPLATE, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.DIAMOND_CHESTPLATE, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.IRON_LEGGINGS, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.DIAMOND_LEGGINGS, this.random, this.p(0.2F));
            b(merchantrecipelist, Items.CHAINMAIL_BOOTS, this.random, this.p(0.1F));
            b(merchantrecipelist, Items.CHAINMAIL_HELMET, this.random, this.p(0.1F));
            b(merchantrecipelist, Items.CHAINMAIL_CHESTPLATE, this.random, this.p(0.1F));
            b(merchantrecipelist, Items.CHAINMAIL_LEGGINGS, this.random, this.p(0.1F));
            break;

        case 4:
            a(merchantrecipelist, Items.COAL, this.random, this.p(0.7F));
            a(merchantrecipelist, Items.PORK, this.random, this.p(0.5F));
            a(merchantrecipelist, Items.RAW_BEEF, this.random, this.p(0.5F));
            b(merchantrecipelist, Items.SADDLE, this.random, this.p(0.1F));
            b(merchantrecipelist, Items.LEATHER_CHESTPLATE, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.LEATHER_BOOTS, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.LEATHER_HELMET, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.LEATHER_LEGGINGS, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.GRILLED_PORK, this.random, this.p(0.3F));
            b(merchantrecipelist, Items.COOKED_BEEF, this.random, this.p(0.3F));
        }

        if (merchantrecipelist.isEmpty()) {
            a(merchantrecipelist, Items.GOLD_INGOT, this.random, 1.0F);
        }

        Collections.shuffle(merchantrecipelist);
        if (this.bu == null) {
            this.bu = new MerchantRecipeList();
        }

        for (int i1 = 0; i1 < i && i1 < merchantrecipelist.size(); ++i1) {
            this.bu.a((MerchantRecipe) merchantrecipelist.get(i1));
        }
    }

    private static void a(MerchantRecipeList merchantrecipelist, Item item, Random random, float f) {
        if (random.nextFloat() < f) {
            merchantrecipelist.add(new MerchantRecipe(a(item, random), Items.EMERALD));
        }
    }

    private static ItemStack a(Item item, Random random) {
        return new ItemStack(item, b(item, random), 0);
    }

    private static int b(Item item, Random random) {
        Tuple tuple = (Tuple) bB.get(item);

        return tuple == null ? 1 : (((Integer) tuple.a()).intValue() >= ((Integer) tuple.b()).intValue() ? ((Integer) tuple.a()).intValue() : ((Integer) tuple.a()).intValue() + random.nextInt(((Integer) tuple.b()).intValue() - ((Integer) tuple.a()).intValue()));
    }

    private static void b(MerchantRecipeList merchantrecipelist, Item item, Random random, float f) {
        if (random.nextFloat() < f) {
            int i = c(item, random);
            ItemStack itemstack;
            ItemStack itemstack1;

            if (i < 0) {
                itemstack = new ItemStack(Items.EMERALD, 1, 0);
                itemstack1 = new ItemStack(item, -i, 0);
            } else {
                itemstack = new ItemStack(Items.EMERALD, i, 0);
                itemstack1 = new ItemStack(item, 1, 0);
            }

            merchantrecipelist.add(new MerchantRecipe(itemstack, itemstack1));
        }
    }

    private static int c(Item item, Random random) {
        Tuple tuple = (Tuple) bC.get(item);

        return tuple == null ? 1 : (((Integer) tuple.a()).intValue() >= ((Integer) tuple.b()).intValue() ? ((Integer) tuple.a()).intValue() : ((Integer) tuple.a()).intValue() + random.nextInt(((Integer) tuple.b()).intValue() - ((Integer) tuple.a()).intValue()));
    }

    public GroupDataEntity prepare(GroupDataEntity groupdataentity) {
        groupdataentity = super.prepare(groupdataentity);
        this.setProfession(this.world.random.nextInt(5));
        return groupdataentity;
    }

    public void cd() {
        this.bz = true;
    }

    public EntityVillager b(EntityAgeable entityageable) {
        EntityVillager entityvillager = new EntityVillager(this.world);

        entityvillager.prepare((GroupDataEntity) null);
        return entityvillager;
    }

    public boolean bM() {
        return false;
    }

    public EntityAgeable createChild(EntityAgeable entityageable) {
        return this.b(entityageable);
    }

    static {
        bB.put(Items.COAL, new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
        bB.put(Items.IRON_INGOT, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bB.put(Items.GOLD_INGOT, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bB.put(Items.DIAMOND, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bB.put(Items.PAPER, new Tuple(Integer.valueOf(24), Integer.valueOf(36)));
        bB.put(Items.BOOK, new Tuple(Integer.valueOf(11), Integer.valueOf(13)));
        bB.put(Items.WRITTEN_BOOK, new Tuple(Integer.valueOf(1), Integer.valueOf(1)));
        bB.put(Items.ENDER_PEARL, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bB.put(Items.EYE_OF_ENDER, new Tuple(Integer.valueOf(2), Integer.valueOf(3)));
        bB.put(Items.PORK, new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bB.put(Items.RAW_BEEF, new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bB.put(Items.RAW_CHICKEN, new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bB.put(Items.COOKED_FISH, new Tuple(Integer.valueOf(9), Integer.valueOf(13)));
        bB.put(Items.SEEDS, new Tuple(Integer.valueOf(34), Integer.valueOf(48)));
        bB.put(Items.MELON_SEEDS, new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
        bB.put(Items.PUMPKIN_SEEDS, new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
        bB.put(Items.WHEAT, new Tuple(Integer.valueOf(18), Integer.valueOf(22)));
        bB.put(Item.getItemOf(Blocks.WOOL), new Tuple(Integer.valueOf(14), Integer.valueOf(22)));
        bB.put(Items.ROTTEN_FLESH, new Tuple(Integer.valueOf(36), Integer.valueOf(64)));
        bC.put(Items.FLINT_AND_STEEL, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bC.put(Items.SHEARS, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bC.put(Items.IRON_SWORD, new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        bC.put(Items.DIAMOND_SWORD, new Tuple(Integer.valueOf(12), Integer.valueOf(14)));
        bC.put(Items.IRON_AXE, new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
        bC.put(Items.DIAMOND_AXE, new Tuple(Integer.valueOf(9), Integer.valueOf(12)));
        bC.put(Items.IRON_PICKAXE, new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
        bC.put(Items.DIAMOND_PICKAXE, new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bC.put(Items.IRON_SPADE, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Items.DIAMOND_SPADE, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Items.IRON_HOE, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Items.DIAMOND_HOE, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Items.IRON_BOOTS, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Items.DIAMOND_BOOTS, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Items.IRON_HELMET, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Items.DIAMOND_HELMET, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Items.IRON_CHESTPLATE, new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
        bC.put(Items.DIAMOND_CHESTPLATE, new Tuple(Integer.valueOf(16), Integer.valueOf(19)));
        bC.put(Items.IRON_LEGGINGS, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bC.put(Items.DIAMOND_LEGGINGS, new Tuple(Integer.valueOf(11), Integer.valueOf(14)));
        bC.put(Items.CHAINMAIL_BOOTS, new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
        bC.put(Items.CHAINMAIL_HELMET, new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
        bC.put(Items.CHAINMAIL_CHESTPLATE, new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
        bC.put(Items.CHAINMAIL_LEGGINGS, new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
        bC.put(Items.BREAD, new Tuple(Integer.valueOf(-4), Integer.valueOf(-2)));
        bC.put(Items.MELON, new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bC.put(Items.APPLE, new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bC.put(Items.COOKIE, new Tuple(Integer.valueOf(-10), Integer.valueOf(-7)));
        bC.put(Item.getItemOf(Blocks.GLASS), new Tuple(Integer.valueOf(-5), Integer.valueOf(-3)));
        bC.put(Item.getItemOf(Blocks.BOOKSHELF), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bC.put(Items.LEATHER_CHESTPLATE, new Tuple(Integer.valueOf(4), Integer.valueOf(5)));
        bC.put(Items.LEATHER_BOOTS, new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bC.put(Items.LEATHER_HELMET, new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bC.put(Items.LEATHER_LEGGINGS, new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bC.put(Items.SADDLE, new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
        bC.put(Items.EXP_BOTTLE, new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bC.put(Items.REDSTONE, new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bC.put(Items.COMPASS, new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bC.put(Items.WATCH, new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bC.put(Item.getItemOf(Blocks.GLOWSTONE), new Tuple(Integer.valueOf(-3), Integer.valueOf(-1)));
        bC.put(Items.GRILLED_PORK, new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bC.put(Items.COOKED_BEEF, new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bC.put(Items.COOKED_CHICKEN, new Tuple(Integer.valueOf(-8), Integer.valueOf(-6)));
        bC.put(Items.EYE_OF_ENDER, new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        bC.put(Items.ARROW, new Tuple(Integer.valueOf(-12), Integer.valueOf(-8)));
    }
}
