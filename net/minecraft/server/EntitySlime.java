package net.minecraft.server;

public class EntitySlime extends EntityLiving implements IMonster {

    public float a;
    public float b;
    private int ae = 0;
    public int ad = 1;

    public EntitySlime(World world) {
        super(world);
        this.aC = "/mob/slime.png";
        this.ad = 1 << this.R.nextInt(3);
        this.C = 0.0F;
        this.ae = this.R.nextInt(20) + 10;
        this.c(this.ad);
    }

    public void c(int i) {
        this.ad = i;
        this.a(0.6F * (float) i, 0.6F * (float) i);
        this.aM = i * i;
        this.a(this.l, this.m, this.n);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.a("Size", this.ad - 1);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.ad = nbttagcompound.d("Size") + 1;
    }

    public void b_() {
        this.b = this.a;
        boolean flag = this.w;

        super.b_();
        if (this.w && !flag) {
            for (int i = 0; i < this.ad * 8; ++i) {
                float f = this.R.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.R.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.a(f) * (float) this.ad * 0.5F * f1;
                float f3 = MathHelper.b(f) * (float) this.ad * 0.5F * f1;

                this.h.a("slime", this.l + (double) f2, this.v.b, this.n + (double) f3, 0.0D, 0.0D, 0.0D);
            }

            if (this.ad > 2) {
                this.h.a(this, "mob.slime", this.f(), ((this.R.nextFloat() - this.R.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.a = -0.5F;
        }

        this.a *= 0.6F;
    }

    protected void d_() {
        EntityHuman entityhuman = this.h.a(this, 16.0D);

        if (entityhuman != null) {
            this.b(entityhuman, 10.0F);
        }

        if (this.w && this.ae-- <= 0) {
            this.ae = this.R.nextInt(20) + 10;
            if (entityhuman != null) {
                this.ae /= 3;
            }

            this.bg = true;
            if (this.ad > 1) {
                this.h.a(this, "mob.slime", this.f(), ((this.R.nextFloat() - this.R.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.a = 1.0F;
            this.bd = 1.0F - this.R.nextFloat() * 2.0F;
            this.be = (float) (1 * this.ad);
        } else {
            this.bg = false;
            if (this.w) {
                this.bd = this.be = 0.0F;
            }
        }
    }

    public void j() {
        if (this.ad > 1 && this.aM == 0) {
            for (int i = 0; i < 4; ++i) {
                float f = ((float) (i % 2) - 0.5F) * (float) this.ad / 4.0F;
                float f1 = ((float) (i / 2) - 0.5F) * (float) this.ad / 4.0F;
                EntitySlime entityslime = new EntitySlime(this.h);

                entityslime.c(this.ad / 2);
                entityslime.c(this.l + (double) f, this.m + 0.5D, this.n + (double) f1, this.R.nextFloat() * 360.0F, 0.0F);
                this.h.a((Entity) entityslime);
            }
        }

        super.j();
    }

    public void a(EntityHuman entityhuman) {
        if (this.ad > 1 && this.g(entityhuman) && (double) this.a((Entity) entityhuman) < 0.6D * (double) this.ad && entityhuman.a(this, this.ad)) {
            this.h.a(this, "mob.slimeattack", 1.0F, (this.R.nextFloat() - this.R.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected String d() {
        return "mob.slime";
    }

    protected String e() {
        return "mob.slime";
    }

    protected int g() {
        return this.ad == 1 ? Item.SLIME_BALL.aS : 0;
    }

    public boolean a() {
        Chunk chunk = this.h.a(MathHelper.b(this.l), MathHelper.b(this.m));

        return (this.ad == 1 || this.h.l > 0) && this.R.nextInt(10) == 0 && chunk.a(987234911L).nextInt(10) == 0 && this.m < 16.0D;
    }

    protected float f() {
        return 0.6F;
    }
}
