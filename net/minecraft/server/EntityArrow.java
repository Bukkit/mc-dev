package net.minecraft.server;

import java.util.List;

public class EntityArrow extends Entity {

    private int b = -1;
    private int ad = -1;
    private int ae = -1;
    private int af = 0;
    private boolean ag = false;
    public int a = 0;
    private EntityLiving ah;
    private int ai;
    private int aj = 0;

    public EntityArrow(World world) {
        super(world);
        this.a(0.5F, 0.5F);
    }

    public EntityArrow(World world, EntityLiving entityliving) {
        super(world);
        this.ah = entityliving;
        this.a(0.5F, 0.5F);
        this.c(entityliving.l, entityliving.m, entityliving.n, entityliving.r, entityliving.s);
        this.l -= (double) (MathHelper.b(this.r / 180.0F * 3.1415927F) * 0.16F);
        this.m -= 0.10000000149011612D;
        this.n -= (double) (MathHelper.a(this.r / 180.0F * 3.1415927F) * 0.16F);
        this.a(this.l, this.m, this.n);
        this.C = 0.0F;
        this.o = (double) (-MathHelper.a(this.r / 180.0F * 3.1415927F) * MathHelper.b(this.s / 180.0F * 3.1415927F));
        this.q = (double) (MathHelper.b(this.r / 180.0F * 3.1415927F) * MathHelper.b(this.s / 180.0F * 3.1415927F));
        this.p = (double) (-MathHelper.a(this.s / 180.0F * 3.1415927F));
        this.a(this.o, this.p, this.q, 1.5F, 1.0F);
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
        float f2 = MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        d0 /= (double) f2;
        d1 /= (double) f2;
        d2 /= (double) f2;
        d0 += this.R.nextGaussian() * 0.007499999832361937D * (double) f1;
        d1 += this.R.nextGaussian() * 0.007499999832361937D * (double) f1;
        d2 += this.R.nextGaussian() * 0.007499999832361937D * (double) f1;
        d0 *= (double) f;
        d1 *= (double) f;
        d2 *= (double) f;
        this.o = d0;
        this.p = d1;
        this.q = d2;
        float f3 = MathHelper.a(d0 * d0 + d2 * d2);

        this.t = this.r = (float) (Math.atan2(d0, d2) * 180.0D / 3.1415927410125732D);
        this.u = this.s = (float) (Math.atan2(d1, (double) f3) * 180.0D / 3.1415927410125732D);
        this.ai = 0;
    }

