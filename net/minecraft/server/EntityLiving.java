package net.minecraft.server;

import java.util.List;

public class EntityLiving extends Entity {

    public int av = 20;
    public float aw;
    public float ax;
    public float ay;
    public float az = 0.0F;
    public float aA = 0.0F;
    protected float aB;
    protected float aC;
    protected float aD;
    protected float aE;
    protected boolean aF = true;
    protected String aG = "/mob/char.png";
    protected boolean aH = true;
    protected float aI = 0.0F;
    protected String aJ = null;
    protected float aK = 1.0F;
    protected int aL = 0;
    protected float aM = 0.0F;
    public boolean aN = false;
    public float aO;
    public float aP;
    public int aQ = 10;
    public int aR;
    private int a;
    public int aS;
    public int aT;
    public float aU = 0.0F;
    public int aV = 0;
    public int aW = 0;
    public float aX;
    public float aY;
    protected boolean aZ = false;
    public int ba = -1;
    public float bb = (float) (Math.random() * 0.8999999761581421D + 0.10000000149011612D);
    public float bc;
    public float bd;
    public float be;
    protected int bf;
    protected double bg;
    protected double bh;
    protected double bi;
    protected double bj;
    protected double bk;
    float bl = 0.0F;
    protected int bm = 0;
    protected int bn = 0;
    protected float bo;
    protected float bp;
    protected float bq;
    protected boolean br = false;
    protected float bs = 0.0F;
    protected float bt = 0.7F;
    private Entity b;
    private int c = 0;

    public EntityLiving(World world) {
        super(world);
        this.i = true;
        this.ay = (float) (Math.random() + 1.0D) * 0.01F;
        this.a(this.p, this.q, this.r);
        this.aw = (float) Math.random() * 12398.0F;
        this.v = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.ax = 1.0F;
        this.S = 0.5F;
    }

    public boolean i(Entity entity) {
        return this.l.a(Vec3D.b(this.p, this.q + (double) this.s(), this.r), Vec3D.b(entity.p, entity.q + (double) entity.s(), entity.r)) == null;
    }

    public boolean c_() {
        return !this.G;
    }

    public boolean v() {
        return !this.G;
    }

    public float s() {
        return this.J * 0.85F;
    }

    public int b() {
        return 80;
    }

