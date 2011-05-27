package net.minecraft.server;

import java.util.List;

public class EntityFish extends Entity {

    private int d = -1;
    private int e = -1;
    private int f = -1;
    private int aj = 0;
    private boolean ak = false;
    public int a = 0;
    public EntityHuman b;
    private int al;
    private int am = 0;
    private int an = 0;
    public Entity c = null;
    private int ao;
    private double ap;
    private double aq;
    private double ar;
    private double as;
    private double at;

    public EntityFish(World world) {
        super(world);
        this.a(0.25F, 0.25F);
    }

    public EntityFish(World world, EntityHuman entityhuman) {
        super(world);
        this.b = entityhuman;
        this.b.aE = this;
        this.a(0.25F, 0.25F);
        this.c(entityhuman.p, entityhuman.q + 1.62D - (double) entityhuman.H, entityhuman.r, entityhuman.v, entityhuman.w);
        this.p -= (double) (MathHelper.b(this.v / 180.0F * 3.1415927F) * 0.16F);
        this.q -= 0.10000000149011612D;
        this.r -= (double) (MathHelper.a(this.v / 180.0F * 3.1415927F) * 0.16F);
        this.a(this.p, this.q, this.r);
        this.H = 0.0F;
        float f = 0.4F;

        this.s = (double) (-MathHelper.a(this.v / 180.0F * 3.1415927F) * MathHelper.b(this.w / 180.0F * 3.1415927F) * f);
        this.u = (double) (MathHelper.b(this.v / 180.0F * 3.1415927F) * MathHelper.b(this.w / 180.0F * 3.1415927F) * f);
        this.t = (double) (-MathHelper.a(this.w / 180.0F * 3.1415927F) * f);
        this.a(this.s, this.t, this.u, 1.5F, 1.0F);
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
        float f2 = MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        d0 /= (double) f2;
        d1 /= (double) f2;
        d2 /= (double) f2;
        d0 += this.W.nextGaussian() * 0.007499999832361937D * (double) f1;
        d1 += this.W.nextGaussian() * 0.007499999832361937D * (double) f1;
        d2 += this.W.nextGaussian() * 0.007499999832361937D * (double) f1;
        d0 *= (double) f;
        d1 *= (double) f;
        d2 *= (double) f;
        this.s = d0;
        this.t = d1;
        this.u = d2;
        float f3 = MathHelper.a(d0 * d0 + d2 * d2);

        this.x = this.v = (float) (Math.atan2(d0, d2) * 180.0D / 3.1415927410125732D);
        this.y = this.w = (float) (Math.atan2(d1, (double) f3) * 180.0D / 3.1415927410125732D);
        this.al = 0;
    }

