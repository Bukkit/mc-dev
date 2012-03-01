package net.minecraft.server;

public class EntitySkeleton extends EntityMonster {

    private static final ItemStack a = new ItemStack(Item.BOW, 1);

    public EntitySkeleton(World world) {
        super(world);
        this.texture = "/mob/skeleton.png";
        this.bb = 0.25F;
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalRestrictSun(this));
        this.goalSelector.a(3, new PathfinderGoalFleeSun(this, this.bb));
        this.goalSelector.a(4, new PathfinderGoalArrowAttack(this, this.bb, 1, 60));
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, this.bb));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 16.0F, 0, false));
    }

    public boolean c_() {
        return true;
    }

    public int getMaxHealth() {
        return 20;
    }

    protected String i() {
        return "mob.skeleton";
    }

    protected String j() {
        return "mob.skeletonhurt";
    }

    protected String k() {
        return "mob.skeletonhurt";
    }

    public MonsterType getMonsterType() {
        return MonsterType.UNDEAD;
    }

    public void e() {
        if (this.world.e() && !this.world.isStatic) {
            float f = this.b(1.0F);

            if (f > 0.5F && this.world.isChunkLoaded(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.setOnFire(8);
            }
        }

        super.e();
    }

    public void die(DamageSource damagesource) {
        super.die(damagesource);
        if (damagesource.b() instanceof EntityArrow && damagesource.getEntity() instanceof EntityHuman) {
            EntityHuman entityhuman = (EntityHuman) damagesource.getEntity();
            double d0 = entityhuman.locX - this.locX;
            double d1 = entityhuman.locZ - this.locZ;

            if (d0 * d0 + d1 * d1 >= 2500.0D) {
                entityhuman.a((Statistic) AchievementList.v);
            }
        }
    }

    protected int getLootId() {
        return Item.ARROW.id;
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(3 + i);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(Item.ARROW.id, 1);
        }

        j = this.random.nextInt(3 + i);

        for (k = 0; k < j; ++k) {
            this.b(Item.BONE.id, 1);
        }
    }

    protected void b(int i) {
        if (i > 0) {
            ItemStack itemstack = new ItemStack(Item.BOW);

            EnchantmentManager.a(this.random, itemstack, 5);
            this.a(itemstack, 0.0F);
        } else {
            this.b(Item.BOW.id, 1);
        }
    }
}