    public void m() {
        this.aO = this.aP;
        super.m();
        if (this.W.nextInt(1000) < this.a++) {
            this.a = -this.b();
            String s = this.d();

            if (s != null) {
                this.l.a(this, s, this.h(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
            }
        }

        if (this.x() && this.y()) {
            this.a((Entity) null, 1);
        }

        if (this.ae || this.l.z) {
            this.Z = 0;
        }

        int i;

        if (this.x() && this.a(Material.f)) {
            --this.ad;
            if (this.ad == -20) {
                this.ad = 0;

                for (i = 0; i < 8; ++i) {
                    float f = this.W.nextFloat() - this.W.nextFloat();
                    float f1 = this.W.nextFloat() - this.W.nextFloat();
                    float f2 = this.W.nextFloat() - this.W.nextFloat();

                    this.l.a("bubble", this.p + (double) f, this.q + (double) f1, this.r + (double) f2, this.s, this.t, this.u);
                }

                this.a((Entity) null, 2);
            }

            this.Z = 0;
        } else {
            this.ad = this.aa;
        }

        this.aX = this.aY;
        if (this.aW > 0) {
            --this.aW;
        }

        if (this.aS > 0) {
            --this.aS;
        }

        if (this.ac > 0) {
            --this.ac;
        }

        if (this.aQ <= 0) {
            ++this.aV;
            if (this.aV > 20) {
                this.L();
                this.l();

                for (i = 0; i < 20; ++i) {
                    double d0 = this.W.nextGaussian() * 0.02D;
                    double d1 = this.W.nextGaussian() * 0.02D;
                    double d2 = this.W.nextGaussian() * 0.02D;

                    this.l.a("explode", this.p + (double) (this.W.nextFloat() * this.I * 2.0F) - (double) this.I, this.q + (double) (this.W.nextFloat() * this.J), this.r + (double) (this.W.nextFloat() * this.I * 2.0F) - (double) this.I, d0, d1, d2);
                }
            }
        }

        this.aE = this.aD;
        this.aA = this.az;
        this.x = this.v;
        this.y = this.w;
    }

    public void J() {
        for (int i = 0; i < 20; ++i) {
            double d0 = this.W.nextGaussian() * 0.02D;
            double d1 = this.W.nextGaussian() * 0.02D;
            double d2 = this.W.nextGaussian() * 0.02D;
            double d3 = 10.0D;

            this.l.a("explode", this.p + (double) (this.W.nextFloat() * this.I * 2.0F) - (double) this.I - d0 * d3, this.q + (double) (this.W.nextFloat() * this.J) - d1 * d3, this.r + (double) (this.W.nextFloat() * this.I * 2.0F) - (double) this.I - d2 * d3, d0, d1, d2);
        }
    }

    public void z() {
        super.z();
        this.aB = this.aC;
        this.aC = 0.0F;
    }

    public void b_() {
        super.b_();
        this.E();
        double d0 = this.p - this.m;
        double d1 = this.r - this.o;
        float f = MathHelper.a(d0 * d0 + d1 * d1);
        float f1 = this.az;
        float f2 = 0.0F;

        this.aB = this.aC;
        float f3 = 0.0F;

        if (f > 0.05F) {
            f3 = 1.0F;
            f2 = f * 3.0F;
            f1 = (float) Math.atan2(d1, d0) * 180.0F / 3.1415927F - 90.0F;
        }

        if (this.aP > 0.0F) {
            f1 = this.v;
        }

        if (!this.A) {
            f3 = 0.0F;
        }

        this.aC += (f3 - this.aC) * 0.3F;

        float f4;

        for (f4 = f1 - this.az; f4 < -180.0F; f4 += 360.0F) {
            ;
        }

        while (f4 >= 180.0F) {
            f4 -= 360.0F;
        }

        this.az += f4 * 0.3F;

        float f5;

        for (f5 = this.v - this.az; f5 < -180.0F; f5 += 360.0F) {
            ;
        }

        while (f5 >= 180.0F) {
            f5 -= 360.0F;
        }

        boolean flag = f5 < -90.0F || f5 >= 90.0F;

        if (f5 < -75.0F) {
            f5 = -75.0F;
        }

        if (f5 >= 75.0F) {
            f5 = 75.0F;
        }

        this.az = this.v - f5;
        if (f5 * f5 > 2500.0F) {
            this.az += f5 * 0.2F;
        }

        if (flag) {
            f2 *= -1.0F;
        }

        while (this.v - this.x < -180.0F) {
            this.x -= 360.0F;
        }

        while (this.v - this.x >= 180.0F) {
            this.x += 360.0F;
        }

        while (this.az - this.aA < -180.0F) {
            this.aA -= 360.0F;
        }

        while (this.az - this.aA >= 180.0F) {
            this.aA += 360.0F;
        }

        while (this.w - this.y < -180.0F) {
            this.y -= 360.0F;
        }

        while (this.w - this.y >= 180.0F) {
            this.y += 360.0F;
        }

        this.aD += f2;
    }

    protected void a(float f, float f1) {
        super.a(f, f1);
    }

    public void a(int i) {
        if (this.aQ > 0) {
            this.aQ += i;
            if (this.aQ > 20) {
                this.aQ = 20;
            }

            this.ac = this.av / 2;
        }
    }

    public boolean a(Entity entity, int i) {
        if (this.l.z) {
            return false;
        } else {
            this.bn = 0;
            if (this.aQ <= 0) {
                return false;
            } else {
                this.bd = 1.5F;
                boolean flag = true;

                if ((float) this.ac > (float) this.av / 2.0F) {
                    if (i <= this.bm) {
                        return false;
                    }

                    this.c(i - this.bm);
                    this.bm = i;
                    flag = false;
                } else {
                    this.bm = i;
                    this.aR = this.aQ;
                    this.ac = this.av;
                    this.c(i);
                    this.aS = this.aT = 10;
                }

                this.aU = 0.0F;
                if (flag) {
                    this.u();
                    if (entity != null) {
                        double d0 = entity.p - this.p;

                        double d1;

                        for (d1 = entity.r - this.r; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                            d0 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.aU = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - this.v;
                        this.a(entity, i, d0, d1);
                    } else {
                        this.aU = (float) ((int) (Math.random() * 2.0D) * 180);
                    }
                }

                if (this.aQ <= 0) {
                    if (flag) {
                        this.l.a(this, this.f(), this.h(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
                    }

                    this.f(entity);
                } else if (flag) {
                    this.l.a(this, this.e(), this.h(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
                }

                return true;
            }
        }
    }

    protected void c(int i) {
        this.aQ -= i;
    }

    protected float h() {
        return 1.0F;
    }

    protected String d() {
        return null;
    }

    protected String e() {
        return "random.hurt";
    }

    protected String f() {
        return "random.hurt";
    }

    public void a(Entity entity, int i, double d0, double d1) {
        float f = MathHelper.a(d0 * d0 + d1 * d1);
        float f1 = 0.4F;

        this.s /= 2.0D;
        this.t /= 2.0D;
        this.u /= 2.0D;
        this.s -= d0 / (double) f * (double) f1;
        this.t += 0.4000000059604645D;
        this.u -= d1 / (double) f * (double) f1;
        if (this.t > 0.4000000059604645D) {
            this.t = 0.4000000059604645D;
        }
    }

    public void f(Entity entity) {
        if (this.aL > 0 && entity != null) {
            entity.b(this, this.aL);
        }

        this.aZ = true;
        if (!this.l.z) {
            int i = this.g();

            if (i > 0) {
                int j = this.W.nextInt(3);

                for (int k = 0; k < j; ++k) {
                    this.a(i, 1);
                }
            }
        }

        this.l.a(this, (byte) 3);
    }

    protected int g() {
        return 0;
    }

    protected void a(float f) {
        int i = (int) Math.ceil((double) (f - 3.0F));

        if (i > 0) {
            this.a((Entity) null, i);
            int j = this.l.a(MathHelper.b(this.p), MathHelper.b(this.q - 0.20000000298023224D - (double) this.H), MathHelper.b(this.r));

            if (j > 0) {
                StepSound stepsound = Block.m[j].bq;

                this.l.a(this, stepsound.c(), stepsound.a() * 0.5F, stepsound.b() * 0.75F);
            }
        }
    }

    public void c(float f, float f1) {
        double d0;

        if (this.r()) {
            d0 = this.q;
            this.a(f, f1, 0.02F);
            this.c(this.s, this.t, this.u);
            this.s *= 0.800000011920929D;
            this.t *= 0.800000011920929D;
            this.u *= 0.800000011920929D;
            this.t -= 0.02D;
            if (this.B && this.b(this.s, this.t + 0.6000000238418579D - this.q + d0, this.u)) {
                this.t = 0.30000001192092896D;
            }
        } else if (this.t()) {
            d0 = this.q;
            this.a(f, f1, 0.02F);
            this.c(this.s, this.t, this.u);
            this.s *= 0.5D;
            this.t *= 0.5D;
            this.u *= 0.5D;
            this.t -= 0.02D;
            if (this.B && this.b(this.s, this.t + 0.6000000238418579D - this.q + d0, this.u)) {
                this.t = 0.30000001192092896D;
            }
        } else {
            float f2 = 0.91F;

            if (this.A) {
                f2 = 0.54600006F;
                int i = this.l.a(MathHelper.b(this.p), MathHelper.b(this.z.b) - 1, MathHelper.b(this.r));

                if (i > 0) {
                    f2 = Block.m[i].bt * 0.91F;
                }
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);

            this.a(f, f1, this.A ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;
            if (this.A) {
                f2 = 0.54600006F;
                int j = this.l.a(MathHelper.b(this.p), MathHelper.b(this.z.b) - 1, MathHelper.b(this.r));

                if (j > 0) {
                    f2 = Block.m[j].bt * 0.91F;
                }
            }

            if (this.d_()) {
                this.N = 0.0F;
                if (this.t < -0.15D) {
                    this.t = -0.15D;
                }
            }

            this.c(this.s, this.t, this.u);
            if (this.B && this.d_()) {
                this.t = 0.2D;
            }

            this.t -= 0.08D;
            this.t *= 0.9800000190734863D;
            this.s *= (double) f2;
            this.u *= (double) f2;
        }

        this.bc = this.bd;
        d0 = this.p - this.m;
        double d1 = this.r - this.o;
        float f4 = MathHelper.a(d0 * d0 + d1 * d1) * 4.0F;

        if (f4 > 1.0F) {
            f4 = 1.0F;
        }

        this.bd += (f4 - this.bd) * 0.4F;
        this.be += this.bd;
    }

    public boolean d_() {
        int i = MathHelper.b(this.p);
        int j = MathHelper.b(this.z.b);
        int k = MathHelper.b(this.r);

        return this.l.a(i, j, k) == Block.LADDER.bh || this.l.a(i, j + 1, k) == Block.LADDER.bh;
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Health", (short) this.aQ);
        nbttagcompound.a("HurtTime", (short) this.aS);
        nbttagcompound.a("DeathTime", (short) this.aV);
        nbttagcompound.a("AttackTime", (short) this.aW);
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.aQ = nbttagcompound.c("Health");
        if (!nbttagcompound.a("Health")) {
            this.aQ = 10;
        }

        this.aS = nbttagcompound.c("HurtTime");
        this.aV = nbttagcompound.c("DeathTime");
        this.aW = nbttagcompound.c("AttackTime");
    }

    public boolean x() {
        return !this.G && this.aQ > 0;
    }

    public void E() {
        if (this.bf > 0) {
            double d0 = this.p + (this.bg - this.p) / (double) this.bf;
            double d1 = this.q + (this.bh - this.q) / (double) this.bf;
            double d2 = this.r + (this.bi - this.r) / (double) this.bf;

            double d3;

            for (d3 = this.bj - (double) this.v; d3 < -180.0D; d3 += 360.0D) {
                ;
            }

            while (d3 >= 180.0D) {
                d3 -= 360.0D;
            }

            this.v = (float) ((double) this.v + d3 / (double) this.bf);
            this.w = (float) ((double) this.w + (this.bk - (double) this.w) / (double) this.bf);
            --this.bf;
            this.a(d0, d1, d2);
            this.b(this.v, this.w);
        }

        if (this.aQ <= 0) {
            this.br = false;
            this.bo = 0.0F;
            this.bp = 0.0F;
            this.bq = 0.0F;
        } else if (!this.aN) {
            this.c();
        }

        boolean flag = this.r();
        boolean flag1 = this.t();

        if (this.br) {
            if (flag) {
                this.t += 0.03999999910593033D;
            } else if (flag1) {
                this.t += 0.03999999910593033D;
            } else if (this.A) {
                this.K();
            }
        }

        this.bo *= 0.98F;
        this.bp *= 0.98F;
        this.bq *= 0.9F;
        this.c(this.bo, this.bp);
        List list = this.l.b((Entity) this, this.z.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity) list.get(i);

                if (entity.v()) {
                    entity.c((Entity) this);
                }
            }
        }
    }

    protected void K() {
        this.t = 0.41999998688697815D;
    }

    protected void c() {
        ++this.bn;
        EntityHuman entityhuman = this.l.a(this, -1.0D);

        if (entityhuman != null) {
            double d0 = entityhuman.p - this.p;
            double d1 = entityhuman.q - this.q;
            double d2 = entityhuman.r - this.r;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;

            if (d3 > 16384.0D) {
                this.l();
            }

            if (this.bn > 600 && this.W.nextInt(800) == 0) {
                if (d3 < 1024.0D) {
                    this.bn = 0;
                } else {
                    this.l();
                }
            }
        }

        this.bo = 0.0F;
        this.bp = 0.0F;
        float f = 8.0F;

        if (this.W.nextFloat() < 0.02F) {
            entityhuman = this.l.a(this, (double) f);
            if (entityhuman != null) {
                this.b = entityhuman;
                this.c = 10 + this.W.nextInt(20);
            } else {
                this.bq = (this.W.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.b != null) {
            this.b(this.b, 10.0F);
            if (this.c-- <= 0 || this.b.G || this.b.b((Entity) this) > (double) (f * f)) {
                this.b = null;
            }
        } else {
            if (this.W.nextFloat() < 0.05F) {
                this.bq = (this.W.nextFloat() - 0.5F) * 20.0F;
            }

            this.v += this.bq;
            this.w = this.bs;
        }

        boolean flag = this.r();
        boolean flag1 = this.t();

        if (flag || flag1) {
            this.br = this.W.nextFloat() < 0.8F;
        }
    }

    public void b(Entity entity, float f) {
        double d0 = entity.p - this.p;
        double d1 = entity.r - this.r;
        double d2;

        if (entity instanceof EntityLiving) {
            EntityLiving entityliving = (EntityLiving) entity;

            d2 = entityliving.q + (double) entityliving.s() - (this.q + (double) this.s());
        } else {
            d2 = (entity.z.b + entity.z.e) / 2.0D - (this.q + (double) this.s());
        }

        double d3 = (double) MathHelper.a(d0 * d0 + d1 * d1);
        float f1 = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
        float f2 = (float) (Math.atan2(d2, d3) * 180.0D / 3.1415927410125732D);

        this.w = this.b(this.w, f2, f);
        this.v = this.b(this.v, f1, f);
    }

    private float b(float f, float f1, float f2) {
        float f3;

        for (f3 = f1 - f; f3 < -180.0F; f3 += 360.0F) {
            ;
        }

        while (f3 >= 180.0F) {
            f3 -= 360.0F;
        }

        if (f3 > f2) {
            f3 = f2;
        }

        if (f3 < -f2) {
            f3 = -f2;
        }

        return f + f3;
    }

    public void L() {}

    public boolean a() {
        return this.l.a(this.z) && this.l.a((Entity) this, this.z).size() == 0 && !this.l.b(this.z);
    }

    protected void o() {
        this.a((Entity) null, 4);
    }

    public Vec3D C() {
        return this.c(1.0F);
    }

    public Vec3D c(float f) {
        float f1;
        float f2;
        float f3;
        float f4;

        if (f == 1.0F) {
            f1 = MathHelper.b(-this.v * 0.017453292F - 3.1415927F);
            f2 = MathHelper.a(-this.v * 0.017453292F - 3.1415927F);
            f3 = -MathHelper.b(-this.w * 0.017453292F);
            f4 = MathHelper.a(-this.w * 0.017453292F);
            return Vec3D.b((double) (f2 * f3), (double) f4, (double) (f1 * f3));
        } else {
            f1 = this.y + (this.w - this.y) * f;
            f2 = this.x + (this.v - this.x) * f;
            f3 = MathHelper.b(-f2 * 0.017453292F - 3.1415927F);
            f4 = MathHelper.a(-f2 * 0.017453292F - 3.1415927F);
            float f5 = -MathHelper.b(-f1 * 0.017453292F);
            float f6 = MathHelper.a(-f1 * 0.017453292F);

            return Vec3D.b((double) (f4 * f5), (double) f6, (double) (f3 * f5));
        }
    }

    public int i() {
        return 4;
    }
}
