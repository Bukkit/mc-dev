package net.minecraft.server;

import java.util.List;

public abstract class EntityLiving extends Entity {

    public int aF = 20;
    public float aG;
    public float aH;
    public float aI = 0.0F;
    public float aJ = 0.0F;
    protected float aK;
    protected float aL;
    protected float aM;
    protected float aN;
    protected boolean aO = true;
    protected String aP = "/mob/char.png";
    protected boolean aQ = true;
    protected float aR = 0.0F;
    protected String aS = null;
    protected float aT = 1.0F;
    protected int aU = 0;
    protected float aV = 0.0F;
    public boolean aW = false;
    public float aX;
    public float aY;
    public int aZ = 10;
    public int ba;
    private int a;
    public int bb;
    public int bc;
    public float bd = 0.0F;
    public int be = 0;
    public int bf = 0;
    public float bg;
    public float bh;
    protected boolean bi = false;
    public int bj = -1;
    public float bk = (float) (Math.random() * 0.8999999761581421D + 0.10000000149011612D);
    public float bl;
    public float bm;
    public float bn;
    protected int bo;
    protected double bp;
    protected double bq;
    protected double br;
    protected double bs;
    protected double bt;
    float bu = 0.0F;
    protected int bv = 0;
    protected int bw = 0;
    protected float bx;
    protected float by;
    protected float bz;
    protected boolean bA = false;
    protected float bB = 0.0F;
    protected float bC = 0.7F;
    private Entity b;
    private int c = 0;

    public EntityLiving(World world) {
        super(world);
        this.i = true;
        this.aH = (float) (Math.random() + 1.0D) * 0.01F;
        this.a(this.p, this.q, this.r);
        this.aG = (float) Math.random() * 12398.0F;
        this.v = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.S = 0.5F;
    }

    protected void a() {}

    public boolean i(Entity entity) {
        return this.l.a(Vec3D.b(this.p, this.q + (double) this.w(), this.r), Vec3D.b(entity.p, entity.q + (double) entity.w(), entity.r)) == null;
    }

    public boolean c_() {
        return !this.G;
    }

    public boolean z() {
        return !this.G;
    }

    public float w() {
        return this.J * 0.85F;
    }

    public int c() {
        return 80;
    }

