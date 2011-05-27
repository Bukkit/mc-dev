package net.minecraft.server;

import java.util.List;
import java.util.Random;

public abstract class Entity {

    private static int a = 0;
    public int c;
    public double d;
    public boolean e;
    public Entity f;
    public Entity g;
    protected World h;
    public double i;
    public double j;
    public double k;
    public double l;
    public double m;
    public double n;
    public double o;
    public double p;
    public double q;
    public float r;
    public float s;
    public float t;
    public float u;
    public final AxisAlignedBB v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;
    public boolean A;
    public boolean B;
    public float C;
    public float D;
    public float E;
    public float F;
    public float G;
    protected boolean H;
    protected float I;
    private int b;
    public double J;
    public double K;
    public double L;
    public float M;
    public float N;
    public boolean O;
    public float P;
    public boolean Q;
    protected Random R;
    public int S;
    public int T;
    public int U;
    protected int V;
    protected boolean W;
    public int X;
    public int Y;
    private boolean ad;
    private double ae;
    private double af;
    public boolean Z;
    public int aa;
    public int ab;
    public int ac;

    public Entity(World world) {
        this.c = a++;
        this.d = 1.0D;
        this.e = false;
        this.v = AxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.w = false;
        this.z = false;
        this.A = true;
        this.B = false;
        this.C = 0.0F;
        this.D = 0.6F;
        this.E = 1.8F;
        this.F = 0.0F;
        this.G = 0.0F;
        this.H = true;
        this.I = 0.0F;
        this.b = 1;
        this.M = 0.0F;
        this.N = 0.0F;
        this.O = false;
        this.P = 0.0F;
        this.Q = false;
        this.R = new Random();
        this.S = 0;
        this.T = 1;
        this.U = 0;
        this.V = 300;
        this.W = false;
        this.X = 0;
        this.Y = 300;
        this.ad = true;
        this.Z = false;
        this.h = world;
        this.a(0.0D, 0.0D, 0.0D);
    }

    public boolean equals(Object object) {
        return object instanceof Entity ? ((Entity) object).c == this.c : false;
    }

    public int hashCode() {
        return this.c;
    }

    public void j() {
        this.B = true;
    }

    protected void a(float f, float f1) {
        this.D = f;
        this.E = f1;
    }

    protected void b(float f, float f1) {
        this.r = f;
        this.s = f1;
    }

    public void a(double d0, double d1, double d2) {
        this.l = d0;
        this.m = d1;
        this.n = d2;
        float f = this.D / 2.0F;
        float f1 = this.E;

        this.v.c(d0 - (double) f, d1 - (double) this.C + (double) this.M, d2 - (double) f, d0 + (double) f, d1 - (double) this.C + (double) this.M + (double) f1, d2 + (double) f);
    }

    public void b_() {
        this.k();
    }

    public void k() {
        if (this.g != null && this.g.B) {
            this.g = null;
        }

        ++this.S;
        this.F = this.G;
        this.i = this.l;
        this.j = this.m;
        this.k = this.n;
        this.u = this.s;
        this.t = this.r;
        if (this.o()) {
            if (!this.W && !this.ad) {
                float f = MathHelper.a(this.o * this.o * 0.20000000298023224D + this.p * this.p + this.q * this.q * 0.20000000298023224D) * 0.2F;

                if (f > 1.0F) {
                    f = 1.0F;
                }

                this.h.a(this, "random.splash", f, 1.0F + (this.R.nextFloat() - this.R.nextFloat()) * 0.4F);
                float f1 = (float) MathHelper.b(this.v.b);

                int i;
                float f2;
                float f3;

                for (i = 0; (float) i < 1.0F + this.D * 20.0F; ++i) {
                    f2 = (this.R.nextFloat() * 2.0F - 1.0F) * this.D;
                    f3 = (this.R.nextFloat() * 2.0F - 1.0F) * this.D;
                    this.h.a("bubble", this.l + (double) f2, (double) (f1 + 1.0F), this.n + (double) f3, this.o, this.p - (double) (this.R.nextFloat() * 0.2F), this.q);
                }

                for (i = 0; (float) i < 1.0F + this.D * 20.0F; ++i) {
                    f2 = (this.R.nextFloat() * 2.0F - 1.0F) * this.D;
                    f3 = (this.R.nextFloat() * 2.0F - 1.0F) * this.D;
                    this.h.a("splash", this.l + (double) f2, (double) (f1 + 1.0F), this.n + (double) f3, this.o, this.p, this.q);
                }
            }

            this.I = 0.0F;
            this.W = true;
            this.U = 0;
        } else {
            this.W = false;
        }

        if (this.U > 0) {
            if (this.U % 20 == 0) {
                this.a((Entity) null, 1);
            }

            --this.U;
        }

        if (this.q()) {
            this.a((Entity) null, 10);
            this.U = 600;
        }

        if (this.m < -64.0D) {
            this.l();
        }

        this.ad = false;
    }

