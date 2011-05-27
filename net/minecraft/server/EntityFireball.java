package net.minecraft.server;

import java.util.List;

public class EntityFireball extends Entity {

    private int e = -1;
    private int f = -1;
    private int ak = -1;
    private int al = 0;
    private boolean am = false;
    public int a = 0;
    private EntityLiving an;
    private int ao;
    private int ap = 0;
    public double b;
    public double c;
    public double d;

    public EntityFireball(World world) {
        super(world);
        this.a(1.0F, 1.0F);
    }

    protected void a() {}

    public EntityFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
        super(world);
        this.an = entityliving;
        this.a(1.0F, 1.0F);
        this.c(entityliving.p, entityliving.q, entityliving.r, entityliving.v, entityliving.w);
        this.a(this.p, this.q, this.r);
        this.H = 0.0F;
        this.s = this.t = this.u = 0.0D;
        d0 += this.W.nextGaussian() * 0.4D;
        d1 += this.W.nextGaussian() * 0.4D;
        d2 += this.W.nextGaussian() * 0.4D;
        double d3 = (double) MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        this.b = d0 / d3 * 0.1D;
        this.c = d1 / d3 * 0.1D;
        this.d = d2 / d3 * 0.1D;
    }

    public void b_() {
        super.b_();
        this.Z = 10;
        if (this.a > 0) {
            --this.a;
        }

        if (this.am) {
            int i = this.l.a(this.e, this.f, this.ak);

            if (i == this.al) {
                ++this.ao;
                if (this.ao == 1200) {
                    this.q();
                }

                return;
            }

            this.am = false;
            this.s *= (double) (this.W.nextFloat() * 0.2F);
            this.t *= (double) (this.W.nextFloat() * 0.2F);
            this.u *= (double) (this.W.nextFloat() * 0.2F);
            this.ao = 0;
            this.ap = 0;
        } else {
            ++this.ap;
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

        for (int j = 0; j < list.size(); ++j) {
            Entity entity1 = (Entity) list.get(j);

            if (entity1.c_() && (entity1 != this.an || this.ap >= 25)) {
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

        if (movingobjectposition != null) {
            if (movingobjectposition.g != null && movingobjectposition.g.a(this.an, 0)) {
                ;
            }

            this.l.a((Entity) null, this.p, this.q, this.r, 1.0F, true);
            this.q();
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
        float f2 = 0.95F;

        if (this.v()) {
            for (int k = 0; k < 4; ++k) {
                float f3 = 0.25F;

                this.l.a("bubble", this.p - this.s * (double) f3, this.q - this.t * (double) f3, this.r - this.u * (double) f3, this.s, this.t, this.u);
            }

            f2 = 0.8F;
        }

        this.s += this.b;
        this.t += this.c;
        this.u += this.d;
        this.s *= (double) f2;
        this.t *= (double) f2;
        this.u *= (double) f2;
        this.l.a("smoke", this.p, this.q + 0.5D, this.r, 0.0D, 0.0D, 0.0D);
        this.a(this.p, this.q, this.r);
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("xTile", (short) this.e);
        nbttagcompound.a("yTile", (short) this.f);
        nbttagcompound.a("zTile", (short) this.ak);
        nbttagcompound.a("inTile", (byte) this.al);
        nbttagcompound.a("shake", (byte) this.a);
        nbttagcompound.a("inGround", (byte) (this.am ? 1 : 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.e = nbttagcompound.c("xTile");
        this.f = nbttagcompound.c("yTile");
        this.ak = nbttagcompound.c("zTile");
        this.al = nbttagcompound.b("inTile") & 255;
        this.a = nbttagcompound.b("shake") & 255;
        this.am = nbttagcompound.b("inGround") == 1;
    }

    public boolean c_() {
        return true;
    }

    public boolean a(Entity entity, int i) {
        this.y();
        if (entity != null) {
            Vec3D vec3d = entity.G();

            if (vec3d != null) {
                this.s = vec3d.a;
                this.t = vec3d.b;
                this.u = vec3d.c;
                this.b = this.s * 0.1D;
                this.c = this.t * 0.1D;
                this.d = this.u * 0.1D;
            }

            return true;
        } else {
            return false;
        }
    }
}
