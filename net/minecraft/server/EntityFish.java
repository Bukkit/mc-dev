package net.minecraft.server;

import java.util.List;

public class EntityFish extends Entity {

    private int d = -1;
    private int e = -1;
    private int f = -1;
    private int ai = 0;
    private boolean aj = false;
    public int a = 0;
    public EntityHuman b;
    private int ak;
    private int al = 0;
    private int am = 0;
    public Entity c = null;

    public EntityFish(World world) {
        super(world);
        this.a(0.25F, 0.25F);
    }

    public void b_() {
        super.b_();
        ItemStack itemstack = this.b.G();

        if (!this.b.F && this.b.w() && itemstack != null && itemstack.a() == Item.FISHING_ROD && this.b(this.b) <= 1024.0D) {
            if (this.c != null) {
                if (!this.c.F) {
                    this.p = this.c.p;
                    this.q = this.c.z.b + (double) this.c.I * 0.8D;
                    this.r = this.c.r;
                    return;
                }

                this.c = null;
            }

            if (this.a > 0) {
                --this.a;
            }

            if (this.aj) {
                int i = this.l.a(this.d, this.e, this.f);

                if (i == this.ai) {
                    ++this.ak;
                    if (this.ak == 1200) {
                        this.l();
                    }

                    return;
                }

                this.aj = false;
                this.s *= (double) (this.V.nextFloat() * 0.2F);
                this.t *= (double) (this.V.nextFloat() * 0.2F);
                this.u *= (double) (this.V.nextFloat() * 0.2F);
                this.ak = 0;
                this.al = 0;
            } else {
                ++this.al;
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
            double d0 = 0.0D;

            double d1;

            for (int j = 0; j < list.size(); ++j) {
                Entity entity1 = (Entity) list.get(j);

                if (entity1.c_() && (entity1 != this.b || this.al >= 5)) {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.z.b((double) f, (double) f, (double) f);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb.a(vec3d, vec3d1);

                    if (movingobjectposition1 != null) {
                        d1 = vec3d.a(movingobjectposition1.f);
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

            if (movingobjectposition != null) {
                if (movingobjectposition.g != null) {
                    if (movingobjectposition.g.a(this.b, 0)) {
                        this.c = movingobjectposition.g;
                    }
                } else {
                    this.aj = true;
                }
            }

            if (!this.aj) {
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
                double d2 = 0.0D;

                for (int k = 0; k < b0; ++k) {
                    double d3 = this.z.b + (this.z.e - this.z.b) * (double) (k + 0) / (double) b0 - 0.125D + 0.125D;
                    double d4 = this.z.b + (this.z.e - this.z.b) * (double) (k + 1) / (double) b0 - 0.125D + 0.125D;
                    AxisAlignedBB axisalignedbb1 = AxisAlignedBB.b(this.z.a, d3, this.z.c, this.z.d, d4, this.z.f);

                    if (this.l.b(axisalignedbb1, Material.f)) {
                        d2 += 1.0D / (double) b0;
                    }
                }

                if (d2 > 0.0D) {
                    if (this.am > 0) {
                        --this.am;
                    } else if (this.V.nextInt(500) == 0) {
                        this.am = this.V.nextInt(30) + 10;
                        this.t -= 0.20000000298023224D;
                        this.l.a(this, "random.splash", 0.25F, 1.0F + (this.V.nextFloat() - this.V.nextFloat()) * 0.4F);
                        float f3 = (float) MathHelper.b(this.z.b);

                        float f4;
                        int l;
                        float f5;

                        for (l = 0; (float) l < 1.0F + this.H * 20.0F; ++l) {
                            f4 = (this.V.nextFloat() * 2.0F - 1.0F) * this.H;
                            f5 = (this.V.nextFloat() * 2.0F - 1.0F) * this.H;
                            this.l.a("bubble", this.p + (double) f4, (double) (f3 + 1.0F), this.r + (double) f5, this.s, this.t - (double) (this.V.nextFloat() * 0.2F), this.u);
                        }

                        for (l = 0; (float) l < 1.0F + this.H * 20.0F; ++l) {
                            f4 = (this.V.nextFloat() * 2.0F - 1.0F) * this.H;
                            f5 = (this.V.nextFloat() * 2.0F - 1.0F) * this.H;
                            this.l.a("splash", this.p + (double) f4, (double) (f3 + 1.0F), this.r + (double) f5, this.s, this.t, this.u);
                        }
                    }
                }

                if (this.am > 0) {
                    this.t -= (double) (this.V.nextFloat() * this.V.nextFloat() * this.V.nextFloat()) * 0.2D;
                }

                d1 = d2 * 2.0D - 1.0D;
                this.t += 0.03999999910593033D * d1;
                if (d2 > 0.0D) {
                    f2 = (float) ((double) f2 * 0.9D);
                    this.t *= 0.8D;
                }

                this.s *= (double) f2;
                this.t *= (double) f2;
                this.u *= (double) f2;
                this.a(this.p, this.q, this.r);
            }
        } else {
            this.l();
            this.b.at = null;
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("xTile", (short) this.d);
        nbttagcompound.a("yTile", (short) this.e);
        nbttagcompound.a("zTile", (short) this.f);
        nbttagcompound.a("inTile", (byte) this.ai);
        nbttagcompound.a("shake", (byte) this.a);
        nbttagcompound.a("inGround", (byte) (this.aj ? 1 : 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.d = nbttagcompound.c("xTile");
        this.e = nbttagcompound.c("yTile");
        this.f = nbttagcompound.c("zTile");
        this.ai = nbttagcompound.b("inTile") & 255;
        this.a = nbttagcompound.b("shake") & 255;
        this.aj = nbttagcompound.b("inGround") == 1;
    }
}