    protected void l() {
        this.j();
    }

    public boolean b(double d0, double d1, double d2) {
        AxisAlignedBB axisalignedbb = this.v.c(d0, d1, d2);
        List list = this.h.a(this, axisalignedbb);

        return list.size() > 0 ? false : !this.h.b(axisalignedbb);
    }

    public void c(double d0, double d1, double d2) {
        if (this.O) {
            this.v.d(d0, d1, d2);
            this.l = (this.v.a + this.v.d) / 2.0D;
            this.m = this.v.b + (double) this.C - (double) this.M;
            this.n = (this.v.c + this.v.f) / 2.0D;
        } else {
            double d3 = this.l;
            double d4 = this.n;
            double d5 = d0;
            double d6 = d1;
            double d7 = d2;
            AxisAlignedBB axisalignedbb = this.v.b();
            boolean flag = this.w && this.m();

            if (flag) {
                double d8;

                for (d8 = 0.05D; d0 != 0.0D && this.h.a(this, this.v.c(d0, -1.0D, 0.0D)).size() == 0; d5 = d0) {
                    if (d0 < d8 && d0 >= -d8) {
                        d0 = 0.0D;
                    } else if (d0 > 0.0D) {
                        d0 -= d8;
                    } else {
                        d0 += d8;
                    }
                }

                for (; d2 != 0.0D && this.h.a(this, this.v.c(0.0D, -1.0D, d2)).size() == 0; d7 = d2) {
                    if (d2 < d8 && d2 >= -d8) {
                        d2 = 0.0D;
                    } else if (d2 > 0.0D) {
                        d2 -= d8;
                    } else {
                        d2 += d8;
                    }
                }
            }

            List list = this.h.a(this, this.v.a(d0, d1, d2));

            for (int i = 0; i < list.size(); ++i) {
                d1 = ((AxisAlignedBB) list.get(i)).b(this.v, d1);
            }

            this.v.d(0.0D, d1, 0.0D);
            if (!this.A && d6 != d1) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            boolean flag1 = this.w || d6 != d1 && d6 < 0.0D;

            int j;

            for (j = 0; j < list.size(); ++j) {
                d0 = ((AxisAlignedBB) list.get(j)).a(this.v, d0);
            }

            this.v.d(d0, 0.0D, 0.0D);
            if (!this.A && d5 != d0) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            for (j = 0; j < list.size(); ++j) {
                d2 = ((AxisAlignedBB) list.get(j)).c(this.v, d2);
            }

            this.v.d(0.0D, 0.0D, d2);
            if (!this.A && d7 != d2) {
                d2 = 0.0D;
                d1 = 0.0D;
                d0 = 0.0D;
            }

            double d9;
            double d10;
            int k;

            if (this.N > 0.0F && flag1 && this.M < 0.05F && (d5 != d0 || d7 != d2)) {
                d9 = d0;
                d10 = d1;
                double d11 = d2;

                d0 = d5;
                d1 = (double) this.N;
                d2 = d7;
                AxisAlignedBB axisalignedbb1 = this.v.b();

                this.v.b(axisalignedbb);
                list = this.h.a(this, this.v.a(d5, d1, d7));

                for (k = 0; k < list.size(); ++k) {
                    d1 = ((AxisAlignedBB) list.get(k)).b(this.v, d1);
                }

                this.v.d(0.0D, d1, 0.0D);
                if (!this.A && d6 != d1) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (k = 0; k < list.size(); ++k) {
                    d0 = ((AxisAlignedBB) list.get(k)).a(this.v, d0);
                }

                this.v.d(d0, 0.0D, 0.0D);
                if (!this.A && d5 != d0) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                for (k = 0; k < list.size(); ++k) {
                    d2 = ((AxisAlignedBB) list.get(k)).c(this.v, d2);
                }

                this.v.d(0.0D, 0.0D, d2);
                if (!this.A && d7 != d2) {
                    d2 = 0.0D;
                    d1 = 0.0D;
                    d0 = 0.0D;
                }

                if (d9 * d9 + d11 * d11 >= d0 * d0 + d2 * d2) {
                    d0 = d9;
                    d1 = d10;
                    d2 = d11;
                    this.v.b(axisalignedbb1);
                } else {
                    this.M = (float) ((double) this.M + 0.5D);
                }
            }

            this.l = (this.v.a + this.v.d) / 2.0D;
            this.m = this.v.b + (double) this.C - (double) this.M;
            this.n = (this.v.c + this.v.f) / 2.0D;
            this.x = d5 != d0 || d7 != d2;
            this.y = d6 != d1;
            this.w = d6 != d1 && d6 < 0.0D;
            this.z = this.x || this.y;
            if (this.w) {
                if (this.I > 0.0F) {
                    this.a(this.I);
                    this.I = 0.0F;
                }
            } else if (d1 < 0.0D) {
                this.I = (float) ((double) this.I - d1);
            }

            if (d5 != d0) {
                this.o = 0.0D;
            }

            if (d6 != d1) {
                this.p = 0.0D;
            }

            if (d7 != d2) {
                this.q = 0.0D;
            }

            d9 = this.l - d3;
            d10 = this.n - d4;
            this.G = (float) ((double) this.G + (double) MathHelper.a(d9 * d9 + d10 * d10) * 0.6D);
            int l;
            int i1;
            int j1;

            if (this.H && !flag) {
                l = MathHelper.b(this.l);
                i1 = MathHelper.b(this.m - 0.20000000298023224D - (double) this.C);
                j1 = MathHelper.b(this.n);
                k = this.h.a(l, i1, j1);
                if (this.G > (float) this.b && k > 0) {
                    ++this.b;
                    StepSound stepsound = Block.n[k].bl;

                    if (this.h.a(l, i1 + 1, j1) == Block.SNOW.bc) {
                        stepsound = Block.SNOW.bl;
                        this.h.a(this, stepsound.c(), stepsound.a() * 0.15F, stepsound.b());
                    } else if (!Block.n[k].bn.d()) {
                        this.h.a(this, stepsound.c(), stepsound.a() * 0.15F, stepsound.b());
                    }

                    Block.n[k].b(this.h, l, i1, j1, this);
                }
            }

            l = MathHelper.b(this.v.a);
            i1 = MathHelper.b(this.v.b);
            j1 = MathHelper.b(this.v.c);
            k = MathHelper.b(this.v.d);
            int k1 = MathHelper.b(this.v.e);
            int l1 = MathHelper.b(this.v.f);

            for (int i2 = l; i2 <= k; ++i2) {
                for (int j2 = i1; j2 <= k1; ++j2) {
                    for (int k2 = j1; k2 <= l1; ++k2) {
                        int l2 = this.h.a(i2, j2, k2);

                        if (l2 > 0) {
                            Block.n[l2].a(this.h, i2, j2, k2, this);
                        }
                    }
                }
            }

            this.M *= 0.4F;
            boolean flag2 = this.o();

            if (this.h.c(this.v)) {
                this.b(1);
                if (!flag2) {
                    ++this.U;
                    if (this.U == 0) {
                        this.U = 300;
                    }
                }
            } else if (this.U <= 0) {
                this.U = -this.T;
            }

            if (flag2 && this.U > 0) {
                this.h.a(this, "random.fizz", 0.7F, 1.6F + (this.R.nextFloat() - this.R.nextFloat()) * 0.4F);
                this.U = -this.T;
            }
        }
    }