    public void b_() {
        super.b_();
        if (this.ao > 0) {
            double d0 = this.p + (this.ap - this.p) / (double) this.ao;
            double d1 = this.q + (this.aq - this.q) / (double) this.ao;
            double d2 = this.r + (this.ar - this.r) / (double) this.ao;

            double d3;

            for (d3 = this.as - (double) this.v; d3 < -180.0D; d3 += 360.0D) {
                ;
            }

            while (d3 >= 180.0D) {
                d3 -= 360.0D;
            }

            this.v = (float) ((double) this.v + d3 / (double) this.ao);
            this.w = (float) ((double) this.w + (this.at - (double) this.w) / (double) this.ao);
            --this.ao;
            this.a(d0, d1, d2);
            this.b(this.v, this.w);
        } else {
            if (!this.l.z) {
                ItemStack itemstack = this.b.M();

                if (this.b.G || !this.b.x() || itemstack == null || itemstack.a() != Item.FISHING_ROD || this.b(this.b) > 1024.0D) {
                    this.l();
                    this.b.aE = null;
                    return;
                }

                if (this.c != null) {
                    if (!this.c.G) {
                        this.p = this.c.p;
                        this.q = this.c.z.b + (double) this.c.J * 0.8D;
                        this.r = this.c.r;
                        return;
                    }

                    this.c = null;
                }
            }

            if (this.a > 0) {
                --this.a;
            }

            if (this.ak) {
                int i = this.l.a(this.d, this.e, this.f);

                if (i == this.aj) {
                    ++this.al;
                    if (this.al == 1200) {
                        this.l();
                    }

                    return;
                }

                this.ak = false;
                this.s *= (double) (this.W.nextFloat() * 0.2F);
                this.t *= (double) (this.W.nextFloat() * 0.2F);
                this.u *= (double) (this.W.nextFloat() * 0.2F);
                this.al = 0;
                this.am = 0;
            } else {
                ++this.am;
            }

            Vec3D vec3d = Vec3D.b(this.p, this.q, this.r);
            Vec3D vec3d1 = Vec3D.b(this.p + this.s, this.q + this.t, this.r + this.u);
            MovingObjectPosition movingobjectposition = this.l.a(vec3d, vec3d1);

            vec3d = Vec3D.b(this.p, this.q, this.r);
            vec3d1 = Vec3D.b(this.p + this.s, this.q + this.t, this.r + this.u);
            if (movingobjectposition != null) {
                vec3d1 = Vec3D.b(movingobjectposition.f.a, movingobjectposition.f.b, movingobjectposition.f.c);
            }

            Entity entity = null;
            List list = this.l.b((Entity) this, this.z.a(this.s, this.t, this.u).b(1.0D, 1.0D, 1.0D));
            double d4 = 0.0D;

            double d5;

            for (int j = 0; j < list.size(); ++j) {
                Entity entity1 = (Entity) list.get(j);

                if (entity1.c_() && (entity1 != this.b || this.am >= 5)) {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.z.b((double) f, (double) f, (double) f);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb.a(vec3d, vec3d1);

                    if (movingobjectposition1 != null) {
                        d5 = vec3d.a(movingobjectposition1.f);
                        if (d5 < d4 || d4 == 0.0D) {
                            entity = entity1;
                            d4 = d5;
                        }
                    }
                }
            }

            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }

            if (movingobjectposition != null) {
                if (movingobjectposition.g != null) {
                    if (movingobjectposition.g.a(this.b, 0)) {
                        this.c = movingobjectposition.g;
                    }
                } else {
                    this.ak = true;
                }
            }

            if (!this.ak) {
                this.c(this.s, this.t, this.u);
                float f1 = MathHelper.a(this.s * this.s + this.u * this.u);

                this.v = (float) (Math.atan2(this.s, this.u) * 180.0D / 3.1415927410125732D);

                for (this.w = (float) (Math.atan2(this.t, (double) f1) * 180.0D / 3.1415927410125732D); this.w - this.y < -180.0F; this.y -= 360.0F) {
                    ;
                }

                while (this.w - this.y >= 180.0F) {
                    this.y += 360.0F;
                }

                while (this.v - this.x < -180.0F) {
                    this.x -= 360.0F;
                }

                while (this.v - this.x >= 180.0F) {
                    this.x += 360.0F;
                }

                this.w = this.y + (this.w - this.y) * 0.2F;
                this.v = this.x + (this.v - this.x) * 0.2F;
                float f2 = 0.92F;

                if (this.A || this.B) {
                    f2 = 0.5F;
                }

                byte b0 = 5;
                double d6 = 0.0D;

                for (int k = 0; k < b0; ++k) {
                    double d7 = this.z.b + (this.z.e - this.z.b) * (double) (k + 0) / (double) b0 - 0.125D + 0.125D;
                    double d8 = this.z.b + (this.z.e - this.z.b) * (double) (k + 1) / (double) b0 - 0.125D + 0.125D;
                    AxisAlignedBB axisalignedbb1 = AxisAlignedBB.b(this.z.a, d7, this.z.c, this.z.d, d8, this.z.f);

                    if (this.l.b(axisalignedbb1, Material.f)) {
                        d6 += 1.0D / (double) b0;
                    }
                }

                if (d6 > 0.0D) {
                    if (this.an > 0) {
                        --this.an;
                    } else if (this.W.nextInt(500) == 0) {
                        this.an = this.W.nextInt(30) + 10;
                        this.t -= 0.20000000298023224D;
                        this.l.a(this, "random.splash", 0.25F, 1.0F + (this.W.nextFloat() - this.W.nextFloat()) * 0.4F);
                        float f3 = (float) MathHelper.b(this.z.b);

                        int l;
                        float f4;
                        float f5;

                        for (l = 0; (float) l < 1.0F + this.I * 20.0F; ++l) {
                            f4 = (this.W.nextFloat() * 2.0F - 1.0F) * this.I;
                            f5 = (this.W.nextFloat() * 2.0F - 1.0F) * this.I;
                            this.l.a("bubble", this.p + (double) f4, (double) (f3 + 1.0F), this.r + (double) f5, this.s, this.t - (double) (this.W.nextFloat() * 0.2F), this.u);
                        }

                        for (l = 0; (float) l < 1.0F + this.I * 20.0F; ++l) {
                            f4 = (this.W.nextFloat() * 2.0F - 1.0F) * this.I;
                            f5 = (this.W.nextFloat() * 2.0F - 1.0F) * this.I;
                            this.l.a("splash", this.p + (double) f4, (double) (f3 + 1.0F), this.r + (double) f5, this.s, this.t, this.u);
                        }
                    }
                }

                if (this.an > 0) {
                    this.t -= (double) (this.W.nextFloat() * this.W.nextFloat() * this.W.nextFloat()) * 0.2D;
                }

                d5 = d6 * 2.0D - 1.0D;
                this.t += 0.03999999910593033D * d5;
                if (d6 > 0.0D) {
                    f2 = (float) ((double) f2 * 0.9D);
                    this.t *= 0.8D;
                }

                this.s *= (double) f2;
                this.t *= (double) f2;
                this.u *= (double) f2;
                this.a(this.p, this.q, this.r);
            }
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("xTile", (short) this.d);
        nbttagcompound.a("yTile", (short) this.e);
        nbttagcompound.a("zTile", (short) this.f);
        nbttagcompound.a("inTile", (byte) this.aj);
        nbttagcompound.a("shake", (byte) this.a);
        nbttagcompound.a("inGround", (byte) (this.ak ? 1 : 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.d = nbttagcompound.c("xTile");
        this.e = nbttagcompound.c("yTile");
        this.f = nbttagcompound.c("zTile");
        this.aj = nbttagcompound.b("inTile") & 255;
        this.a = nbttagcompound.b("shake") & 255;
        this.ak = nbttagcompound.b("inGround") == 1;
    }

    public int c() {
        byte b0 = 0;

        if (this.c != null) {
            double d0 = this.b.p - this.p;
            double d1 = this.b.q - this.q;
            double d2 = this.b.r - this.r;
            double d3 = (double) MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);
            double d4 = 0.1D;

            this.c.s += d0 * d4;
            this.c.t += d1 * d4 + (double) MathHelper.a(d3) * 0.08D;
            this.c.u += d2 * d4;
            b0 = 3;
        } else if (this.an > 0) {
            EntityItem entityitem = new EntityItem(this.l, this.p, this.q, this.r, new ItemStack(Item.RAW_FISH.aW));
            double d5 = this.b.p - this.p;
            double d6 = this.b.q - this.q;
            double d7 = this.b.r - this.r;
            double d8 = (double) MathHelper.a(d5 * d5 + d6 * d6 + d7 * d7);
            double d9 = 0.1D;

            entityitem.s = d5 * d9;
            entityitem.t = d6 * d9 + (double) MathHelper.a(d8) * 0.08D;
            entityitem.u = d7 * d9;
            this.l.a((Entity) entityitem);
            b0 = 1;
        }

        if (this.ak) {
            b0 = 2;
        }

        this.l();
        this.b.aE = null;
        return b0;
    }
}
