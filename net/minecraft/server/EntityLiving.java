package net.minecraft.server;

import java.util.List;

public class EntityLiving extends Entity {

    public int aw = 20;
    public float ax;
    public float ay;
    public float az;
    public float aA = 0.0F;
    public float aB = 0.0F;
    protected float aC;
    protected float aD;
    protected float aE;
    protected float aF;
    protected boolean aG = true;
    protected String aH = "/mob/char.png";
    protected boolean aI = true;
    protected float aJ = 0.0F;
    protected String aK = null;
    protected float aL = 1.0F;
    protected int aM = 0;
    protected float aN = 0.0F;
    public boolean aO = false;
    public float aP;
    public float aQ;
    public int aR = 10;
    public int aS;
    private int a;
    public int aT;
    public int aU;
    public float aV = 0.0F;
    public int aW = 0;
    public int aX = 0;
    public float aY;
    public float aZ;
    protected boolean ba = false;
    public int bb = -1;
    public float bc = (float) (Math.random() * 0.8999999761581421D + 0.10000000149011612D);
    public float bd;
    public float be;
    public float bf;
    protected int bg;
    protected double bh;
    protected double bi;
    protected double bj;
    protected double bk;
    protected double bl;
    float bm = 0.0F;
    protected int bn = 0;
    protected int bo = 0;
    protected float bp;
    protected float bq;
    protected float br;
    protected boolean bs = false;
    protected float bt = 0.0F;
    protected float bu = 0.7F;
    private Entity b;
    private int c = 0;