    public boolean m() {
        return false;
    }

    public AxisAlignedBB n() {
        return null;
    }

    protected void b(int i) {
        this.a((Entity) null, i);
    }

    protected void a(float f) {}

    public boolean o() {
        return this.h.a(this.v.b(0.0D, -0.4000000059604645D, 0.0D), Material.f, this);
    }

    public boolean a(Material material) {
        double d0 = this.m + (double) this.p();
        int i = MathHelper.b(this.l);
        int j = MathHelper.d((float) MathHelper.b(d0));
        int k = MathHelper.b(this.n);
        int l = this.h.a(i, j, k);

        if (l != 0 && Block.n[l].bn == material) {
            float f = BlockFluids.b(this.h.b(i, j, k)) - 0.11111111F;
            float f1 = (float) (j + 1) - f;

            return d0 < (double) f1;
        } else {
            return false;
        }
    }

    protected float p() {
        return 0.0F;
    }

    public boolean q() {
        return this.h.a(this.v.b(0.0D, -0.4000000059604645D, 0.0D), Material.g);
    }

    public void a(float f, float f1, float f2) {
        float f3 = MathHelper.c(f * f + f1 * f1);

        if (f3 >= 0.01F) {
            if (f3 < 1.0F) {
                f3 = 1.0F;
            }

            f3 = f2 / f3;
            f *= f3;
            f1 *= f3;
            float f4 = MathHelper.a(this.r * 3.1415927F / 180.0F);
            float f5 = MathHelper.b(this.r * 3.1415927F / 180.0F);

            this.o += (double) (f * f5 - f1 * f4);
            this.q += (double) (f1 * f5 + f * f4);
        }
    }

