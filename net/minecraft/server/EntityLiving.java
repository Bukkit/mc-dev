package net.minecraft.server;

import java.util.List;

public class EntityLiving extends Entity {

    public int au = 20;
    public float av;
    public float aw;
    public float ax;
    public float ay = 0.0F;
    public float az = 0.0F;
    protected float aA;
    protected float aB;
    protected float aC;
    protected float aD;
    protected boolean aE = true;
    protected String aF = "/char.png";
    protected boolean aG = true;
    protected float aH = 0.0F;
    protected String aI = null;
    protected float aJ = 1.0F;
    protected int aK = 0;
    protected float aL = 0.0F;
    public boolean aM = false;
    public float aN;
    public float aO;
    public int aP = 10;
    public int aQ;
    private int a;
    public int aR;
    public int aS;
    public float aT = 0.0F;
    public int aU = 0;
    public int aV = 0;
    public float aW;
    public float aX;
    protected boolean aY = false;
    public int aZ = -1;
    public float ba = (float) (Math.random() * 0.8999999761581421D + 0.10000000149011612D);
    public float bb;
    public float bc;
    public float bd;
    private int b;
    private double c;
    private double d;
    private double e;
    private double f;
    private double ai;
    float be = 0.0F;
    protected int bf = 0;
    protected float bg;
    protected float bh;
    protected float bi;
    protected boolean bj = false;
    protected float bk = 0.0F;
    protected float bl = 0.7F;
    private Entity aj;
    private int ak = 0;

    public EntityLiving(World world) {
        super(world);
        this.i = true;
        this.ax = (float) (Math.random() + 1.0D) * 0.01F;
        this.a(this.p, this.q, this.r);
        this.av = (float) Math.random() * 12398.0F;
        this.v = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.aw = 1.0F;
        this.R = 0.5F;
    }

    protected boolean g(Entity entity) {
        return this.l.a(Vec3D.b(this.p, this.q + (double) this.s(), this.r), Vec3D.b(entity.p, entity.q + (double) entity.s(), entity.r)) == null;
    }

    public boolean c_() {
        return !this.F;
    }

    public boolean u() {
        return !this.F;
    }

    protected float s() {
        return this.I * 0.85F;
    }

    public int b() {
        return 80;
    }

