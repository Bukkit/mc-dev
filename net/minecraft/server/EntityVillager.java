package net.minecraft.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class EntityVillager extends EntityAgeable implements NPC, IMerchant {

    private int profession;
    private boolean f;
    private boolean g;
    Village village;
    private EntityHuman h;
    private MerchantRecipeList i;
    private int j;
    private boolean bK;
    private int bL;
    private String bM;
    private boolean bN;
    private float bO;
    private static final Map bP = new HashMap();
    private static final Map bQ = new HashMap();

    public EntityVillager(World world) {
        this(world, 0);
    }

    public EntityVillager(World world, int i) {
        super(world);
        this.profession = 0;
        this.f = false;
        this.g = false;
        this.village = null;
        this.setProfession(i);
        this.texture = "/mob/villager/villager.png";
        this.bI = 0.5F;
        this.a(0.6F, 1.8F);
        this.getNavigation().b(true);
        this.getNavigation().a(true);
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalAvoidPlayer(this, EntityZombie.class, 8.0F, 0.3F, 0.35F));
        this.goalSelector.a(1, new PathfinderGoalTradeWithPlayer(this));
        this.goalSelector.a(1, new PathfinderGoalLookAtTradingPlayer(this));
        this.goalSelector.a(2, new PathfinderGoalMoveIndoors(this));
        this.goalSelector.a(3, new PathfinderGoalRestrictOpenDoor(this));
        this.goalSelector.a(4, new PathfinderGoalOpenDoor(this, true));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.3F));
        this.goalSelector.a(6, new PathfinderGoalMakeLove(this));
        this.goalSelector.a(7, new PathfinderGoalTakeFlower(this));
        this.goalSelector.a(8, new PathfinderGoalPlay(this, 0.32F));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0F, 1.0F));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityVillager.class, 5.0F, 0.02F));
        this.goalSelector.a(9, new PathfinderGoalRandomStroll(this, 0.3F));
        this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityLiving.class, 8.0F));
    }

    public boolean bh() {
        return true;
    }

    protected void bp() {
        if (--this.profession <= 0) {
            this.world.villages.a(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
            this.profession = 70 + this.random.nextInt(50);
            this.village = this.world.villages.getClosestVillage(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ), 32);
            if (this.village == null) {
                this.aO();
            } else {
                ChunkCoordinates chunkcoordinates = this.village.getCenter();

                this.b(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, (int) ((float) this.village.getSize() * 0.6F));
                if (this.bN) {
                    this.bN = false;
                    this.village.b(5);
                }
            }
        }

        if (!this.p() && this.j > 0) {
            --this.j;
            if (this.j <= 0) {
                if (this.bK) {
                    if (this.i.size() > 1) {
                        Iterator iterator = this.i.iterator();

                        while (iterator.hasNext()) {
                            MerchantRecipe merchantrecipe = (MerchantRecipe) iterator.next();

                            if (merchantrecipe.g()) {
                                merchantrecipe.a(this.random.nextInt(6) + this.random.nextInt(6) + 2);
                            }
                        }
                    }

                    this.t(1);
                    this.bK = false;
                    if (this.village != null && this.bM != null) {
                        this.world.broadcastEntityEffect(this, (byte) 14);
                        this.village.a(this.bM, 1);
                    }
                }

                this.addEffect(new MobEffect(MobEffectList.REGENERATION.id, 200, 0));
            }
        }

        super.bp();
    }

    public boolean a_(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();
        boolean flag = itemstack != null && itemstack.id == Item.MONSTER_EGG.id;

        if (!flag && this.isAlive() && !this.p() && !this.isBaby()) {
            if (!this.world.isStatic) {
                this.a(entityhuman);
                entityhuman.openTrade(this, this.getCustomName());
            }

            return true;
        } else {
            return super.a_(entityhuman);
        }
    }

    protected void a() {
        super.a();
        this.datawatcher.a(16, Integer.valueOf(0));
    }

    public int getMaxHealth() {
        return 20;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Profession", this.getProfession());
        nbttagcompound.setInt("Riches", this.bL);
        if (this.i != null) {
            nbttagcompound.setCompound("Offers", this.i.a());
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setProfession(nbttagcompound.getInt("Profession"));
        this.bL = nbttagcompound.getInt("Riches");
        if (nbttagcompound.hasKey("Offers")) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Offers");

            this.i = new MerchantRecipeList(nbttagcompound1);
        }
    }

    protected boolean isTypeNotPersistent() {
        return false;
    }

    protected String bb() {
        return "mob.villager.default";
    }

    protected String bc() {
        return "mob.villager.defaulthurt";
    }

    protected String bd() {
        return "mob.villager.defaultdeath";
    }

    public void setProfession(int i) {
        this.datawatcher.watch(16, Integer.valueOf(i));
    }

    public int getProfession() {
        return this.datawatcher.getInt(16);
    }

    public boolean n() {
        return this.f;
    }

    public void i(boolean flag) {
        this.f = flag;
    }

    public void j(boolean flag) {
        this.g = flag;
    }

    public boolean o() {
        return this.g;
    }

    public void c(EntityLiving entityliving) {
        super.c(entityliving);
        if (this.village != null && entityliving != null) {
            this.village.a(entityliving);
            if (entityliving instanceof EntityHuman) {
                byte b0 = -1;

                if (this.isBaby()) {
                    b0 = -3;
                }

                this.village.a(((EntityHuman) entityliving).getName(), b0);
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
                    this.village.a(((EntityHuman) entity).getName(), -2);
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

    public void a(EntityHuman entityhuman) {
        this.h = entityhuman;
    }

    public EntityHuman m_() {
        return this.h;
    }

    public boolean p() {
        return this.h != null;
    }

    public void a(MerchantRecipe merchantrecipe) {
        merchantrecipe.f();
        if (merchantrecipe.a((MerchantRecipe) this.i.get(this.i.size() - 1))) {
            this.j = 40;
            this.bK = true;
            if (this.h != null) {
                this.bM = this.h.getName();
            } else {
                this.bM = null;
            }
        }

        if (merchantrecipe.getBuyItem1().id == Item.EMERALD.id) {
            this.bL += merchantrecipe.getBuyItem1().count;
        }
    }

    public MerchantRecipeList getOffers(EntityHuman entityhuman) {
        if (this.i == null) {
            this.t(1);
        }

        return this.i;
    }

    private float j(float f) {
        float f1 = f + this.bO;

        return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
    }

    private void t(int i) {
        if (this.i != null) {
            this.bO = MathHelper.c((float) this.i.size()) * 0.2F;
        } else {
            this.bO = 0.0F;
        }

        MerchantRecipeList merchantrecipelist;

        merchantrecipelist = new MerchantRecipeList();
        int j;

        label50:
        switch (this.getProfession()) {
        case 0:
            a(merchantrecipelist, Item.WHEAT.id, this.random, this.j(0.9F));
            a(merchantrecipelist, Block.WOOL.id, this.random, this.j(0.5F));
            a(merchantrecipelist, Item.RAW_CHICKEN.id, this.random, this.j(0.5F));
            a(merchantrecipelist, Item.COOKED_FISH.id, this.random, this.j(0.4F));
            b(merchantrecipelist, Item.BREAD.id, this.random, this.j(0.9F));
            b(merchantrecipelist, Item.MELON.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.APPLE.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.COOKIE.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.SHEARS.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.FLINT_AND_STEEL.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.COOKED_CHICKEN.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.ARROW.id, this.random, this.j(0.5F));
            if (this.random.nextFloat() < this.j(0.5F)) {
                merchantrecipelist.add(new MerchantRecipe(new ItemStack(Block.GRAVEL, 10), new ItemStack(Item.EMERALD), new ItemStack(Item.FLINT.id, 4 + this.random.nextInt(2), 0)));
            }
            break;

        case 1:
            a(merchantrecipelist, Item.PAPER.id, this.random, this.j(0.8F));
            a(merchantrecipelist, Item.BOOK.id, this.random, this.j(0.8F));
            a(merchantrecipelist, Item.WRITTEN_BOOK.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Block.BOOKSHELF.id, this.random, this.j(0.8F));
            b(merchantrecipelist, Block.GLASS.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.COMPASS.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.WATCH.id, this.random, this.j(0.2F));
            if (this.random.nextFloat() < this.j(0.07F)) {
                Enchantment enchantment = Enchantment.c[this.random.nextInt(Enchantment.c.length)];
                int k = MathHelper.nextInt(this.random, enchantment.getStartLevel(), enchantment.getMaxLevel());
                ItemStack itemstack = Item.ENCHANTED_BOOK.a(new EnchantmentInstance(enchantment, k));

                j = 2 + this.random.nextInt(5 + k * 10) + 3 * k;
                merchantrecipelist.add(new MerchantRecipe(new ItemStack(Item.BOOK), new ItemStack(Item.EMERALD, j), itemstack));
            }
            break;

        case 2:
            b(merchantrecipelist, Item.EYE_OF_ENDER.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.EXP_BOTTLE.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.REDSTONE.id, this.random, this.j(0.4F));
            b(merchantrecipelist, Block.GLOWSTONE.id, this.random, this.j(0.3F));
            int[] aint = new int[] { Item.IRON_SWORD.id, Item.DIAMOND_SWORD.id, Item.IRON_CHESTPLATE.id, Item.DIAMOND_CHESTPLATE.id, Item.IRON_AXE.id, Item.DIAMOND_AXE.id, Item.IRON_PICKAXE.id, Item.DIAMOND_PICKAXE.id};
            int[] aint1 = aint;
            int l = aint.length;

            j = 0;

            while (true) {
                if (j >= l) {
                    break label50;
                }

                int i1 = aint1[j];

                if (this.random.nextFloat() < this.j(0.05F)) {
                    merchantrecipelist.add(new MerchantRecipe(new ItemStack(i1, 1, 0), new ItemStack(Item.EMERALD, 2 + this.random.nextInt(3), 0), EnchantmentManager.a(this.random, new ItemStack(i1, 1, 0), 5 + this.random.nextInt(15))));
                }

                ++j;
            }

        case 3:
            a(merchantrecipelist, Item.COAL.id, this.random, this.j(0.7F));
            a(merchantrecipelist, Item.IRON_INGOT.id, this.random, this.j(0.5F));
            a(merchantrecipelist, Item.GOLD_INGOT.id, this.random, this.j(0.5F));
            a(merchantrecipelist, Item.DIAMOND.id, this.random, this.j(0.5F));
            b(merchantrecipelist, Item.IRON_SWORD.id, this.random, this.j(0.5F));
            b(merchantrecipelist, Item.DIAMOND_SWORD.id, this.random, this.j(0.5F));
            b(merchantrecipelist, Item.IRON_AXE.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.DIAMOND_AXE.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.IRON_PICKAXE.id, this.random, this.j(0.5F));
            b(merchantrecipelist, Item.DIAMOND_PICKAXE.id, this.random, this.j(0.5F));
            b(merchantrecipelist, Item.IRON_SPADE.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.DIAMOND_SPADE.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.IRON_HOE.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.DIAMOND_HOE.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.IRON_BOOTS.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.DIAMOND_BOOTS.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.IRON_HELMET.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.DIAMOND_HELMET.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.IRON_CHESTPLATE.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.DIAMOND_CHESTPLATE.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.IRON_LEGGINGS.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.DIAMOND_LEGGINGS.id, this.random, this.j(0.2F));
            b(merchantrecipelist, Item.CHAINMAIL_BOOTS.id, this.random, this.j(0.1F));
            b(merchantrecipelist, Item.CHAINMAIL_HELMET.id, this.random, this.j(0.1F));
            b(merchantrecipelist, Item.CHAINMAIL_CHESTPLATE.id, this.random, this.j(0.1F));
            b(merchantrecipelist, Item.CHAINMAIL_LEGGINGS.id, this.random, this.j(0.1F));
            break;

        case 4:
            a(merchantrecipelist, Item.COAL.id, this.random, this.j(0.7F));
            a(merchantrecipelist, Item.PORK.id, this.random, this.j(0.5F));
            a(merchantrecipelist, Item.RAW_BEEF.id, this.random, this.j(0.5F));
            b(merchantrecipelist, Item.SADDLE.id, this.random, this.j(0.1F));
            b(merchantrecipelist, Item.LEATHER_CHESTPLATE.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.LEATHER_BOOTS.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.LEATHER_HELMET.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.LEATHER_LEGGINGS.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.GRILLED_PORK.id, this.random, this.j(0.3F));
            b(merchantrecipelist, Item.COOKED_BEEF.id, this.random, this.j(0.3F));
        }

        if (merchantrecipelist.isEmpty()) {
            a(merchantrecipelist, Item.GOLD_INGOT.id, this.random, 1.0F);
        }

        Collections.shuffle(merchantrecipelist);
        if (this.i == null) {
            this.i = new MerchantRecipeList();
        }

        for (int j1 = 0; j1 < i && j1 < merchantrecipelist.size(); ++j1) {
            this.i.a((MerchantRecipe) merchantrecipelist.get(j1));
        }
    }

    private static void a(MerchantRecipeList merchantrecipelist, int i, Random random, float f) {
        if (random.nextFloat() < f) {
            merchantrecipelist.add(new MerchantRecipe(a(i, random), Item.EMERALD));
        }
    }

    private static ItemStack a(int i, Random random) {
        return new ItemStack(i, b(i, random), 0);
    }

    private static int b(int i, Random random) {
        Tuple tuple = (Tuple) bP.get(Integer.valueOf(i));

        return tuple == null ? 1 : (((Integer) tuple.a()).intValue() >= ((Integer) tuple.b()).intValue() ? ((Integer) tuple.a()).intValue() : ((Integer) tuple.a()).intValue() + random.nextInt(((Integer) tuple.b()).intValue() - ((Integer) tuple.a()).intValue()));
    }

    private static void b(MerchantRecipeList merchantrecipelist, int i, Random random, float f) {
        if (random.nextFloat() < f) {
            int j = c(i, random);
            ItemStack itemstack;
            ItemStack itemstack1;

            if (j < 0) {
                itemstack = new ItemStack(Item.EMERALD.id, 1, 0);
                itemstack1 = new ItemStack(i, -j, 0);
            } else {
                itemstack = new ItemStack(Item.EMERALD.id, j, 0);
                itemstack1 = new ItemStack(i, 1, 0);
            }

            merchantrecipelist.add(new MerchantRecipe(itemstack, itemstack1));
        }
    }

    private static int c(int i, Random random) {
        Tuple tuple = (Tuple) bQ.get(Integer.valueOf(i));

        return tuple == null ? 1 : (((Integer) tuple.a()).intValue() >= ((Integer) tuple.b()).intValue() ? ((Integer) tuple.a()).intValue() : ((Integer) tuple.a()).intValue() + random.nextInt(((Integer) tuple.b()).intValue() - ((Integer) tuple.a()).intValue()));
    }

    public void bJ() {
        this.setProfession(this.world.random.nextInt(5));
    }

    public void q() {
        this.bN = true;
    }

    public EntityVillager b(EntityAgeable entityageable) {
        EntityVillager entityvillager = new EntityVillager(this.world);

        entityvillager.bJ();
        return entityvillager;
    }

    public EntityAgeable createChild(EntityAgeable entityageable) {
        return this.b(entityageable);
    }

    static {
        bP.put(Integer.valueOf(Item.COAL.id), new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
        bP.put(Integer.valueOf(Item.IRON_INGOT.id), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bP.put(Integer.valueOf(Item.GOLD_INGOT.id), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bP.put(Integer.valueOf(Item.DIAMOND.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bP.put(Integer.valueOf(Item.PAPER.id), new Tuple(Integer.valueOf(24), Integer.valueOf(36)));
        bP.put(Integer.valueOf(Item.BOOK.id), new Tuple(Integer.valueOf(11), Integer.valueOf(13)));
        bP.put(Integer.valueOf(Item.WRITTEN_BOOK.id), new Tuple(Integer.valueOf(1), Integer.valueOf(1)));
        bP.put(Integer.valueOf(Item.ENDER_PEARL.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bP.put(Integer.valueOf(Item.EYE_OF_ENDER.id), new Tuple(Integer.valueOf(2), Integer.valueOf(3)));
        bP.put(Integer.valueOf(Item.PORK.id), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bP.put(Integer.valueOf(Item.RAW_BEEF.id), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bP.put(Integer.valueOf(Item.RAW_CHICKEN.id), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bP.put(Integer.valueOf(Item.COOKED_FISH.id), new Tuple(Integer.valueOf(9), Integer.valueOf(13)));
        bP.put(Integer.valueOf(Item.SEEDS.id), new Tuple(Integer.valueOf(34), Integer.valueOf(48)));
        bP.put(Integer.valueOf(Item.MELON_SEEDS.id), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
        bP.put(Integer.valueOf(Item.PUMPKIN_SEEDS.id), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
        bP.put(Integer.valueOf(Item.WHEAT.id), new Tuple(Integer.valueOf(18), Integer.valueOf(22)));
        bP.put(Integer.valueOf(Block.WOOL.id), new Tuple(Integer.valueOf(14), Integer.valueOf(22)));
        bP.put(Integer.valueOf(Item.ROTTEN_FLESH.id), new Tuple(Integer.valueOf(36), Integer.valueOf(64)));
        bQ.put(Integer.valueOf(Item.FLINT_AND_STEEL.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(Item.SHEARS.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(Item.IRON_SWORD.id), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        bQ.put(Integer.valueOf(Item.DIAMOND_SWORD.id), new Tuple(Integer.valueOf(12), Integer.valueOf(14)));
        bQ.put(Integer.valueOf(Item.IRON_AXE.id), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(Item.DIAMOND_AXE.id), new Tuple(Integer.valueOf(9), Integer.valueOf(12)));
        bQ.put(Integer.valueOf(Item.IRON_PICKAXE.id), new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
        bQ.put(Integer.valueOf(Item.DIAMOND_PICKAXE.id), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bQ.put(Integer.valueOf(Item.IRON_SPADE.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bQ.put(Integer.valueOf(Item.DIAMOND_SPADE.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(Item.IRON_HOE.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bQ.put(Integer.valueOf(Item.DIAMOND_HOE.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(Item.IRON_BOOTS.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bQ.put(Integer.valueOf(Item.DIAMOND_BOOTS.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(Item.IRON_HELMET.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bQ.put(Integer.valueOf(Item.DIAMOND_HELMET.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(Item.IRON_CHESTPLATE.id), new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
        bQ.put(Integer.valueOf(Item.DIAMOND_CHESTPLATE.id), new Tuple(Integer.valueOf(16), Integer.valueOf(19)));
        bQ.put(Integer.valueOf(Item.IRON_LEGGINGS.id), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bQ.put(Integer.valueOf(Item.DIAMOND_LEGGINGS.id), new Tuple(Integer.valueOf(11), Integer.valueOf(14)));
        bQ.put(Integer.valueOf(Item.CHAINMAIL_BOOTS.id), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
        bQ.put(Integer.valueOf(Item.CHAINMAIL_HELMET.id), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
        bQ.put(Integer.valueOf(Item.CHAINMAIL_CHESTPLATE.id), new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
        bQ.put(Integer.valueOf(Item.CHAINMAIL_LEGGINGS.id), new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
        bQ.put(Integer.valueOf(Item.BREAD.id), new Tuple(Integer.valueOf(-4), Integer.valueOf(-2)));
        bQ.put(Integer.valueOf(Item.MELON.id), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bQ.put(Integer.valueOf(Item.APPLE.id), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bQ.put(Integer.valueOf(Item.COOKIE.id), new Tuple(Integer.valueOf(-10), Integer.valueOf(-7)));
        bQ.put(Integer.valueOf(Block.GLASS.id), new Tuple(Integer.valueOf(-5), Integer.valueOf(-3)));
        bQ.put(Integer.valueOf(Block.BOOKSHELF.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(Item.LEATHER_CHESTPLATE.id), new Tuple(Integer.valueOf(4), Integer.valueOf(5)));
        bQ.put(Integer.valueOf(Item.LEATHER_BOOTS.id), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(Item.LEATHER_HELMET.id), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(Item.LEATHER_LEGGINGS.id), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(Item.SADDLE.id), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(Item.EXP_BOTTLE.id), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bQ.put(Integer.valueOf(Item.REDSTONE.id), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bQ.put(Integer.valueOf(Item.COMPASS.id), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bQ.put(Integer.valueOf(Item.WATCH.id), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bQ.put(Integer.valueOf(Block.GLOWSTONE.id), new Tuple(Integer.valueOf(-3), Integer.valueOf(-1)));
        bQ.put(Integer.valueOf(Item.GRILLED_PORK.id), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bQ.put(Integer.valueOf(Item.COOKED_BEEF.id), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bQ.put(Integer.valueOf(Item.COOKED_CHICKEN.id), new Tuple(Integer.valueOf(-8), Integer.valueOf(-6)));
        bQ.put(Integer.valueOf(Item.EYE_OF_ENDER.id), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        bQ.put(Integer.valueOf(Item.ARROW.id), new Tuple(Integer.valueOf(-12), Integer.valueOf(-8)));
    }
}