    public float b(float f) {
        int i = MathHelper.b(this.l);
        double d0 = (this.v.e - this.v.b) * 0.66D;
        int j = MathHelper.b(this.m - (double) this.C + d0);
        int k = MathHelper.b(this.n);

        return this.h.j(i, j, k);
    }

    public void b(double d0, double d1, double d2, float f, float f1) {
        this.i = this.l = d0;
        this.j = this.m = d1;
        this.k = this.n = d2;
        this.r = f;
        this.s = f1;
        this.M = 0.0F;
        double d3 = (double) (this.t - f);

        if (d3 < -180.0D) {
            this.t += 360.0F;
        }

        if (d3 >= 180.0D) {
            this.t -= 360.0F;
        }

        this.a(this.l, this.m, this.n);
    }

    public void c(double d0, double d1, double d2, float f, float f1) {
        this.i = this.l = d0;
        this.j = this.m = d1 + (double) this.C;
        this.k = this.n = d2;
        this.r = f;
        this.s = f1;
        this.a(this.l, this.m, this.n);
    }

    public float a(Entity entity) {
        float f = (float) (this.l - entity.l);
        float f1 = (float) (this.m - entity.m);
        float f2 = (float) (this.n - entity.n);

        return MathHelper.c(f * f + f1 * f1 + f2 * f2);
    }

    public double d(double d0, double d1, double d2) {
        double d3 = this.l - d0;
        double d4 = this.m - d1;
        double d5 = this.n - d2;

        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public double e(double d0, double d1, double d2) {
        double d3 = this.l - d0;
        double d4 = this.m - d1;
        double d5 = this.n - d2;

        return (double) MathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);
    }