    public EntityLiving(World world) {
        super(world);
        this.i = true;
        this.az = (float) (Math.random() + 1.0D) * 0.01F;
        this.a(this.p, this.q, this.r);
        this.ax = (float) Math.random() * 12398.0F;
        this.v = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.ay = 1.0F;
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
        this.aP = this.aQ;
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

        this.aY = this.aZ;
        if (this.aX > 0) {
            --this.aX;
        }

        if (this.aT > 0) {
            --this.aT;
        }

        if (this.ac > 0) {
            --this.ac;
        }

        if (this.aR <= 0) {
            ++this.aW;
            if (this.aW > 20) {
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

        this.aF = this.aE;
        this.aB = this.aA;
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
        this.aC = this.aD;
        this.aD = 0.0F;
    }

    public void b_() {
        super.b_();
        this.E();
        double d0 = this.p - this.m;
        double d1 = this.r - this.o;
        float f = MathHelper.a(d0 * d0 + d1 * d1);
        float f1 = this.aA;
        float f2 = 0.0F;

        this.aC = this.aD;
        float f3 = 0.0F;

        if (f > 0.05F) {
            f3 = 1.0F;
            f2 = f * 3.0F;
            f1 = (float) Math.atan2(d1, d0) * 180.0F / 3.1415927F - 90.0F;
        }

        if (this.aQ > 0.0F) {
            f1 = this.v;
        }

        if (!this.A) {
            f3 = 0.0F;
        }

        this.aD += (f3 - this.aD) * 0.3F;

        float f4;

        for (f4 = f1 - this.aA; f4 < -180.0F; f4 += 360.0F) {
            ;
        }

        while (f4 >= 180.0F) {
            f4 -= 360.0F;
        }

        this.aA += f4 * 0.3F;

        float f5;

        for (f5 = this.v - this.aA; f5 < -180.0F; f5 += 360.0F) {
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

        this.aA = this.v - f5;
        if (f5 * f5 > 2500.0F) {
            this.aA += f5 * 0.2F;
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

        while (this.aA - this.aB < -180.0F) {
            this.aB -= 360.0F;
        }

        while (this.aA - this.aB >= 180.0F) {
            this.aB += 360.0F;
        }

        while (this.w - this.y < -180.0F) {
            this.y -= 360.0F;
        }

        while (this.w - this.y >= 180.0F) {
            this.y += 360.0F;
        }

        this.aE += f2;
    }

    protected void a(float f, float f1) {
        super.a(f, f1);
    }

    public void a(int i) {
        if (this.aR > 0) {
            this.aR += i;
            if (this.aR > 20) {
                this.aR = 20;
            }

            this.ac = this.aw / 2;
        }
    }

    public boolean a(Entity entity, int i) {
        if (this.l.z) {
            return false;
        } else {
            this.bo = 0;
            if (this.aR <= 0) {
                return false;
            } else {
                this.be = 1.5F;
                boolean flag = true;

                if ((float) this.ac > (float) this.aw / 2.0F) {
                    if (i <= this.bn) {
                        return false;
                    }

                    this.c(i - this.bn);
                    this.bn = i;
                    flag = false;
                } else {
                    this.bn = i;
                    this.aS = this.aR;
                    this.ac = this.aw;
                    this.c(i);
                    this.aT = this.aU = 10;
                }

                this.aV = 0.0F;
                if (flag) {
                    this.l.a(this, (byte) 2);
                    this.u();
                    if (entity != null) {
                        double d0 = entity.p - this.p;

                        double d1;

                        for (d1 = entity.r - this.r; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                            d0 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.aV = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - this.v;
                        this.a(entity, i, d0, d1);
                    } else {
                        this.aV = (float) ((int) (Math.random() * 2.0D) * 180);
                    }
                }

                if (this.aR <= 0) {
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
        this.aR -= i;
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
        if (this.aM > 0 && entity != null) {
            entity.b(this, this.aM);
        }

        this.ba = true;
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

        this.bd = this.be;
        d0 = this.p - this.m;
        double d1 = this.r - this.o;
        float f4 = MathHelper.a(d0 * d0 + d1 * d1) * 4.0F;

        if (f4 > 1.0F) {
            f4 = 1.0F;
        }

        this.be += (f4 - this.be) * 0.4F;
        this.bf += this.be;
    }

    public boolean d_() {
        int i = MathHelper.b(this.p);
        int j = MathHelper.b(this.z.b);
        int k = MathHelper.b(this.r);

        return this.l.a(i, j, k) == Block.LADDER.bh || this.l.a(i, j + 1, k) == Block.LADDER.bh;
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Health", (short) this.aR);
        nbttagcompound.a("HurtTime", (short) this.aT);
        nbttagcompound.a("DeathTime", (short) this.aW);
        nbttagcompound.a("AttackTime", (short) this.aX);
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.aR = nbttagcompound.c("Health");
        if (!nbttagcompound.a("Health")) {
            this.aR = 10;
        }

        this.aT = nbttagcompound.c("HurtTime");
        this.aW = nbttagcompound.c("DeathTime");
        this.aX = nbttagcompound.c("AttackTime");
    }

    public boolean x() {
        return !this.G && this.aR > 0;
    }

    public void E() {
        if (this.bg > 0) {
            double d0 = this.p + (this.bh - this.p) / (double) this.bg;
            double d1 = this.q + (this.bi - this.q) / (double) this.bg;
            double d2 = this.r + (this.bj - this.r) / (double) this.bg;

            double d3;

            for (d3 = this.bk - (double) this.v; d3 < -180.0D; d3 += 360.0D) {
                ;
            }

            while (d3 >= 180.0D) {
                d3 -= 360.0D;
            }

            this.v = (float) ((double) this.v + d3 / (double) this.bg);
            this.w = (float) ((double) this.w + (this.bl - (double) this.w) / (double) this.bg);
            --this.bg;
            this.a(d0, d1, d2);
            this.b(this.v, this.w);
        }

        if (this.aR <= 0) {
            this.bs = false;
            this.bp = 0.0F;
            this.bq = 0.0F;
            this.br = 0.0F;
        } else if (!this.aO) {
            this.c();
        }

        boolean flag = this.r();
        boolean flag1 = this.t();

        if (this.bs) {
            if (flag) {
                this.t += 0.03999999910593033D;
            } else if (flag1) {
                this.t += 0.03999999910593033D;
            } else if (this.A) {
                this.K();
            }
        }

        this.bp *= 0.98F;
        this.bq *= 0.98F;
        this.br *= 0.9F;
        this.c(this.bp, this.bq);
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
        ++this.bo;
        EntityHuman entityhuman = this.l.a(this, -1.0D);

        if (entityhuman != null) {
            double d0 = entityhuman.p - this.p;
            double d1 = entityhuman.q - this.q;
            double d2 = entityhuman.r - this.r;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;

            if (d3 > 16384.0D) {
                this.l();
            }

            if (this.bo > 600 && this.W.nextInt(800) == 0) {
                if (d3 < 1024.0D) {
                    this.bo = 0;
                } else {
                    this.l();
                }
            }
        }

        this.bp = 0.0F;
        this.bq = 0.0F;
        float f = 8.0F;

        if (this.W.nextFloat() < 0.02F) {
            entityhuman = this.l.a(this, (double) f);
            if (entityhuman != null) {
                this.b = entityhuman;
                this.c = 10 + this.W.nextInt(20);
            } else {
                this.br = (this.W.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.b != null) {
            this.b(this.b, 10.0F);
            if (this.c-- <= 0 || this.b.G || this.b.b((Entity) this) > (double) (f * f)) {
                this.b = null;
            }
        } else {
            if (this.W.nextFloat() < 0.05F) {
                this.br = (this.W.nextFloat() - 0.5F) * 20.0F;
            }

            this.v += this.br;
            this.w = this.bt;
        }

        boolean flag = this.r();
        boolean flag1 = this.t();

        if (flag || flag1) {
            this.bs = this.W.nextFloat() < 0.8F;
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

        this.w = -this.b(this.w, f2, f);
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
