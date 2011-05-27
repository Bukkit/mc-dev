package net.minecraft.server;

public class EntitySpider extends EntityMonster {

    public EntitySpider(World world) {
        super(world);
        this.aF = "/mob/spider.png";
        this.a(1.4F, 0.9F);
        this.bl = 0.8F;
    }

    public double j() {
        return (double) this.I * 0.75D - 0.5D;
    }

    protected Entity k() {
        float f = this.b(1.0F);

        if (f < 0.5F) {
            double d0 = 16.0D;

            return this.l.a(this, d0);
        } else {
            return null;
        }
    }

    protected String d() {
        return "mob.spider";
    }

    protected String e() {
        return "mob.spider";
    }

    protected String f() {
        return "mob.spiderdeath";
    }

    protected void a(Entity entity, float f) {
        float f1 = this.b(1.0F);

        if (f1 > 0.5F && this.V.nextInt(100) == 0) {
            this.f = null;
        } else {
            if (f > 2.0F && f < 6.0F && this.V.nextInt(10) == 0) {
                if (this.A) {
                    double d0 = entity.p - this.p;
                    double d1 = entity.r - this.r;
                    float f2 = MathHelper.a(d0 * d0 + d1 * d1);

                    this.s = d0 / (double) f2 * 0.5D * 0.800000011920929D + this.s * 0.20000000298023224D;
                    this.u = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.u * 0.20000000298023224D;
                    this.t = 0.4000000059604645D;
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
        return Item.STRING.aW;
    }
}
