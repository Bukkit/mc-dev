package net.minecraft.server;

public class EntitySpider extends EntityMonster {

    public EntitySpider(World world) {
        super(world);
        this.aC = "/mob/spider.png";
        this.a(1.4F, 0.9F);
        this.bi = 0.8F;
    }

    public double h() {
        return (double) this.E * 0.75D - 0.5D;
    }

    protected Entity i() {
        float f = this.b(1.0F);

        if (f < 0.5F) {
            double d0 = 16.0D;

            return this.h.a(this, d0);
        } else {
            return null;
        }
    }

    protected String c() {
        return "mob.spider";
    }

    protected String d() {
        return "mob.spider";
    }

    protected String e() {
        return "mob.spiderdeath";
    }

    protected void a(Entity entity, float f) {
        float f1 = this.b(1.0F);

        if (f1 > 0.5F && this.R.nextInt(100) == 0) {
            this.ag = null;
        } else {
            if (f > 2.0F && f < 6.0F && this.R.nextInt(10) == 0) {
                if (this.w) {
                    double d0 = entity.l - this.l;
                    double d1 = entity.n - this.n;
                    float f2 = MathHelper.a(d0 * d0 + d1 * d1);

                    this.o = d0 / (double) f2 * 0.5D * 0.800000011920929D + this.o * 0.20000000298023224D;
                    this.q = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.q * 0.20000000298023224D;
                    this.p = 0.4000000059604645D;
                }
            } else {
                super.a(entity, f);
            }
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    protected int g() {
        return Item.STRING.aS;
    }
}