    public void b_() {
        super.b_();
        if (this.a > 0) {
            --this.a;
        }

        if (this.ag) {
            int i = this.h.a(this.b, this.ad, this.ae);

            if (i == this.af) {
                ++this.ai;
                if (this.ai == 1200) {
                    this.j();
                }

                return;
            }

            this.ag = false;
            this.o *= (double) (this.R.nextFloat() * 0.2F);
            this.p *= (double) (this.R.nextFloat() * 0.2F);
            this.q *= (double) (this.R.nextFloat() * 0.2F);
            this.ai = 0;
            this.aj = 0;
        } else {
            ++this.aj;
        }

        Vec3D vec3d = Vec3D.b(this.l, this.m, this.n);
        Vec3D vec3d1 = Vec3D.b(this.l + this.o, this.m + this.p, this.n + this.q);
        MovingObjectPosition movingobjectposition = this.h.a(vec3d, vec3d1);

        vec3d = Vec3D.b(this.l, this.m, this.n);
        vec3d1 = Vec3D.b(this.l + this.o, this.m + this.p, this.n + this.q);
        if (movingobjectposition != null) {
            vec3d1 = Vec3D.b(movingobjectposition.f.a, movingobjectposition.f.b, movingobjectposition.f.c);
        }

        Entity entity = null;
        List list = this.h.b((Entity) this, this.v.a(this.o, this.p, this.q).b(1.0D, 1.0D, 1.0D));
        double d0 = 0.0D;

        float f;

        for (int j = 0; j < list.size(); ++j) {
            Entity entity1 = (Entity) list.get(j);

            if (entity1.c_() && (entity1 != this.ah || this.aj >= 5)) {
                f = 0.3F;
                AxisAlignedBB axisalignedbb = entity1.v.b((double) f, (double) f, (double) f);
                MovingObjectPosition movingobjectposition1 = axisalignedbb.a(vec3d, vec3d1);

                if (movingobjectposition1 != null) {
                    double d1 = vec3d.a(movingobjectposition1.f);

                    if (d1 < d0 || d0 == 0.0D) {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        if (entity != null) {
            movingobjectposition = new MovingObjectPosition(entity);
        }

        float f1;

        if (movingobjectposition != null) {
            if (movingobjectposition.g != null) {
                if (movingobjectposition.g.a(this.ah, 4)) {
                    this.h.a(this, "random.drr", 1.0F, 1.2F / (this.R.nextFloat() * 0.2F + 0.9F));
                    this.j();
                } else {
                    this.o *= -0.10000000149011612D;
                    this.p *= -0.10000000149011612D;
                    this.q *= -0.10000000149011612D;
                    this.r += 180.0F;
                    this.t += 180.0F;
                    this.aj = 0;
                }
            } else {
                this.b = movingobjectposition.b;
                this.ad = movingobjectposition.c;
                this.ae = movingobjectposition.d;
                this.af = this.h.a(this.b, this.ad, this.ae);
                this.o = (double) ((float) (movingobjectposition.f.a - this.l));
                this.p = (double) ((float) (movingobjectposition.f.b - this.m));
                this.q = (double) ((float) (movingobjectposition.f.c - this.n));
                f1 = MathHelper.a(this.o * this.o + this.p * this.p + this.q * this.q);
                this.l -= this.o / (double) f1 * 0.05000000074505806D;
                this.m -= this.p / (double) f1 * 0.05000000074505806D;
                this.n -= this.q / (double) f1 * 0.05000000074505806D;
                this.h.a(this, "random.drr", 1.0F, 1.2F / (this.R.nextFloat() * 0.2F + 0.9F));
                this.ag = true;
                this.a = 7;
            }
        }

        this.l += this.o;
        this.m += this.p;
        this.n += this.q;
        f1 = MathHelper.a(this.o * this.o + this.q * this.q);
        this.r = (float) (Math.atan2(this.o, this.q) * 180.0D / 3.1415927410125732D);

        for (this.s = (float) (Math.atan2(this.p, (double) f1) * 180.0D / 3.1415927410125732D); this.s - this.u < -180.0F; this.u -= 360.0F) {
            ;
        }

        while (this.s - this.u >= 180.0F) {
            this.u += 360.0F;
        }

        while (this.r - this.t < -180.0F) {
            this.t -= 360.0F;
        }

        while (this.r - this.t >= 180.0F) {
            this.t += 360.0F;
        }

        this.s = this.u + (this.s - this.u) * 0.2F;
        this.r = this.t + (this.r - this.t) * 0.2F;
        float f2 = 0.99F;

        f = 0.03F;
        if (this.o()) {
            for (int k = 0; k < 4; ++k) {
                float f3 = 0.25F;

                this.h.a("bubble", this.l - this.o * (double) f3, this.m - this.p * (double) f3, this.n - this.q * (double) f3, this.o, this.p, this.q);
            }

            f2 = 0.8F;
        }

        this.o *= (double) f2;
        this.p *= (double) f2;
        this.q *= (double) f2;
        this.p -= (double) f;
        this.a(this.l, this.m, this.n);
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("xTile", (short) this.b);
        nbttagcompound.a("yTile", (short) this.ad);
        nbttagcompound.a("zTile", (short) this.ae);
        nbttagcompound.a("inTile", (byte) this.af);
        nbttagcompound.a("shake", (byte) this.a);
        nbttagcompound.a("inGround", (byte) (this.ag ? 1 : 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.b = nbttagcompound.c("xTile");
        this.ad = nbttagcompound.c("yTile");
        this.ae = nbttagcompound.c("zTile");
        this.af = nbttagcompound.b("inTile") & 255;
        this.a = nbttagcompound.b("shake") & 255;
        this.ag = nbttagcompound.b("inGround") == 1;
    }

    public void a(EntityHuman entityhuman) {
        if (this.ag && this.ah == entityhuman && this.a <= 0 && entityhuman.aj.a(new ItemStack(Item.ARROW.aS, 1))) {
            this.h.a(this, "random.pop", 0.2F, ((this.R.nextFloat() - this.R.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityhuman.c(this, 1);
            this.j();
        }
    }
}
