package net.minecraft.server;

import java.util.List;

public class EntitySnowball extends Entity {

    private int b = -1;
    private int ad = -1;
    private int ae = -1;
    private int af = 0;
    private boolean ag = false;
    public int a = 0;
    private EntityLiving ah;
    private int ai;
    private int aj = 0;

    public EntitySnowball(World world) {
        super(world);
        this.a(0.25F, 0.25F);
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

        int j;
        float f;

        for (j = 0; j < list.size(); ++j) {
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

        if (movingobjectposition != null) {
            if (movingobjectposition.g != null && movingobjectposition.g.a(this.ah, 0)) {
                ;
            }

            for (j = 0; j < 8; ++j) {
                this.h.a("snowballpoof", this.l, this.m, this.n, 0.0D, 0.0D, 0.0D);
            }

            this.j();
        }

        this.l += this.o;
        this.m += this.p;
        this.n += this.q;
        float f1 = MathHelper.a(this.o * this.o + this.q * this.q);

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
