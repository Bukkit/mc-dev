package net.minecraft.server;

public class EntityCreeper extends EntityMonster {

    private int d;
    private int fuseTicks;
    private int maxFuseTicks = 30;
    private int explosionRadius = 3;

    public EntityCreeper(World world) {
        super(world);
        this.texture = "/mob/creeper.png";
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalSwell(this));
        this.goalSelector.a(3, new PathfinderGoalAvoidPlayer(this, EntityOcelot.class, 6.0F, 0.25F, 0.3F));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 0.25F, false));
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.2F));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 16.0F, 0, true));
        this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false));
    }

    public boolean bd() {
        return true;
    }

    public int as() {
        return this.aF() == null ? 3 : 3 + (this.health - 1);
    }

    protected void a(float f) {
        super.a(f);
        this.fuseTicks = (int) ((float) this.fuseTicks + f * 1.5F);
        if (this.fuseTicks > this.maxFuseTicks - 5) {
            this.fuseTicks = this.maxFuseTicks - 5;
        }
    }

    public int getMaxHealth() {
        return 20;
    }

    protected void a() {
        super.a();
        this.datawatcher.a(16, Byte.valueOf((byte) -1));
        this.datawatcher.a(17, Byte.valueOf((byte) 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.datawatcher.getByte(17) == 1) {
            nbttagcompound.setBoolean("powered", true);
        }

        nbttagcompound.setShort("Fuse", (short) this.maxFuseTicks);
        nbttagcompound.setByte("ExplosionRadius", (byte) this.explosionRadius);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.datawatcher.watch(17, Byte.valueOf((byte) (nbttagcompound.getBoolean("powered") ? 1 : 0)));
        if (nbttagcompound.hasKey("Fuse")) {
            this.maxFuseTicks = nbttagcompound.getShort("Fuse");
        }

        if (nbttagcompound.hasKey("ExplosionRadius")) {
            this.explosionRadius = nbttagcompound.getByte("ExplosionRadius");
        }
    }

    public void j_() {
        if (this.isAlive()) {
            this.d = this.fuseTicks;
            int i = this.o();

            if (i > 0 && this.fuseTicks == 0) {
                this.makeSound("random.fuse", 1.0F, 0.5F);
            }

            this.fuseTicks += i;
            if (this.fuseTicks < 0) {
                this.fuseTicks = 0;
            }

            if (this.fuseTicks >= this.maxFuseTicks) {
                this.fuseTicks = this.maxFuseTicks;
                if (!this.world.isStatic) {
                    boolean flag = this.world.getGameRules().getBoolean("mobGriefing");

                    if (this.isPowered()) {
                        this.world.explode(this, this.locX, this.locY, this.locZ, (float) (this.explosionRadius * 2), flag);
                    } else {
                        this.world.explode(this, this.locX, this.locY, this.locZ, (float) this.explosionRadius, flag);
                    }

                    this.die();
                }
            }
        }

        super.j_();
    }

    protected String aY() {
        return "mob.creeper.say";
    }

    protected String aZ() {
        return "mob.creeper.death";
    }

    public void die(DamageSource damagesource) {
        super.die(damagesource);
        if (damagesource.getEntity() instanceof EntitySkeleton) {
            int i = Item.RECORD_1.id + this.random.nextInt(Item.RECORD_12.id - Item.RECORD_1.id + 1);

            this.b(i, 1);
        }
    }

    public boolean m(Entity entity) {
        return true;
    }

    public boolean isPowered() {
        return this.datawatcher.getByte(17) == 1;
    }

    protected int getLootId() {
        return Item.SULPHUR.id;
    }

    public int o() {
        return this.datawatcher.getByte(16);
    }

    public void a(int i) {
        this.datawatcher.watch(16, Byte.valueOf((byte) i));
    }

    public void a(EntityLightning entitylightning) {
        super.a(entitylightning);
        this.datawatcher.watch(17, Byte.valueOf((byte) 1));
    }
}