    public void m() {
        this.aN = this.aO;
        super.m();
        if (this.V.nextInt(1000) < this.a++) {
            this.a = -this.b();
            String s = this.d();

            if (s != null) {
                this.l.a(this, s, this.h(), (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
            }
        }

        if (this.w() && this.x()) {
            this.a((Entity) null, 1);
        }

        int i;

        if (this.w() && this.a(Material.f)) {
            --this.ac;
            if (this.ac == -20) {
                this.ac = 0;

                for (i = 0; i < 8; ++i) {
                    float f = this.V.nextFloat() - this.V.nextFloat();
                    float f1 = this.V.nextFloat() - this.V.nextFloat();
                    float f2 = this.V.nextFloat() - this.V.nextFloat();

                    this.l.a("bubble", this.p + (double) f, this.q + (double) f1, this.r + (double) f2, this.s, this.t, this.u);
                }

                this.a((Entity) null, 2);
            }

            this.Y = 0;
        } else {
            this.ac = this.Z;
        }

        this.aW = this.aX;
        if (this.aV > 0) {
            --this.aV;
        }

        if (this.aR > 0) {
            --this.aR;
        }

        if (this.ab > 0) {
            --this.ab;
        }

        if (this.aP <= 0) {
            ++this.aU;
            if (this.aU > 20) {
                this.K();
                this.l();

                for (i = 0; i < 20; ++i) {
                    double d0 = this.V.nextGaussian() * 0.02D;
                    double d1 = this.V.nextGaussian() * 0.02D;
                    double d2 = this.V.nextGaussian() * 0.02D;

                    this.l.a("explode", this.p + (double) (this.V.nextFloat() * this.H * 2.0F) - (double) this.H, this.q + (double) (this.V.nextFloat() * this.I), this.r + (double) (this.V.nextFloat() * this.H * 2.0F) - (double) this.H, d0, d1, d2);
                }
            }
        }

        this.aD = this.aC;
        this.az = this.ay;
        this.x = this.v;
        this.y = this.w;
    }

    public void I() {
        for (int i = 0; i < 20; ++i) {
            double d0 = this.V.nextGaussian() * 0.02D;
            double d1 = this.V.nextGaussian() * 0.02D;
            double d2 = this.V.nextGaussian() * 0.02D;
            double d3 = 10.0D;

            this.l.a("explode", this.p + (double) (this.V.nextFloat() * this.H * 2.0F) - (double) this.H - d0 * d3, this.q + (double) (this.V.nextFloat() * this.I) - d1 * d3, this.r + (double) (this.V.nextFloat() * this.H * 2.0F) - (double) this.H - d2 * d3, d0, d1, d2);
        }
    }

    public void y() {
        super.y();
        this.aA = this.aB;
        this.aB = 0.0F;
    }

    public void b_() {
        super.b_();
        this.D();
        double d0 = this.p - this.m;
        double d1 = this.r - this.o;
        float f = MathHelper.a(d0 * d0 + d1 * d1);
        float f1 = this.ay;
        float f2 = 0.0F;

        this.aA = this.aB;
        float f3 = 0.0F;

        if (f > 0.05F) {
            f3 = 1.0F;
            f2 = f * 3.0F;
            f1 = (float) Math.atan2(d1, d0) * 180.0F / 3.1415927F - 90.0F;
        }

        if (this.aO > 0.0F) {
            f1 = this.v;
        }

        if (!this.A) {
            f3 = 0.0F;
        }

        this.aB += (f3 - this.aB) * 0.3F;

        float f4;

        for (f4 = f1 - this.ay; f4 < -180.0F; f4 += 360.0F) {
            ;
        }

        while (f4 >= 180.0F) {
            f4 -= 360.0F;
        }

        this.ay += f4 * 0.3F;

        float f5;

        for (f5 = this.v - this.ay; f5 < -180.0F; f5 += 360.0F) {
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

        this.ay = this.v - f5;
        if (f5 * f5 > 2500.0F) {
            this.ay += f5 * 0.2F;
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

        while (this.ay - this.az < -180.0F) {
            this.az -= 360.0F;
        }

        while (this.ay - this.az >= 180.0F) {
            this.az += 360.0F;
        }

        while (this.w - this.y < -180.0F) {
            this.y -= 360.0F;
        }

        while (this.w - this.y >= 180.0F) {
            this.y += 360.0F;
        }

        this.aC += f2;
    }

    protected void a(float f, float f1) {
        super.a(f, f1);
    }

    public void a(int i) {
        if (this.aP > 0) {
            this.aP += i;
            if (this.aP > 20) {
                this.aP = 20;
            }

            this.ab = this.au / 2;
        }
    }

    public boolean a(Entity entity, int i) {
        if (this.l.z) {
            i = 0;
        }

        this.bf = 0;
        if (this.aP <= 0) {
            return false;
        } else {
            this.bc = 1.5F;
            if ((float) this.ab > (float) this.au / 2.0F) {
                if (this.aQ - i >= this.aP) {
                    return false;
                }

                this.aP = this.aQ - i;
            } else {
                this.aQ = this.aP;
                this.ab = this.au;
                this.aP -= i;
                this.aR = this.aS = 10;
            }

            this.aT = 0.0F;
            if (entity != null) {
                double d0 = entity.p - this.p;

                double d1;

                for (d1 = entity.r - this.r; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                    d0 = (Math.random() - Math.random()) * 0.01D;
                }

                this.aT = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - this.v;
                this.a(entity, i, d0, d1);
            } else {
                this.aT = (float) ((int) (Math.random() * 2.0D) * 180);
            }

            if (this.aP <= 0) {
                this.l.a(this, this.f(), this.h(), (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
                this.f(entity);
            } else {
                this.l.a(this, this.e(), this.h(), (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
            }

            return true;
        }
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
        if (this.aK > 0 && entity != null) {
            entity.b(this, this.aK);
        }

        this.aY = true;
        int i = this.g();

        if (i > 0) {
            int j = this.V.nextInt(3);

            for (int k = 0; k < j; ++k) {
                this.a(i, 1);
            }
        }
    }

    protected int g() {
        return 0;
    }

    protected void a(float f) {
        int i = (int) Math.ceil((double) (f - 3.0F));

        if (i > 0) {
            this.a((Entity) null, i);
            int j = this.l.a(MathHelper.b(this.p), MathHelper.b(this.q - 0.20000000298023224D - (double) this.G), MathHelper.b(this.r));

            if (j > 0) {
                StepSound stepsound = Block.n[j].br;

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
                    f2 = Block.n[i].bu * 0.91F;
                }
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);

            this.a(f, f1, this.A ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;
            if (this.A) {
                f2 = 0.54600006F;
                int j = this.l.a(MathHelper.b(this.p), MathHelper.b(this.z.b) - 1, MathHelper.b(this.r));

                if (j > 0) {
                    f2 = Block.n[j].bu * 0.91F;
                }
            }

            if (this.d_()) {
                this.M = 0.0F;
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

        this.bb = this.bc;
        d0 = this.p - this.m;
        double d1 = this.r - this.o;
        float f4 = MathHelper.a(d0 * d0 + d1 * d1) * 4.0F;

        if (f4 > 1.0F) {
            f4 = 1.0F;
        }

        this.bc += (f4 - this.bc) * 0.4F;
        this.bd += this.bc;
    }

    public boolean d_() {
        int i = MathHelper.b(this.p);
        int j = MathHelper.b(this.z.b);
        int k = MathHelper.b(this.r);

        return this.l.a(i, j, k) == Block.LADDER.bi || this.l.a(i, j + 1, k) == Block.LADDER.bi;
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Health", (short) this.aP);
        nbttagcompound.a("HurtTime", (short) this.aR);
        nbttagcompound.a("DeathTime", (short) this.aU);
        nbttagcompound.a("AttackTime", (short) this.aV);
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.aP = nbttagcompound.c("Health");
        if (!nbttagcompound.a("Health")) {
            this.aP = 10;
        }

        this.aR = nbttagcompound.c("HurtTime");
        this.aU = nbttagcompound.c("DeathTime");
        this.aV = nbttagcompound.c("AttackTime");
    }

    public boolean w() {
        return !this.F && this.aP > 0;
    }

    public void D() {
        if (this.b > 0) {
            double d0 = this.p + (this.c - this.p) / (double) this.b;
            double d1 = this.q + (this.d - this.q) / (double) this.b;
            double d2 = this.r + (this.e - this.r) / (double) this.b;

            double d3;

            for (d3 = this.f - (double) this.v; d3 < -180.0D; d3 += 360.0D) {
                ;
            }

            while (d3 >= 180.0D) {
                d3 -= 360.0D;
            }

            this.v = (float) ((double) this.v + d3 / (double) this.b);
            this.w = (float) ((double) this.w + (this.ai - (double) this.w) / (double) this.b);
            --this.b;
            this.a(d0, d1, d2);
            this.b(this.v, this.w);
        }

        if (this.aP <= 0) {
            this.bj = false;
            this.bg = 0.0F;
            this.bh = 0.0F;
            this.bi = 0.0F;
        } else if (!this.aM) {
            this.c();
        }

        boolean flag = this.r();
        boolean flag1 = this.t();

        if (this.bj) {
            if (flag) {
                this.t += 0.03999999910593033D;
            } else if (flag1) {
                this.t += 0.03999999910593033D;
            } else if (this.A) {
                this.J();
            }
        }

        this.bg *= 0.98F;
        this.bh *= 0.98F;
        this.bi *= 0.9F;
        this.c(this.bg, this.bh);
        List list = this.l.b((Entity) this, this.z.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity) list.get(i);

                if (entity.u()) {
                    entity.c((Entity) this);
                }
            }
        }
    }

    protected void J() {
        this.t = 0.41999998688697815D;
    }

    protected void c() {
        ++this.bf;
        EntityHuman entityhuman = this.l.a(this, -1.0D);

        if (entityhuman != null) {
            double d0 = entityhuman.p - this.p;
            double d1 = entityhuman.q - this.q;
            double d2 = entityhuman.r - this.r;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;

            if (d3 > 16384.0D) {
                this.l();
            }

            if (this.bf > 600 && this.V.nextInt(800) == 0) {
                if (d3 < 1024.0D) {
                    this.bf = 0;
                } else {
                    this.l();
                }
            }
        }

        this.bg = 0.0F;
        this.bh = 0.0F;
        float f = 8.0F;

        if (this.V.nextFloat() < 0.02F) {
            entityhuman = this.l.a(this, (double) f);
            if (entityhuman != null) {
                this.aj = entityhuman;
                this.ak = 10 + this.V.nextInt(20);
            } else {
                this.bi = (this.V.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.aj != null) {
            this.b(this.aj, 10.0F);
            if (this.ak-- <= 0 || this.aj.F || this.aj.b((Entity) this) > (double) (f * f)) {
                this.aj = null;
            }
        } else {
            if (this.V.nextFloat() < 0.05F) {
                this.bi = (this.V.nextFloat() - 0.5F) * 20.0F;
            }

            this.v += this.bi;
            this.w = this.bk;
        }

        boolean flag = this.r();
        boolean flag1 = this.t();

        if (flag || flag1) {
            this.bj = this.V.nextFloat() < 0.8F;
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

    public void K() {}

    public boolean a() {
        return this.l.a(this.z) && this.l.a((Entity) this, this.z).size() == 0 && !this.l.b(this.z);
    }

    protected void o() {
        this.a((Entity) null, 4);
    }

    public Vec3D c(float f) {
        if (f == 1.0F) {
            return Vec3D.b(this.p, this.q, this.r);
        } else {
            double d0 = this.m + (this.p - this.m) * (double) f;
            double d1 = this.n + (this.q - this.n) * (double) f;
            double d2 = this.o + (this.r - this.o) * (double) f;

            return Vec3D.b(d0, d1, d2);
        }
    }

    public Vec3D B() {
        return this.d(1.0F);
    }

    public Vec3D d(float f) {
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

    public MovingObjectPosition a(double d0, float f) {
        Vec3D vec3d = this.c(f);
        Vec3D vec3d1 = this.d(f);
        Vec3D vec3d2 = vec3d.c(vec3d1.a * d0, vec3d1.b * d0, vec3d1.c * d0);

        return this.l.a(vec3d, vec3d2);
    }

    public int i() {
        return 4;
    }
}
