package net.minecraft.server;

import java.util.Collections;
import java.util.HashMap;
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
    private boolean by;
    private int bz;
    private MerchantRecipe bA;
    private static final Map bB = new HashMap();
    private static final Map bC = new HashMap();

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
        this.bw = 0.5F;
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

    public boolean aV() {
        return true;
    }

    protected void bd() {
        if (--this.profession <= 0) {
            this.world.villages.a(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
            this.profession = 70 + this.random.nextInt(50);
            this.village = this.world.villages.getClosestVillage(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ), 32);
            if (this.village == null) {
                this.aE();
            } else {
                ChunkCoordinates chunkcoordinates = this.village.getCenter();

                this.b(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, this.village.getSize());
            }
        }

        if (!this.q() && this.j > 0) {
            --this.j;
            if (this.j <= 0) {
                if (this.by) {
                    this.c(1);
                    this.by = false;
                }

                if (this.bA != null) {
                    this.i.remove(this.bA);
                    this.bA = null;
                }

                this.addEffect(new MobEffect(MobEffectList.REGENERATION.id, 200, 0));
            }
        }

        super.bd();
    }

    public boolean c(EntityHuman entityhuman) {
        if (this.isAlive() && !this.q() && !this.isBaby()) {
            if (!this.world.isStatic) {
                this.a_(entityhuman);
                entityhuman.openTrade(this);
            }

            return true;
        } else {
            return super.c(entityhuman);
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
        nbttagcompound.setInt("Riches", this.bz);
        if (this.i != null) {
            nbttagcompound.setCompound("Offers", this.i.a());
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setProfession(nbttagcompound.getInt("Profession"));
        this.bz = nbttagcompound.getInt("Riches");
        if (nbttagcompound.hasKey("Offers")) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Offers");

            this.i = new MerchantRecipeList(nbttagcompound1);
        }
    }

    protected boolean ba() {
        return false;
    }

    protected String aQ() {
        return "mob.villager.default";
    }

    protected String aR() {
        return "mob.villager.defaulthurt";
    }

    protected String aS() {
        return "mob.villager.defaultdeath";
    }

    public void setProfession(int i) {
        this.datawatcher.watch(16, Integer.valueOf(i));
    }

    public int getProfession() {
        return this.datawatcher.getInt(16);
    }

    public boolean o() {
        return this.f;
    }

    public void e(boolean flag) {
        this.f = flag;
    }

    public void f(boolean flag) {
        this.g = flag;
    }

    public boolean p() {
        return this.g;
    }

    public void c(EntityLiving entityliving) {
        super.c(entityliving);
        if (this.village != null && entityliving != null) {
            this.village.a(entityliving);
        }
    }

    public void a_(EntityHuman entityhuman) {
        this.h = entityhuman;
    }

    public EntityHuman l_() {
        return this.h;
    }

    public boolean q() {
        return this.h != null;
    }

    public void a(MerchantRecipe merchantrecipe) {
        merchantrecipe.f();
        if (merchantrecipe.a((MerchantRecipe) this.i.get(this.i.size() - 1))) {
            this.j = 60;
            this.by = true;
        } else if (this.i.size() > 1) {
            int i = this.random.nextInt(6) + this.random.nextInt(6) + 3;

            if (i <= merchantrecipe.getUses()) {
                this.j = 20;
                this.bA = merchantrecipe;
            }
        }

        if (merchantrecipe.getBuyItem1().id == Item.EMERALD.id) {
            this.bz += merchantrecipe.getBuyItem1().count;
        }
    }

    public MerchantRecipeList getOffers(EntityHuman entityhuman) {
        if (this.i == null) {
            this.c(1);
        }

        return this.i;
    }

    private void c(int i) {
        MerchantRecipeList merchantrecipelist;

        merchantrecipelist = new MerchantRecipeList();
        label44:
        switch (this.getProfession()) {
        case 0:
            a(merchantrecipelist, Item.WHEAT.id, this.random, 0.9F);
            a(merchantrecipelist, Block.WOOL.id, this.random, 0.5F);
            a(merchantrecipelist, Item.RAW_CHICKEN.id, this.random, 0.5F);
            a(merchantrecipelist, Item.COOKED_FISH.id, this.random, 0.4F);
            b(merchantrecipelist, Item.BREAD.id, this.random, 0.9F);
            b(merchantrecipelist, Item.MELON.id, this.random, 0.3F);
            b(merchantrecipelist, Item.APPLE.id, this.random, 0.3F);
            b(merchantrecipelist, Item.COOKIE.id, this.random, 0.3F);
            b(merchantrecipelist, Item.SHEARS.id, this.random, 0.3F);
            b(merchantrecipelist, Item.FLINT_AND_STEEL.id, this.random, 0.3F);
            b(merchantrecipelist, Item.COOKED_CHICKEN.id, this.random, 0.3F);
            b(merchantrecipelist, Item.ARROW.id, this.random, 0.5F);
            if (this.random.nextFloat() < 0.5F) {
                merchantrecipelist.add(new MerchantRecipe(new ItemStack(Block.GRAVEL, 10), new ItemStack(Item.EMERALD), new ItemStack(Item.FLINT.id, 2 + this.random.nextInt(2), 0)));
            }
            break;

        case 1:
            a(merchantrecipelist, Item.PAPER.id, this.random, 0.8F);
            a(merchantrecipelist, Item.BOOK.id, this.random, 0.8F);
            a(merchantrecipelist, Item.WRITTEN_BOOK.id, this.random, 0.3F);
            b(merchantrecipelist, Block.BOOKSHELF.id, this.random, 0.8F);
            b(merchantrecipelist, Block.GLASS.id, this.random, 0.2F);
            b(merchantrecipelist, Item.COMPASS.id, this.random, 0.2F);
            b(merchantrecipelist, Item.WATCH.id, this.random, 0.2F);
            break;

        case 2:
            b(merchantrecipelist, Item.EYE_OF_ENDER.id, this.random, 0.3F);
            b(merchantrecipelist, Item.EXP_BOTTLE.id, this.random, 0.2F);
            b(merchantrecipelist, Item.REDSTONE.id, this.random, 0.4F);
            b(merchantrecipelist, Block.GLOWSTONE.id, this.random, 0.3F);
            int[] aint = new int[] { Item.IRON_SWORD.id, Item.DIAMOND_SWORD.id, Item.IRON_CHESTPLATE.id, Item.DIAMOND_CHESTPLATE.id, Item.IRON_AXE.id, Item.DIAMOND_AXE.id, Item.IRON_PICKAXE.id, Item.DIAMOND_PICKAXE.id};
            int[] aint1 = aint;
            int j = aint.length;
            int k = 0;

            while (true) {
                if (k >= j) {
                    break label44;
                }

                int l = aint1[k];

                if (this.random.nextFloat() < 0.1F) {
                    merchantrecipelist.add(new MerchantRecipe(new ItemStack(l, 1, 0), new ItemStack(Item.EMERALD, 2 + this.random.nextInt(3), 0), EnchantmentManager.a(this.random, new ItemStack(l, 1, 0), 5 + this.random.nextInt(15))));
                }

                ++k;
            }

        case 3:
            a(merchantrecipelist, Item.COAL.id, this.random, 0.7F);
            a(merchantrecipelist, Item.IRON_INGOT.id, this.random, 0.5F);
            a(merchantrecipelist, Item.GOLD_INGOT.id, this.random, 0.5F);
            a(merchantrecipelist, Item.DIAMOND.id, this.random, 0.5F);
            b(merchantrecipelist, Item.IRON_SWORD.id, this.random, 0.5F);
            b(merchantrecipelist, Item.DIAMOND_SWORD.id, this.random, 0.5F);
            b(merchantrecipelist, Item.IRON_AXE.id, this.random, 0.3F);
            b(merchantrecipelist, Item.DIAMOND_AXE.id, this.random, 0.3F);
            b(merchantrecipelist, Item.IRON_PICKAXE.id, this.random, 0.5F);
            b(merchantrecipelist, Item.DIAMOND_PICKAXE.id, this.random, 0.5F);
            b(merchantrecipelist, Item.IRON_SPADE.id, this.random, 0.2F);
            b(merchantrecipelist, Item.DIAMOND_SPADE.id, this.random, 0.2F);
            b(merchantrecipelist, Item.IRON_HOE.id, this.random, 0.2F);
            b(merchantrecipelist, Item.DIAMOND_HOE.id, this.random, 0.2F);
            b(merchantrecipelist, Item.IRON_BOOTS.id, this.random, 0.2F);
            b(merchantrecipelist, Item.DIAMOND_BOOTS.id, this.random, 0.2F);
            b(merchantrecipelist, Item.IRON_HELMET.id, this.random, 0.2F);
            b(merchantrecipelist, Item.DIAMOND_HELMET.id, this.random, 0.2F);
            b(merchantrecipelist, Item.IRON_CHESTPLATE.id, this.random, 0.2F);
            b(merchantrecipelist, Item.DIAMOND_CHESTPLATE.id, this.random, 0.2F);
            b(merchantrecipelist, Item.IRON_LEGGINGS.id, this.random, 0.2F);
            b(merchantrecipelist, Item.DIAMOND_LEGGINGS.id, this.random, 0.2F);
            b(merchantrecipelist, Item.CHAINMAIL_BOOTS.id, this.random, 0.1F);
            b(merchantrecipelist, Item.CHAINMAIL_HELMET.id, this.random, 0.1F);
            b(merchantrecipelist, Item.CHAINMAIL_CHESTPLATE.id, this.random, 0.1F);
            b(merchantrecipelist, Item.CHAINMAIL_LEGGINGS.id, this.random, 0.1F);
            break;

        case 4:
            a(merchantrecipelist, Item.COAL.id, this.random, 0.7F);
            a(merchantrecipelist, Item.PORK.id, this.random, 0.5F);
            a(merchantrecipelist, Item.RAW_BEEF.id, this.random, 0.5F);
            b(merchantrecipelist, Item.SADDLE.id, this.random, 0.1F);
            b(merchantrecipelist, Item.LEATHER_CHESTPLATE.id, this.random, 0.3F);
            b(merchantrecipelist, Item.LEATHER_BOOTS.id, this.random, 0.3F);
            b(merchantrecipelist, Item.LEATHER_HELMET.id, this.random, 0.3F);
            b(merchantrecipelist, Item.LEATHER_LEGGINGS.id, this.random, 0.3F);
            b(merchantrecipelist, Item.GRILLED_PORK.id, this.random, 0.3F);
            b(merchantrecipelist, Item.COOKED_BEEF.id, this.random, 0.3F);
        }

        if (merchantrecipelist.isEmpty()) {
            a(merchantrecipelist, Item.GOLD_INGOT.id, this.random, 1.0F);
        }

        Collections.shuffle(merchantrecipelist);
        if (this.i == null) {
            this.i = new MerchantRecipeList();
        }

        for (int i1 = 0; i1 < i && i1 < merchantrecipelist.size(); ++i1) {
            this.i.a((MerchantRecipe) merchantrecipelist.get(i1));
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
        Tuple tuple = (Tuple) bB.get(Integer.valueOf(i));

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
        Tuple tuple = (Tuple) bC.get(Integer.valueOf(i));

        return tuple == null ? 1 : (((Integer) tuple.a()).intValue() >= ((Integer) tuple.b()).intValue() ? ((Integer) tuple.a()).intValue() : ((Integer) tuple.a()).intValue() + random.nextInt(((Integer) tuple.b()).intValue() - ((Integer) tuple.a()).intValue()));
    }

    static {
        bB.put(Integer.valueOf(Item.COAL.id), new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
        bB.put(Integer.valueOf(Item.IRON_INGOT.id), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bB.put(Integer.valueOf(Item.GOLD_INGOT.id), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bB.put(Integer.valueOf(Item.DIAMOND.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bB.put(Integer.valueOf(Item.PAPER.id), new Tuple(Integer.valueOf(19), Integer.valueOf(30)));
        bB.put(Integer.valueOf(Item.BOOK.id), new Tuple(Integer.valueOf(12), Integer.valueOf(15)));
        bB.put(Integer.valueOf(Item.WRITTEN_BOOK.id), new Tuple(Integer.valueOf(1), Integer.valueOf(1)));
        bB.put(Integer.valueOf(Item.ENDER_PEARL.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bB.put(Integer.valueOf(Item.EYE_OF_ENDER.id), new Tuple(Integer.valueOf(2), Integer.valueOf(3)));
        bB.put(Integer.valueOf(Item.PORK.id), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bB.put(Integer.valueOf(Item.RAW_BEEF.id), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bB.put(Integer.valueOf(Item.RAW_CHICKEN.id), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
        bB.put(Integer.valueOf(Item.COOKED_FISH.id), new Tuple(Integer.valueOf(9), Integer.valueOf(13)));
        bB.put(Integer.valueOf(Item.SEEDS.id), new Tuple(Integer.valueOf(34), Integer.valueOf(48)));
        bB.put(Integer.valueOf(Item.MELON_SEEDS.id), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
        bB.put(Integer.valueOf(Item.PUMPKIN_SEEDS.id), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
        bB.put(Integer.valueOf(Item.WHEAT.id), new Tuple(Integer.valueOf(18), Integer.valueOf(22)));
        bB.put(Integer.valueOf(Block.WOOL.id), new Tuple(Integer.valueOf(14), Integer.valueOf(22)));
        bB.put(Integer.valueOf(Item.ROTTEN_FLESH.id), new Tuple(Integer.valueOf(36), Integer.valueOf(64)));
        bC.put(Integer.valueOf(Item.FLINT_AND_STEEL.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bC.put(Integer.valueOf(Item.SHEARS.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bC.put(Integer.valueOf(Item.IRON_SWORD.id), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        bC.put(Integer.valueOf(Item.DIAMOND_SWORD.id), new Tuple(Integer.valueOf(12), Integer.valueOf(14)));
        bC.put(Integer.valueOf(Item.IRON_AXE.id), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
        bC.put(Integer.valueOf(Item.DIAMOND_AXE.id), new Tuple(Integer.valueOf(9), Integer.valueOf(12)));
        bC.put(Integer.valueOf(Item.IRON_PICKAXE.id), new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
        bC.put(Integer.valueOf(Item.DIAMOND_PICKAXE.id), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bC.put(Integer.valueOf(Item.IRON_SPADE.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Integer.valueOf(Item.DIAMOND_SPADE.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Integer.valueOf(Item.IRON_HOE.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Integer.valueOf(Item.DIAMOND_HOE.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Integer.valueOf(Item.IRON_BOOTS.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Integer.valueOf(Item.DIAMOND_BOOTS.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Integer.valueOf(Item.IRON_HELMET.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Integer.valueOf(Item.DIAMOND_HELMET.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Integer.valueOf(Item.IRON_CHESTPLATE.id), new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
        bC.put(Integer.valueOf(Item.DIAMOND_CHESTPLATE.id), new Tuple(Integer.valueOf(16), Integer.valueOf(19)));
        bC.put(Integer.valueOf(Item.IRON_LEGGINGS.id), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
        bC.put(Integer.valueOf(Item.DIAMOND_LEGGINGS.id), new Tuple(Integer.valueOf(11), Integer.valueOf(14)));
        bC.put(Integer.valueOf(Item.CHAINMAIL_BOOTS.id), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
        bC.put(Integer.valueOf(Item.CHAINMAIL_HELMET.id), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
        bC.put(Integer.valueOf(Item.CHAINMAIL_CHESTPLATE.id), new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
        bC.put(Integer.valueOf(Item.CHAINMAIL_LEGGINGS.id), new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
        bC.put(Integer.valueOf(Item.BREAD.id), new Tuple(Integer.valueOf(-4), Integer.valueOf(-2)));
        bC.put(Integer.valueOf(Item.MELON.id), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bC.put(Integer.valueOf(Item.APPLE.id), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bC.put(Integer.valueOf(Item.COOKIE.id), new Tuple(Integer.valueOf(-10), Integer.valueOf(-7)));
        bC.put(Integer.valueOf(Block.GLASS.id), new Tuple(Integer.valueOf(-5), Integer.valueOf(-3)));
        bC.put(Integer.valueOf(Block.BOOKSHELF.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        bC.put(Integer.valueOf(Item.LEATHER_CHESTPLATE.id), new Tuple(Integer.valueOf(4), Integer.valueOf(5)));
        bC.put(Integer.valueOf(Item.LEATHER_BOOTS.id), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bC.put(Integer.valueOf(Item.LEATHER_HELMET.id), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bC.put(Integer.valueOf(Item.LEATHER_LEGGINGS.id), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
        bC.put(Integer.valueOf(Item.SADDLE.id), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
        bC.put(Integer.valueOf(Item.EXP_BOTTLE.id), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bC.put(Integer.valueOf(Item.REDSTONE.id), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bC.put(Integer.valueOf(Item.COMPASS.id), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bC.put(Integer.valueOf(Item.WATCH.id), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
        bC.put(Integer.valueOf(Block.GLOWSTONE.id), new Tuple(Integer.valueOf(-3), Integer.valueOf(-1)));
        bC.put(Integer.valueOf(Item.GRILLED_PORK.id), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bC.put(Integer.valueOf(Item.COOKED_BEEF.id), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bC.put(Integer.valueOf(Item.COOKED_CHICKEN.id), new Tuple(Integer.valueOf(-8), Integer.valueOf(-6)));
        bC.put(Integer.valueOf(Item.EYE_OF_ENDER.id), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        bC.put(Integer.valueOf(Item.ARROW.id), new Tuple(Integer.valueOf(-5), Integer.valueOf(-19)));
    }
}
