package net.minecraft.server;

public class EntitySkeleton extends EntityMonster {

    private static final ItemStack a = new ItemStack(Item.BOW, 1);

    public EntitySkeleton(World world) {
        super(world);
        this.texture = "/mob/skeleton.png";
    }

    public int getMaxHealth() {
        return 20;
    }

    protected String c_() {
        return "mob.skeleton";
    }

    protected String m() {
        return "mob.skeletonhurt";
    }

    protected String n() {
        return "mob.skeletonhurt";
    }

    public boolean damageEntity(DamageSource damagesource, int i) {
        return super.damageEntity(damagesource, i);
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

    public void d() {
        if (this.world.e() && !this.world.isStatic) {
            float f = this.a(1.0F);

            if (f > 0.5F && this.world.isChunkLoaded(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.setOnFire(8);
            }
        }

        super.d();
    }

    protected void a(Entity entity, float f) {
        if (f < 10.0F) {
            double d0 = entity.locX - this.locX;
            double d1 = entity.locZ - this.locZ;

            if (this.attackTicks == 0) {
                EntityArrow entityarrow = new EntityArrow(this.world, this, 1.0F);
                double d2 = entity.locY + (double) entity.getHeadHeight() - 0.699999988079071D - entityarrow.locY;
                float f1 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 0.2F;

                this.world.makeSound(this, "random.bow", 1.0F, 1.0F / (this.random.nextFloat() * 0.4F + 0.8F));
                this.world.addEntity(entityarrow);
                entityarrow.shoot(d0, d2 + (double) f1, d1, 1.6F, 12.0F);
                this.attackTicks = 60;
            }

            this.yaw = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
            this.e = true;
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
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

    public MonsterType getMonsterType() {
        return MonsterType.UNDEAD;
    }
}
