package net.minecraft.server;

public class EntityZombie extends EntityMonster {

    public EntityZombie(World world) {
        super(world);
        this.texture = "/mob/zombie.png";
        this.bw = 0.23F;
        this.damage = 4;
        this.getNavigation().b(true);
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalBreakDoor(this));
        this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, this.bw, false));
        this.goalSelector.a(3, new PathfinderGoalMeleeAttack(this, EntityVillager.class, this.bw, true));
        this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, this.bw));
        this.goalSelector.a(5, new PathfinderGoalMoveThroughVillage(this, this.bw, false));
        this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, this.bw));
        this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 16.0F, 0, true));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, 16.0F, 0, false));
    }

    public int getMaxHealth() {
        return 20;
    }

    public int aO() {
        return 2;
    }

    protected boolean aV() {
        return true;
    }

    public void d() {
        if (this.world.r() && !this.world.isStatic) {
            float f = this.c(1.0F);

            if (f > 0.5F && this.world.j(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.setOnFire(8);
            }
        }

        super.d();
    }

    protected String aQ() {
        return "mob.zombie";
    }

    protected String aR() {
        return "mob.zombiehurt";
    }

    protected String aS() {
        return "mob.zombiedeath";
    }

    protected int getLootId() {
        return Item.ROTTEN_FLESH.id;
    }

    public EnumMonsterType getMonsterType() {
        return EnumMonsterType.UNDEAD;
    }

    protected void l(int i) {
        switch (this.random.nextInt(4)) {
        case 0:
            this.b(Item.IRON_SWORD.id, 1);
            break;

        case 1:
            this.b(Item.IRON_HELMET.id, 1);
            break;

        case 2:
            this.b(Item.IRON_INGOT.id, 1);
            break;

        case 3:
            this.b(Item.IRON_SPADE.id, 1);
        }
    }
}
