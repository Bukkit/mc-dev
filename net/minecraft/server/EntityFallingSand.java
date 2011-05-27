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
        this.e = true;
        this.a(0.98F, 0.98F);
        this.C = this.E / 2.0F;
        this.a((double) f, (double) f1, (double) f2);
        this.o = 0.0D;
        this.p = 0.0D;
        this.q = 0.0D;
        this.H = false;
        this.i = (double) f;
        this.j = (double) f1;
        this.k = (double) f2;
    }

    public boolean c_() {
        return !this.B;
    }

    public void b_() {
        if (this.a == 0) {
            this.j();
        } else {
            this.i = this.l;
            this.j = this.m;
            this.k = this.n;
            ++this.b;
            this.p -= 0.03999999910593033D;
            this.c(this.o, this.p, this.q);
            this.o *= 0.9800000190734863D;
            this.p *= 0.9800000190734863D;
            this.q *= 0.9800000190734863D;
            int i = MathHelper.b(this.l);
            int j = MathHelper.b(this.m);
            int k = MathHelper.b(this.n);

            if (this.h.a(i, j, k) == this.a) {
                this.h.d(i, j, k, 0);
            }

            if (this.w) {
                this.o *= 0.699999988079071D;
                this.q *= 0.699999988079071D;
                this.p *= -0.5D;
                this.j();
                if (!this.h.a(this.a, i, j, k, true) || !this.h.d(i, j, k, this.a)) {
                    this.a(this.a, 1);
                }
            } else if (this.b > 100) {
                this.a(this.a, 1);
                this.j();
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
