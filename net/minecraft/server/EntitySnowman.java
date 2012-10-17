package net.minecraft.server;

public class EntitySnowman extends EntityGolem implements IRangedEntity {

    public EntitySnowman(World world) {
        super(world);
        this.texture = "/mob/snowman.png";
        this.a(0.4F, 1.8F);
        this.getNavigation().a(true);
        this.goalSelector.a(1, new PathfinderGoalArrowAttack(this, 0.25F, 20, 10.0F));
        this.goalSelector.a(2, new PathfinderGoalRandomStroll(this, 0.2F));
        this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
        this.goalSelector.a(4, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityLiving.class, 16.0F, 0, true, false, IMonster.a));
    }

    public boolean bb() {
        return true;
    }

    public int getMaxHealth() {
        return 4;
    }

    public void c() {
        super.c();
        if (this.G()) {
            this.damageEntity(DamageSource.DROWN, 1);
        }

        int i = MathHelper.floor(this.locX);
        int j = MathHelper.floor(this.locZ);

        if (this.world.getBiome(i, j).j() > 1.0F) {
            this.damageEntity(DamageSource.BURN, 1);
        }

        for (i = 0; i < 4; ++i) {
            j = MathHelper.floor(this.locX + (double) ((float) (i % 2 * 2 - 1) * 0.25F));
            int k = MathHelper.floor(this.locY);
            int l = MathHelper.floor(this.locZ + (double) ((float) (i / 2 % 2 * 2 - 1) * 0.25F));

            if (this.world.getTypeId(j, k, l) == 0 && this.world.getBiome(j, l).j() < 0.8F && Block.SNOW.canPlace(this.world, j, k, l)) {
                this.world.setTypeId(j, k, l, Block.SNOW.id);
            }
        }
    }

    protected int getLootId() {
        return Item.SNOW_BALL.id;
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(16);

        for (int k = 0; k < j; ++k) {
            this.b(Item.SNOW_BALL.id, 1);
        }
    }

    public void d(EntityLiving entityliving) {
        EntitySnowball entitysnowball = new EntitySnowball(this.world, this);
        double d0 = entityliving.locX - this.locX;
        double d1 = entityliving.locY + (double) entityliving.getHeadHeight() - 1.100000023841858D - entitysnowball.locY;
        double d2 = entityliving.locZ - this.locZ;
        float f = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;

        entitysnowball.shoot(d0, d1 + (double) f, d2, 1.6F, 12.0F);
        this.world.makeSound(this, "random.bow", 1.0F, 1.0F / (this.aA().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(entitysnowball);
    }
}
