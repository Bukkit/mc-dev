package net.minecraft.server;

public class EntityCreeper extends EntityMonster {

    int fuseTicks;
    int b;

    public EntityCreeper(World world) {
        super(world);
        this.texture = "/mob/creeper.png";
    }

    protected void a() {
        super.a();
        this.datawatcher.a(16, Byte.valueOf((byte) -1));
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void f_() {
        this.b = this.fuseTicks;
        if (this.world.isStatic) {
            int i = this.v();

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

        super.f_();
    }

    protected String f() {
        return "mob.creeper";
    }

    protected String g() {
        return "mob.creeperdeath";
    }

    public void a(Entity entity) {
        super.a(entity);
        if (entity instanceof EntitySkeleton) {
            this.b(Item.GOLD_RECORD.id + this.random.nextInt(2), 1);
        }
    }

    protected void a(Entity entity, float f) {
        int i = this.v();

        if ((i > 0 || f >= 3.0F) && (i <= 0 || f >= 7.0F)) {
            this.e(-1);
            --this.fuseTicks;
            if (this.fuseTicks < 0) {
                this.fuseTicks = 0;
            }
        } else {
            if (this.fuseTicks == 0) {
                this.world.makeSound(this, "random.fuse", 1.0F, 0.5F);
            }

            this.e(1);
            ++this.fuseTicks;
            if (this.fuseTicks >= 30) {
                this.world.a(this, this.locX, this.locY, this.locZ, 3.0F);
                this.die();
            }

            this.e = true;
        }
    }

    protected int h() {
        return Item.SULPHUR.id;
    }

    private int v() {
        return this.datawatcher.a(16);
    }

    private void e(int i) {
        this.datawatcher.b(16, Byte.valueOf((byte) i));
    }
}
