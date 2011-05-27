package net.minecraft.server;

public class EntityTNTPrimed extends Entity {

    public int a;

    public EntityTNTPrimed(World world) {
        super(world);
        this.a = 0;
        this.e = true;
        this.a(0.98F, 0.98F);
        this.C = this.E / 2.0F;
    }

    public EntityTNTPrimed(World world, float f, float f1, float f2) {
        this(world);
        this.a((double) f, (double) f1, (double) f2);
        float f3 = (float) (Math.random() * 3.1415927410125732D * 2.0D);

        this.o = (double) (-MathHelper.a(f3 * 3.1415927F / 180.0F) * 0.02F);
        this.p = 0.20000000298023224D;
        this.q = (double) (-MathHelper.b(f3 * 3.1415927F / 180.0F) * 0.02F);
        this.H = false;
        this.a = 80;
        this.i = (double) f;
        this.j = (double) f1;
        this.k = (double) f2;
    }

    public boolean c_() {
        return !this.B;
    }

    public void b_() {
        this.i = this.l;
        this.j = this.m;
        this.k = this.n;
        this.p -= 0.03999999910593033D;
        this.c(this.o, this.p, this.q);
        this.o *= 0.9800000190734863D;
        this.p *= 0.9800000190734863D;
        this.q *= 0.9800000190734863D;
        if (this.w) {
            this.o *= 0.699999988079071D;
            this.q *= 0.699999988079071D;
            this.p *= -0.5D;
        }

        if (this.a-- <= 0) {
            this.j();
            this.b();
        } else {
            this.h.a("smoke", this.l, this.m + 0.5D, this.n, 0.0D, 0.0D, 0.0D);
        }
    }

    private void b() {
        float f = 4.0F;

        this.h.a((Entity) null, this.l, this.m, this.n, f);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Fuse", (byte) this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.a = nbttagcompound.b("Fuse");
    }
}
