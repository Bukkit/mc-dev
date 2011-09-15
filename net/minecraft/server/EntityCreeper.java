package net.minecraft.server;

public class EntityCreeper extends EntityMonster {

    int fuseTicks;
    int b;

    public EntityCreeper(World world) {
        super(world);
        this.texture = "/mob/creeper.png";
    }

    protected void b() {
        super.b();
        this.datawatcher.a(16, Byte.valueOf((byte) -1));
        this.datawatcher.a(17, Byte.valueOf((byte) 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.datawatcher.a(17) == 1) {
            nbttagcompound.a("powered", true);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.datawatcher.watch(17, Byte.valueOf((byte) (nbttagcompound.m("powered") ? 1 : 0)));
    }

    protected void b(Entity entity, float f) {
        if (!this.world.isStatic) {
            if (this.fuseTicks > 0) {
                this.b(-1);
                --this.fuseTicks;
                if (this.fuseTicks < 0) {
                    this.fuseTicks = 0;
                }
            }
        }
    }

    public void s_() {
        this.b = this.fuseTicks;
        if (this.world.isStatic) {
            int i = this.w();

            if (i > 0 && this.fuseTicks == 0) {
                this.world.makeSound(this, "random.fuse", 1.0F, 0.5F);
            }

            this.fuseTicks += i;
            if (this.fuseTicks < 0) {
                this.fuseTicks = 0;
            }

            if (this.fuseTicks >= 30) {
                this.fuseTicks = 30;
            }
        }

        super.s_();
        if (this.target == null && this.fuseTicks > 0) {
            this.b(-1);
            --this.fuseTicks;
            if (this.fuseTicks < 0) {
                this.fuseTicks = 0;
            }
        }
    }

    protected String i() {
        return "mob.creeper";
    }

    protected String j() {
        return "mob.creeperdeath";
    }

    public void die(DamageSource damagesource) {
        super.die(damagesource);
        if (damagesource.getEntity() instanceof EntitySkeleton) {
            this.b(Item.GOLD_RECORD.id + this.random.nextInt(2), 1);
        }
    }

    protected void a(Entity entity, float f) {
        if (!this.world.isStatic) {
            int i = this.w();

            if ((i > 0 || f >= 3.0F) && (i <= 0 || f >= 7.0F)) {
                this.b(-1);
                --this.fuseTicks;
                if (this.fuseTicks < 0) {
                    this.fuseTicks = 0;
                }
            } else {
                if (this.fuseTicks == 0) {
                    this.world.makeSound(this, "random.fuse", 1.0F, 0.5F);
                }

                this.b(1);
                ++this.fuseTicks;
                if (this.fuseTicks >= 30) {
                    if (this.isPowered()) {
                        this.world.a(this, this.locX, this.locY, this.locZ, 6.0F);
                    } else {
                        this.world.a(this, this.locX, this.locY, this.locZ, 3.0F);
                    }

                    this.die();
                }

                this.e = true;
            }
        }
    }

    public boolean isPowered() {
        return this.datawatcher.a(17) == 1;
    }

    protected int k() {
        return Item.SULPHUR.id;
    }

    private int w() {
        return this.datawatcher.a(16);
    }

    private void b(int i) {
        this.datawatcher.watch(16, Byte.valueOf((byte) i));
    }

    public void a(EntityWeatherStorm entityweatherstorm) {
        super.a(entityweatherstorm);
        this.datawatcher.watch(17, Byte.valueOf((byte) 1));
    }
}
