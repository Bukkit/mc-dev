package net.minecraft.server;

import java.util.List;

public class EntityEgg extends Entity {

    private int b = -1;
    private int c = -1;
    private int d = -1;
    private int e = 0;
    private boolean f = false;
    public int a = 0;
    private EntityLiving aj;
    private int ak;
    private int al = 0;

    public EntityEgg(World world) {
        super(world);
        this.a(0.25F, 0.25F);
    }

    public EntityEgg(World world, EntityLiving entityliving) {
        super(world);
        this.aj = entityliving;
        this.a(0.25F, 0.25F);
        this.c(entityliving.p, entityliving.q + (double) entityliving.s(), entityliving.r, entityliving.v, entityliving.w);
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
        this.ak = 0;
    }

    public void b_() {
        this.O = this.p;
        this.P = this.q;
        this.Q = this.r;
        super.b_();
        if (this.a > 0) {
            --this.a;
        }

        if (this.f) {
            int i = this.l.a(this.b, this.c, this.d);

            if (i == this.e) {
                ++this.ak;
                if (this.ak == 1200) {
                    this.l();
                }

                return;
            }

            this.f = false;
            this.s *= (double) (this.W.nextFloat() * 0.2F);
            this.t *= (double) (this.W.nextFloat() * 0.2F);
            this.u *= (double) (this.W.nextFloat() * 0.2F);
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

        if (!this.l.z) {
            Entity entity = null;
            List list = this.l.b((Entity) this, this.z.a(this.s, this.t, this.u).b(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;

            for (int j = 0; j < list.size(); ++j) {
                Entity entity1 = (Entity) list.get(j);

                if (entity1.c_() && (entity1 != this.aj || this.al >= 5)) {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.z.b((double) f, (double) f, (double) f);
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
        }

        if (movingobjectposition != null) {
            if (movingobjectposition.g != null && movingobjectposition.g.a(this.aj, 0)) {
                ;
            }

            if (!this.l.z && this.W.nextInt(8) == 0) {
                byte b0 = 1;

                if (this.W.nextInt(32) == 0) {
                    b0 = 4;
                }

                for (int k = 0; k < b0; ++k) {
                    EntityChicken entitychicken = new EntityChicken(this.l);

                    entitychicken.c(this.p, this.q, this.r, this.v, 0.0F);
                    this.l.a((Entity) entitychicken);
                }
            }

            for (int l = 0; l < 8; ++l) {
                this.l.a("snowballpoof", this.p, this.q, this.r, 0.0D, 0.0D, 0.0D);
            }

            this.l();
        }

        this.p += this.s;
        this.q += this.t;
        this.r += this.u;
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
        float f2 = 0.99F;
        float f3 = 0.03F;

        if (this.r()) {
            for (int i1 = 0; i1 < 4; ++i1) {
                float f4 = 0.25F;

                this.l.a("bubble", this.p - this.s * (double) f4, this.q - this.t * (double) f4, this.r - this.u * (double) f4, this.s, this.t, this.u);
            }

            f2 = 0.8F;
        }

        this.s *= (double) f2;
        this.t *= (double) f2;
        this.u *= (double) f2;
        this.t -= (double) f3;
        this.a(this.p, this.q, this.r);
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("xTile", (short) this.b);
        nbttagcompound.a("yTile", (short) this.c);
        nbttagcompound.a("zTile", (short) this.d);
        nbttagcompound.a("inTile", (byte) this.e);
        nbttagcompound.a("shake", (byte) this.a);
        nbttagcompound.a("inGround", (byte) (this.f ? 1 : 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.b = nbttagcompound.c("xTile");
        this.c = nbttagcompound.c("yTile");
        this.d = nbttagcompound.c("zTile");
        this.e = nbttagcompound.b("inTile") & 255;
        this.a = nbttagcompound.b("shake") & 255;
        this.f = nbttagcompound.b("inGround") == 1;
    }

    public void b(EntityHuman entityhuman) {
        if (this.f && this.aj == entityhuman && this.a <= 0 && entityhuman.an.a(new ItemStack(Item.ARROW.aW, 1))) {
            this.l.a(this, "random.pop", 0.2F, ((this.W.nextFloat() - this.W.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityhuman.c(this, 1);
            this.l();
        }
    }
}
