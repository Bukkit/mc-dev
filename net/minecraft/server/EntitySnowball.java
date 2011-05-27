package net.minecraft.server;

import java.util.List;

public class EntitySnowball extends Entity {

    private int b = -1;
    private int c = -1;
    private int d = -1;
    private int e = 0;
    private boolean f = false;
    public int a = 0;
    private EntityLiving ai;
    private int aj;
    private int ak = 0;

    public EntitySnowball(World world) {
        super(world);
        this.a(0.25F, 0.25F);
    }

    public void b_() {
        super.b_();
        if (this.a > 0) {
            --this.a;
        }

        if (this.f) {
            int i = this.l.a(this.b, this.c, this.d);

            if (i == this.e) {
                ++this.aj;
                if (this.aj == 1200) {
                    this.l();
                }

                return;
            }

            this.f = false;
            this.s *= (double) (this.V.nextFloat() * 0.2F);
            this.t *= (double) (this.V.nextFloat() * 0.2F);
            this.u *= (double) (this.V.nextFloat() * 0.2F);
            this.aj = 0;
            this.ak = 0;
        } else {
            ++this.ak;
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

        int j;
        float f;

        for (j = 0; j < list.size(); ++j) {
            Entity entity1 = (Entity) list.get(j);

            if (entity1.c_() && (entity1 != this.ai || this.ak >= 5)) {
                f = 0.3F;
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
            if (movingobjectposition.g != null && movingobjectposition.g.a(this.ai, 0)) {
                ;
            }

            for (j = 0; j < 8; ++j) {
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

        f = 0.03F;
        if (this.r()) {
            for (int k = 0; k < 4; ++k) {
                float f3 = 0.25F;

                this.l.a("bubble", this.p - this.s * (double) f3, this.q - this.t * (double) f3, this.r - this.u * (double) f3, this.s, this.t, this.u);
            }

            f2 = 0.8F;
        }

        this.s *= (double) f2;
        this.t *= (double) f2;
        this.u *= (double) f2;
        this.t -= (double) f;
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

    public void a(EntityHuman entityhuman) {
        if (this.f && this.ai == entityhuman && this.a <= 0 && entityhuman.ak.a(new ItemStack(Item.ARROW.aW, 1))) {
            this.l.a(this, "random.pop", 0.2F, ((this.V.nextFloat() - this.V.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityhuman.c(this, 1);
            this.l();
        }
    }
}
