package net.minecraft.server;

public class EntityCreeper extends EntityMonster {

    int a;
    int b;
    int ad = 30;
    int ae = -1;

    public EntityCreeper(World world) {
        super(world);
        this.aC = "/mob/creeper.png";
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    protected void d_() {
        this.b = this.a;
        if (this.a > 0 && this.ae < 0) {
            --this.a;
        }

        if (this.ae >= 0) {
            this.ae = 2;
        }

        super.d_();
        if (this.ae != 1) {
            this.ae = -1;
        }
    }

    protected String d() {
        return "mob.creeper";
    }

    protected String e() {
        return "mob.creeperdeath";
    }

    public void f(Entity entity) {
        super.f(entity);
        if (entity instanceof EntitySkeleton) {
            this.a(Item.GOLD_RECORD.aS + this.R.nextInt(2), 1);
        }
    }

    protected void a(Entity entity, float f) {
        if (this.ae <= 0 && f < 3.0F || this.ae > 0 && f < 7.0F) {
            if (this.a == 0) {
                this.h.a(this, "random.fuse", 1.0F, 0.5F);
            }

            this.ae = 1;
            ++this.a;
            if (this.a == this.ad) {
                this.h.a(this, this.l, this.m, this.n, 3.0F);
                this.j();
            }

            this.ah = true;
        }
    }

    protected int g() {
        return Item.SULPHUR.aS;
    }
}