    public void r() {
        this.aX = this.aY;
        super.r();
        if (this.W.nextInt(1000) < this.a++) {
            this.a = -this.c();
            String s = this.e();

            if (s != null) {
                this.l.a(this, s, this.i(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
            }
        }

        if (this.B() && this.C()) {
            this.a((Entity) null, 1);
        }

        if (this.ae || this.l.z) {
            this.Z = 0;
        }

        int i;

        if (this.B() && this.a(Material.f) && !this.d_()) {
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

        this.bg = this.bh;
        if (this.bf > 0) {
            --this.bf;
        }

        if (this.bb > 0) {
            --this.bb;
        }

        if (this.ac > 0) {
            --this.ac;
        }

        if (this.aZ <= 0) {
            ++this.be;
            if (this.be > 20) {
                this.T();
                this.q();

                for (i = 0; i < 20; ++i) {
                    double d0 = this.W.nextGaussian() * 0.02D;
                    double d1 = this.W.nextGaussian() * 0.02D;
                    double d2 = this.W.nextGaussian() * 0.02D;

                    this.l.a("explode", this.p + (double) (this.W.nextFloat() * this.I * 2.0F) - (double) this.I, this.q + (double) (this.W.nextFloat() * this.J), this.r + (double) (this.W.nextFloat() * this.I * 2.0F) - (double) this.I, d0, d1, d2);
                }
            }
        }

        this.aN = this.aM;
        this.aJ = this.aI;
        this.x = this.v;
        this.y = this.w;
    }

    public void R() {
        for (int i = 0; i < 20; ++i) {
            double d0 = this.W.nextGaussian() * 0.02D;
            double d1 = this.W.nextGaussian() * 0.02D;
            double d2 = this.W.nextGaussian() * 0.02D;
            double d3 = 10.0D;

            this.l.a("explode", this.p + (double) (this.W.nextFloat() * this.I * 2.0F) - (double) this.I - d0 * d3, this.q + (double) (this.W.nextFloat() * this.J) - d1 * d3, this.r + (double) (this.W.nextFloat() * this.I * 2.0F) - (double) this.I - d2 * d3, d0, d1, d2);
        }
    }

    public void D() {
        super.D();
        this.aK = this.aL;
        this.aL = 0.0F;
    }

    public void b_() {
        super.b_();
        this.o();
        double d0 = this.p - this.m;
        double d1 = this.r - this.o;
        float f = MathHelper.a(d0 * d0 + d1 * d1);
        float f1 = this.aI;
        float f2 = 0.0F;

        this.aK = this.aL;
        float f3 = 0.0F;

        if (f > 0.05F) {
            f3 = 1.0F;
            f2 = f * 3.0F;
            f1 = (float) Math.atan2(d1, d0) * 180.0F / 3.1415927F - 90.0F;
        }

        if (this.aY > 0.0F) {
            f1 = this.v;
        }

        if (!this.A) {
            f3 = 0.0F;
        }

        this.aL += (f3 - this.aL) * 0.3F;

        float f4;

        for (f4 = f1 - this.aI; f4 < -180.0F; f4 += 360.0F) {
            ;
        }

        while (f4 >= 180.0F) {
            f4 -= 360.0F;
        }

        this.aI += f4 * 0.3F;

        float f5;

        for (f5 = this.v - this.aI; f5 < -180.0F; f5 += 360.0F) {
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

        this.aI = this.v - f5;
        if (f5 * f5 > 2500.0F) {
            this.aI += f5 * 0.2F;
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

        while (this.aI - this.aJ < -180.0F) {
            this.aJ -= 360.0F;
        }

        while (this.aI - this.aJ >= 180.0F) {
            this.aJ += 360.0F;
        }

        while (this.w - this.y < -180.0F) {
            this.y -= 360.0F;
        }

        while (this.w - this.y >= 180.0F) {
            this.y += 360.0F;
        }

        this.aM += f2;
    }

    protected void a(float f, float f1) {
        super.a(f, f1);
    }

    public void d(int i) {
        if (this.aZ > 0) {
            this.aZ += i;
            if (this.aZ > 20) {
                this.aZ = 20;
            }

            this.ac = this.aF / 2;
        }
    }

    public boolean a(Entity entity, int i) {
        if (this.l.z) {
            return false;
        } else {
            this.bw = 0;
            if (this.aZ <= 0) {
                return false;
            } else {
                this.bm = 1.5F;
                boolean flag = true;

                if ((float) this.ac > (float) this.aF / 2.0F) {
                    if (i <= this.bv) {
                        return false;
                    }

                    this.e(i - this.bv);
                    this.bv = i;
                    flag = false;
                } else {
                    this.bv = i;
                    this.ba = this.aZ;
                    this.ac = this.aF;
                    this.e(i);
                    this.bb = this.bc = 10;
                }

                this.bd = 0.0F;
                if (flag) {
                    this.l.a(this, (byte) 2);
                    this.y();
                    if (entity != null) {
                        double d0 = entity.p - this.p;

                        double d1;

                        for (d1 = entity.r - this.r; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                            d0 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.bd = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - this.v;
                        this.a(entity, i, d0, d1);
                    } else {
                        this.bd = (float) ((int) (Math.random() * 2.0D) * 180);
                    }
                }

                if (this.aZ <= 0) {
                    if (flag) {
                        this.l.a(this, this.g(), this.i(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
                    }

                    this.f(entity);
                } else if (flag) {
                    this.l.a(this, this.f(), this.i(), (this.W.nextFloat() - this.W.nextFloat()) * 0.2F + 1.0F);
                }

                return true;
            }
        }
    }

    protected void e(int i) {
        this.aZ -= i;
    }

    protected float i() {
        return 1.0F;
    }

    protected String e() {
        return null;
    }

    protected String f() {
        return "random.hurt";
    }

    protected String g() {
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
        if (this.aU > 0 && entity != null) {
            entity.b(this, this.aU);
        }

        this.bi = true;
        if (!this.l.z) {
            this.g_();
        }

        this.l.a(this, (byte) 3);
    }

    protected void g_() {
        int i = this.h();

        if (i > 0) {
            int j = this.W.nextInt(3);

            for (int k = 0; k < j; ++k) {
                this.a(i, 1);
            }
        }
    }

    protected int h() {
        return 0;
    }

    protected void a(float f) {
        int i = (int) Math.ceil((double) (f - 3.0F));

        if (i > 0) {
            this.a((Entity) null, i);
            int j = this.l.a(MathHelper.b(this.p), MathHelper.b(this.q - 0.20000000298023224D - (double) this.H), MathHelper.b(this.r));

            if (j > 0) {
                StepSound stepsound = Block.m[j].br;

                this.l.a(this, stepsound.c(), stepsound.a() * 0.5F, stepsound.b() * 0.75F);
            }
        }
    }

    public void c(float f, float f1) {
        double d0;

        if (this.v()) {
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
        } else if (this.x()) {
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
                    f2 = Block.m[i].bu * 0.91F;
                }
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);

            this.a(f, f1, this.A ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;
            if (this.A) {
                f2 = 0.54600006F;
                int j = this.l.a(MathHelper.b(this.p), MathHelper.b(this.z.b) - 1, MathHelper.b(this.r));

                if (j > 0) {
                    f2 = Block.m[j].bu * 0.91F;
                }
            }

            if (this.m()) {
                this.N = 0.0F;
                if (this.t < -0.15D) {
                    this.t = -0.15D;
                }
            }

            this.c(this.s, this.t, this.u);
            if (this.B && this.m()) {
                this.t = 0.2D;
            }

            this.t -= 0.08D;
            this.t *= 0.9800000190734863D;
            this.s *= (double) f2;
            this.u *= (double) f2;
        }

        this.bl = this.bm;
        d0 = this.p - this.m;
        double d1 = this.r - this.o;
        float f4 = MathHelper.a(d0 * d0 + d1 * d1) * 4.0F;

        if (f4 > 1.0F) {
            f4 = 1.0F;
        }

        this.bm += (f4 - this.bm) * 0.4F;
        this.bn += this.bm;
    }

    public boolean m() {
        int i = MathHelper.b(this.p);
        int j = MathHelper.b(this.z.b);
        int k = MathHelper.b(this.r);

        return this.l.a(i, j, k) == Block.LADDER.bi || this.l.a(i, j + 1, k) == Block.LADDER.bi;
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Health", (short) this.aZ);
        nbttagcompound.a("HurtTime", (short) this.bb);
        nbttagcompound.a("DeathTime", (short) this.be);
        nbttagcompound.a("AttackTime", (short) this.bf);
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.aZ = nbttagcompound.c("Health");
        if (!nbttagcompound.a("Health")) {
            this.aZ = 10;
        }

        this.bb = nbttagcompound.c("HurtTime");
        this.be = nbttagcompound.c("DeathTime");
        this.bf = nbttagcompound.c("AttackTime");
    }

    public boolean B() {
        return !this.G && this.aZ > 0;
    }

    public boolean d_() {
        return false;
    }

    public void o() {
        if (this.bo > 0) {
            double d0 = this.p + (this.bp - this.p) / (double) this.bo;
            double d1 = this.q + (this.bq - this.q) / (double) this.bo;
            double d2 = this.r + (this.br - this.r) / (double) this.bo;

            double d3;

            for (d3 = this.bs - (double) this.v; d3 < -180.0D; d3 += 360.0D) {
                ;
            }

            while (d3 >= 180.0D) {
                d3 -= 360.0D;
            }

            this.v = (float) ((double) this.v + d3 / (double) this.bo);
            this.w = (float) ((double) this.w + (this.bt - (double) this.w) / (double) this.bo);
            --this.bo;
            this.a(d0, d1, d2);
            this.b(this.v, this.w);
        }

        if (this.aZ <= 0) {
            this.bA = false;
            this.bx = 0.0F;
            this.by = 0.0F;
            this.bz = 0.0F;
        } else if (!this.aW) {
            this.d();
        }

        boolean flag = this.v();
        boolean flag1 = this.x();

        if (this.bA) {
            if (flag) {
                this.t += 0.03999999910593033D;
            } else if (flag1) {
                this.t += 0.03999999910593033D;
            } else if (this.A) {
                this.S();
            }
        }

        this.bx *= 0.98F;
        this.by *= 0.98F;
        this.bz *= 0.9F;
        this.c(this.bx, this.by);
        List list = this.l.b((Entity) this, this.z.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity) list.get(i);

                if (entity.z()) {
                    entity.c((Entity) this);
                }
            }
        }
    }

    protected void S() {
        this.t = 0.41999998688697815D;
    }

    protected void d() {
        ++this.bw;
        EntityHuman entityhuman = this.l.a(this, -1.0D);

        if (entityhuman != null) {
            double d0 = entityhuman.p - this.p;
            double d1 = entityhuman.q - this.q;
            double d2 = entityhuman.r - this.r;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;

            if (d3 > 16384.0D) {
                this.q();
            }

            if (this.bw > 600 && this.W.nextInt(800) == 0) {
                if (d3 < 1024.0D) {
                    this.bw = 0;
                } else {
                    this.q();
                }
            }
        }

        this.bx = 0.0F;
        this.by = 0.0F;
        float f = 8.0F;

        if (this.W.nextFloat() < 0.02F) {
            entityhuman = this.l.a(this, (double) f);
            if (entityhuman != null) {
                this.b = entityhuman;
                this.c = 10 + this.W.nextInt(20);
            } else {
                this.bz = (this.W.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.b != null) {
            this.b(this.b, 10.0F);
            if (this.c-- <= 0 || this.b.G || this.b.b((Entity) this) > (double) (f * f)) {
                this.b = null;
            }
        } else {
            if (this.W.nextFloat() < 0.05F) {
                this.bz = (this.W.nextFloat() - 0.5F) * 20.0F;
            }

            this.v += this.bz;
            this.w = this.bB;
        }

        boolean flag = this.v();
        boolean flag1 = this.x();

        if (flag || flag1) {
            this.bA = this.W.nextFloat() < 0.8F;
        }
    }

    public void b(Entity entity, float f) {
        double d0 = entity.p - this.p;
        double d1 = entity.r - this.r;
        double d2;

        if (entity instanceof EntityLiving) {
            EntityLiving entityliving = (EntityLiving) entity;

            d2 = entityliving.q + (double) entityliving.w() - (this.q + (double) this.w());
        } else {
            d2 = (entity.z.b + entity.z.e) / 2.0D - (this.q + (double) this.w());
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

    public void T() {}

    public boolean b() {
        return this.l.a(this.z) && this.l.a((Entity) this, this.z).size() == 0 && !this.l.b(this.z);
    }

    protected void t() {
        this.a((Entity) null, 4);
    }

    public Vec3D G() {
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

    public int j() {
        return 4;
    }
}