    public double b(Entity entity) {
        double d0 = this.l - entity.l;
        double d1 = this.m - entity.m;
        double d2 = this.n - entity.n;

        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public void a(EntityHuman entityhuman) {}

    public void c(Entity entity) {
        if (entity.f != this && entity.g != this) {
            double d0 = entity.l - this.l;
            double d1 = entity.n - this.n;
            double d2 = MathHelper.a(d0, d1);

            if (d2 >= 0.009999999776482582D) {
                d2 = (double) MathHelper.a(d2);
                d0 /= d2;
                d1 /= d2;
                double d3 = 1.0D / d2;

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                d0 *= d3;
                d1 *= d3;
                d0 *= 0.05000000074505806D;
                d1 *= 0.05000000074505806D;
                d0 *= (double) (1.0F - this.P);
                d1 *= (double) (1.0F - this.P);
                this.f(-d0, 0.0D, -d1);
                entity.f(d0, 0.0D, d1);
            }
        }
    }

    public void f(double d0, double d1, double d2) {
        this.o += d0;
        this.p += d1;
        this.q += d2;
    }

    public boolean a(Entity entity, int i) {
        return false;
    }

    public boolean c_() {
        return false;
    }

    public boolean r() {
        return false;
    }

    public void b(Entity entity, int i) {}

    public boolean c(NBTTagCompound nbttagcompound) {
        String s = this.s();

        if (!this.B && s != null) {
            nbttagcompound.a("id", s);
            this.d(nbttagcompound);
            return true;
        } else {
            return false;
        }
    }

    public void d(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Pos", (NBTBase) this.a(new double[] { this.l, this.m, this.n}));
        nbttagcompound.a("Motion", (NBTBase) this.a(new double[] { this.o, this.p, this.q}));
        nbttagcompound.a("Rotation", (NBTBase) this.a(new float[] { this.r, this.s}));
        nbttagcompound.a("FallDistance", this.I);
        nbttagcompound.a("Fire", (short) this.U);
        nbttagcompound.a("Air", (short) this.Y);
        nbttagcompound.a("OnGround", this.w);
        this.a(nbttagcompound);
    }

    public void e(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = nbttagcompound.k("Pos");
        NBTTagList nbttaglist1 = nbttagcompound.k("Motion");
        NBTTagList nbttaglist2 = nbttagcompound.k("Rotation");

        this.a(0.0D, 0.0D, 0.0D);
        this.o = ((NBTTagDouble) nbttaglist1.a(0)).a;
        this.p = ((NBTTagDouble) nbttaglist1.a(1)).a;
        this.q = ((NBTTagDouble) nbttaglist1.a(2)).a;
        this.i = this.J = this.l = ((NBTTagDouble) nbttaglist.a(0)).a;
        this.j = this.K = this.m = ((NBTTagDouble) nbttaglist.a(1)).a;
        this.k = this.L = this.n = ((NBTTagDouble) nbttaglist.a(2)).a;
        this.t = this.r = ((NBTTagFloat) nbttaglist2.a(0)).a;
        this.u = this.s = ((NBTTagFloat) nbttaglist2.a(1)).a;
        this.I = nbttagcompound.f("FallDistance");
        this.U = nbttagcompound.c("Fire");
        this.Y = nbttagcompound.c("Air");
        this.w = nbttagcompound.l("OnGround");
        this.a(this.l, this.m, this.n);
        this.b(nbttagcompound);
    }

    protected final String s() {
        return EntityTypes.b(this);
    }

    protected abstract void b(NBTTagCompound nbttagcompound);

    protected abstract void a(NBTTagCompound nbttagcompound);

    protected NBTTagList a(double... adouble) {
        NBTTagList nbttaglist = new NBTTagList();
        double[] adouble1 = adouble;
        int i = adouble.length;

        for (int j = 0; j < i; ++j) {
            double d0 = adouble1[j];

            nbttaglist.a((NBTBase) (new NBTTagDouble(d0)));
        }

        return nbttaglist;
    }

    protected NBTTagList a(float... afloat) {
        NBTTagList nbttaglist = new NBTTagList();
        float[] afloat1 = afloat;
        int i = afloat.length;

        for (int j = 0; j < i; ++j) {
            float f = afloat1[j];

            nbttaglist.a((NBTBase) (new NBTTagFloat(f)));
        }

        return nbttaglist;
    }

    public EntityItem a(int i, int j) {
        return this.a(i, j, 0.0F);
    }

    public EntityItem a(int i, int j, float f) {
        EntityItem entityitem = new EntityItem(this.h, this.l, this.m + (double) f, this.n, new ItemStack(i, j));

        entityitem.ad = 10;
        this.h.a((Entity) entityitem);
        return entityitem;
    }

    public boolean t() {
        return !this.B;
    }

    public boolean u() {
        int i = MathHelper.b(this.l);
        int j = MathHelper.b(this.m + (double) this.p());
        int k = MathHelper.b(this.n);

        return this.h.d(i, j, k);
    }

    public AxisAlignedBB d(Entity entity) {
        return null;
    }

    public void v() {
        if (this.g.B) {
            this.g = null;
        } else {
            this.o = 0.0D;
            this.p = 0.0D;
            this.q = 0.0D;
            this.b_();
            this.g.w();
            this.af += (double) (this.g.r - this.g.t);

            for (this.ae += (double) (this.g.s - this.g.u); this.af >= 180.0D; this.af -= 360.0D) {
                ;
            }

            while (this.af < -180.0D) {
                this.af += 360.0D;
            }

            while (this.ae >= 180.0D) {
                this.ae -= 360.0D;
            }

            while (this.ae < -180.0D) {
                this.ae += 360.0D;
            }

            double d0 = this.af * 0.5D;
            double d1 = this.ae * 0.5D;
            float f = 10.0F;

            if (d0 > (double) f) {
                d0 = (double) f;
            }

            if (d0 < (double) (-f)) {
                d0 = (double) (-f);
            }

            if (d1 > (double) f) {
                d1 = (double) f;
            }

            if (d1 < (double) (-f)) {
                d1 = (double) (-f);
            }

            this.af -= d0;
            this.ae -= d1;
            this.r = (float) ((double) this.r + d0);
            this.s = (float) ((double) this.s + d1);
        }
    }

    protected void w() {
        this.f.a(this.l, this.m + this.h() + this.f.x(), this.n);
    }

    public double x() {
        return (double) this.C;
    }

    public double h() {
        return (double) this.E * 0.75D;
    }

    public void e(Entity entity) {
        this.ae = 0.0D;
        this.af = 0.0D;
        if (this.g == entity) {
            this.g.f = null;
            this.g = null;
            this.c(entity.l, entity.v.b + (double) entity.E, entity.n, this.r, this.s);
        } else {
            if (this.g != null) {
                this.g.f = null;
            }

            if (entity.f != null) {
                entity.f.g = null;
            }

            this.g = entity;
            entity.f = this;
        }
    }
}
