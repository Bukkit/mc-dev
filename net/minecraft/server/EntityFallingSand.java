package net.minecraft.server;

public class EntityFallingSand extends Entity {

    public int a;
    public int b = 0;

    public EntityFallingSand(World world) {
        super(world);
    }

    public EntityFallingSand(World world, float f, float f1, float f2, int i) {
        super(world);
        this.a = i;
        this.i = true;
        this.a(0.98F, 0.98F);
        this.G = this.I / 2.0F;
        this.a((double) f, (double) f1, (double) f2);
        this.s = 0.0D;
        this.t = 0.0D;
        this.u = 0.0D;
        this.L = false;
        this.m = (double) f;
        this.n = (double) f1;
        this.o = (double) f2;
    }

    public boolean c_() {
        return !this.F;
    }

    public void b_() {
        if (this.a == 0) {
            this.l();
        } else {
            this.m = this.p;
            this.n = this.q;
            this.o = this.r;
            ++this.b;
            this.t -= 0.03999999910593033D;
            this.c(this.s, this.t, this.u);
            this.s *= 0.9800000190734863D;
            this.t *= 0.9800000190734863D;
            this.u *= 0.9800000190734863D;
            int i = MathHelper.b(this.p);
            int j = MathHelper.b(this.q);
            int k = MathHelper.b(this.r);

            if (this.l.a(i, j, k) == this.a) {
                this.l.d(i, j, k, 0);
            }

            if (this.A) {
                this.s *= 0.699999988079071D;
                this.u *= 0.699999988079071D;
                this.t *= -0.5D;
                this.l();
                if (!this.l.a(this.a, i, j, k, true) || !this.l.d(i, j, k, this.a)) {
                    this.a(this.a, 1);
                }
            } else if (this.b > 100) {
                this.a(this.a, 1);
                this.l();
            }
        }
    }

    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Tile", (byte) this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.a = nbttagcompound.b("Tile") & 255;
    }
}
