package net.minecraft.server;

public class EntitySquid extends EntityWaterAnimal {

    public float a = 0.0F;
    public float b = 0.0F;
    public float c = 0.0F;
    public float f = 0.0F;
    public float ak = 0.0F;
    public float al = 0.0F;
    public float am = 0.0F;
    public float an = 0.0F;
    private float ao = 0.0F;
    private float ap = 0.0F;
    private float aq = 0.0F;
    private float ar = 0.0F;
    private float as = 0.0F;
    private float at = 0.0F;

    public EntitySquid(World world) {
        super(world);
        this.aP = "/mob/squid.png";
        this.a(0.95F, 0.95F);
        this.ap = 1.0F / (this.W.nextFloat() + 1.0F) * 0.2F;
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    protected String e() {
        return null;
    }

    protected String f() {
        return null;
    }

    protected String g() {
        return null;
    }

    protected float i() {
        return 0.4F;
    }

    protected int h() {
        return 0;
    }

    protected void g_() {
        int i = this.W.nextInt(3) + 1;

        for (int j = 0; j < i; ++j) {
            this.a(new ItemStack(Item.INK_SACK, 1, 0), 0.0F);
        }
    }

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.an.e();

        if (itemstack != null && itemstack.c == Item.BUCKET.ba) {
            entityhuman.an.a(entityhuman.an.c, new ItemStack(Item.MILK_BUCKET));
            return true;
        } else {
            return false;
        }
    }

    public boolean v() {
        return this.l.a(this.z.b(0.0D, -0.6000000238418579D, 0.0D), Material.f, this);
    }

    public void o() {
        super.o();
        this.b = this.a;
        this.f = this.c;
        this.al = this.ak;
        this.an = this.am;
        this.ak += this.ap;
        if (this.ak > 6.2831855F) {
            this.ak -= 6.2831855F;
            if (this.W.nextInt(10) == 0) {
                this.ap = 1.0F / (this.W.nextFloat() + 1.0F) * 0.2F;
            }
        }

        if (this.v()) {
            float f;

            if (this.ak < 3.1415927F) {
                f = this.ak / 3.1415927F;
                this.am = MathHelper.a(f * f * 3.1415927F) * 3.1415927F * 0.25F;
                if ((double) f > 0.75D) {
                    this.ao = 1.0F;
                    this.aq = 1.0F;
                } else {
                    this.aq *= 0.8F;
                }
            } else {
                this.am = 0.0F;
                this.ao *= 0.9F;
                this.aq *= 0.99F;
            }

            if (!this.aW) {
                this.s = (double) (this.ar * this.ao);
                this.t = (double) (this.as * this.ao);
                this.u = (double) (this.at * this.ao);
            }

            f = MathHelper.a(this.s * this.s + this.u * this.u);
            this.aI += (-((float) Math.atan2(this.s, this.u)) * 180.0F / 3.1415927F - this.aI) * 0.1F;
            this.v = this.aI;
            this.c += 3.1415927F * this.aq * 1.5F;
            this.a += (-((float) Math.atan2((double) f, this.t)) * 180.0F / 3.1415927F - this.a) * 0.1F;
        } else {
            this.am = MathHelper.e(MathHelper.a(this.ak)) * 3.1415927F * 0.25F;
            if (!this.aW) {
                this.s = 0.0D;
                this.t -= 0.08D;
                this.t *= 0.9800000190734863D;
                this.u = 0.0D;
            }

            this.a = (float) ((double) this.a + (double) (-90.0F - this.a) * 0.02D);
        }
    }

    public void c(float f, float f1) {
        this.c(this.s, this.t, this.u);
    }

    protected void d() {
        if (this.W.nextInt(50) == 0 || !this.ab || this.ar == 0.0F && this.as == 0.0F && this.at == 0.0F) {
            float f = this.W.nextFloat() * 3.1415927F * 2.0F;

            this.ar = MathHelper.b(f) * 0.2F;
            this.as = -0.1F + this.W.nextFloat() * 0.2F;
            this.at = MathHelper.a(f) * 0.2F;
        }
    }
}
