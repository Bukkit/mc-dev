package net.minecraft.server;

public class EntityItem extends Entity {

    public ItemStack a;
    private int e;
    public int b = 0;
    public int c;
    private int f = 5;
    public float d = (float) (Math.random() * 3.141592653589793D * 2.0D);

    public EntityItem(World world, double d0, double d1, double d2, ItemStack itemstack) {
        super(world);
        this.a(0.25F, 0.25F);
        this.G = this.I / 2.0F;
        this.a(d0, d1, d2);
        this.a = itemstack;
        this.v = (float) (Math.random() * 360.0D);
        this.s = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.t = 0.20000000298023224D;
        this.u = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.L = false;
    }

    public EntityItem(World world) {
        super(world);
        this.a(0.25F, 0.25F);
        this.G = this.I / 2.0F;
    }

    public void b_() {
        super.b_();
        if (this.c > 0) {
            --this.c;
        }

        this.m = this.p;
        this.n = this.q;
        this.o = this.r;
        this.t -= 0.03999999910593033D;
        if (this.l.c(MathHelper.b(this.p), MathHelper.b(this.q), MathHelper.b(this.r)) == Material.g) {
            this.t = 0.20000000298023224D;
            this.s = (double) ((this.V.nextFloat() - this.V.nextFloat()) * 0.2F);
            this.u = (double) ((this.V.nextFloat() - this.V.nextFloat()) * 0.2F);
            this.l.a(this, "random.fizz", 0.4F, 2.0F + this.V.nextFloat() * 0.4F);
        }

        this.g(this.p, this.q, this.r);
        this.r();
        this.c(this.s, this.t, this.u);
        float f = 0.98F;

        if (this.A) {
            f = 0.58800006F;
            int i = this.l.a(MathHelper.b(this.p), MathHelper.b(this.z.b) - 1, MathHelper.b(this.r));

            if (i > 0) {
                f = Block.n[i].bu * 0.98F;
            }
        }

        this.s *= (double) f;
        this.t *= 0.9800000190734863D;
        this.u *= (double) f;
        if (this.A) {
            this.t *= -0.5D;
        }

        ++this.e;
        ++this.b;
        if (this.b >= 6000) {
            this.l();
        }
    }

    public boolean r() {
        return this.l.a(this.z, Material.f, this);
    }

    private boolean g(double d0, double d1, double d2) {
        int i = MathHelper.b(d0);
        int j = MathHelper.b(d1);
        int k = MathHelper.b(d2);
        double d3 = d0 - (double) i;
        double d4 = d1 - (double) j;
        double d5 = d2 - (double) k;

        if (Block.p[this.l.a(i, j, k)]) {
            boolean flag = !Block.p[this.l.a(i - 1, j, k)];
            boolean flag1 = !Block.p[this.l.a(i + 1, j, k)];
            boolean flag2 = !Block.p[this.l.a(i, j - 1, k)];
            boolean flag3 = !Block.p[this.l.a(i, j + 1, k)];
            boolean flag4 = !Block.p[this.l.a(i, j, k - 1)];
            boolean flag5 = !Block.p[this.l.a(i, j, k + 1)];
            byte b0 = -1;
            double d6 = 9999.0D;

            if (flag && d3 < d6) {
                d6 = d3;
                b0 = 0;
            }

            if (flag1 && 1.0D - d3 < d6) {
                d6 = 1.0D - d3;
                b0 = 1;
            }

            if (flag2 && d4 < d6) {
                d6 = d4;
                b0 = 2;
            }

            if (flag3 && 1.0D - d4 < d6) {
                d6 = 1.0D - d4;
                b0 = 3;
            }

            if (flag4 && d5 < d6) {
                d6 = d5;
                b0 = 4;
            }

            if (flag5 && 1.0D - d5 < d6) {
                d6 = 1.0D - d5;
                b0 = 5;
            }

            float f = this.V.nextFloat() * 0.2F + 0.1F;

            if (b0 == 0) {
                this.s = (double) (-f);
            }

            if (b0 == 1) {
                this.s = (double) f;
            }

            if (b0 == 2) {
                this.t = (double) (-f);
            }

            if (b0 == 3) {
                this.t = (double) f;
            }

            if (b0 == 4) {
                this.u = (double) (-f);
            }

            if (b0 == 5) {
                this.u = (double) f;
            }
        }

        return false;
    }

    protected void b(int i) {
        this.a((Entity) null, i);
    }

    public boolean a(Entity entity, int i) {
        this.f -= i;
        if (this.f <= 0) {
            this.l();
        }

        return false;
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Health", (short) ((byte) this.f));
        nbttagcompound.a("Age", (short) this.b);
        nbttagcompound.a("Item", this.a.a(new NBTTagCompound()));
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.f = nbttagcompound.c("Health") & 255;
        this.b = nbttagcompound.c("Age");
        NBTTagCompound nbttagcompound1 = nbttagcompound.j("Item");

        this.a = new ItemStack(nbttagcompound1);
    }

    public void a(EntityHuman entityhuman) {
        if (!this.l.z) {
            int i = this.a.a;

            if (this.c == 0 && entityhuman.ak.a(this.a)) {
                this.l.a(this, "random.pop", 0.2F, ((this.V.nextFloat() - this.V.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityhuman.c(this, i);
                this.l();
            }
        }
    }
}
