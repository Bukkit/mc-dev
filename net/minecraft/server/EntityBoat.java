package net.minecraft.server;

import java.util.List;

public class EntityBoat extends Entity {

    public int a;
    public int b;
    public int c;
    private int d;
    private double e;
    private double f;
    private double ak;
    private double al;
    private double am;

    public EntityBoat(World world) {
        super(world);
        this.a = 0;
        this.b = 0;
        this.c = 1;
        this.i = true;
        this.a(1.5F, 0.6F);
        this.H = this.J / 2.0F;
        this.M = false;
    }

    protected void a() {}

    public AxisAlignedBB d(Entity entity) {
        return entity.z;
    }

    public AxisAlignedBB u() {
        return this.z;
    }

    public boolean z() {
        return true;
    }

    public EntityBoat(World world, double d0, double d1, double d2) {
        this(world);
        this.a(d0, d1 + (double) this.H, d2);
        this.s = 0.0D;
        this.t = 0.0D;
        this.u = 0.0D;
        this.m = d0;
        this.n = d1;
        this.o = d2;
    }

    public double k() {
        return (double) this.J * 0.0D - 0.30000001192092896D;
    }

    public boolean a(Entity entity, int i) {
        if (!this.l.z && !this.G) {
            this.c = -this.c;
            this.b = 10;
            this.a += i * 10;
            this.y();
            if (this.a > 40) {
                int j;

                for (j = 0; j < 3; ++j) {
                    this.a(Block.WOOD.bi, 1, 0.0F);
                }

                for (j = 0; j < 2; ++j) {
                    this.a(Item.STICK.ba, 1, 0.0F);
                }

                this.q();
            }

            return true;
        } else {
            return true;
        }
    }

    public boolean c_() {
        return !this.G;
    }

    public void b_() {
        super.b_();
        if (this.b > 0) {
            --this.b;
        }

        if (this.a > 0) {
            --this.a;
        }

        this.m = this.p;
        this.n = this.q;
        this.o = this.r;
        byte b0 = 5;
        double d0 = 0.0D;

        for (int i = 0; i < b0; ++i) {
            double d1 = this.z.b + (this.z.e - this.z.b) * (double) (i + 0) / (double) b0 - 0.125D;
            double d2 = this.z.b + (this.z.e - this.z.b) * (double) (i + 1) / (double) b0 - 0.125D;
            AxisAlignedBB axisalignedbb = AxisAlignedBB.b(this.z.a, d1, this.z.c, this.z.d, d2, this.z.f);

            if (this.l.b(axisalignedbb, Material.f)) {
                d0 += 1.0D / (double) b0;
            }
        }

        double d3;
        double d4;
        double d5;
        double d6;

        if (this.l.z) {
            if (this.d > 0) {
                d3 = this.p + (this.e - this.p) / (double) this.d;
                d4 = this.q + (this.f - this.q) / (double) this.d;
                d5 = this.r + (this.ak - this.r) / (double) this.d;

                for (d6 = this.al - (double) this.v; d6 < -180.0D; d6 += 360.0D) {
                    ;
                }

                while (d6 >= 180.0D) {
                    d6 -= 360.0D;
                }

                this.v = (float) ((double) this.v + d6 / (double) this.d);
                this.w = (float) ((double) this.w + (this.am - (double) this.w) / (double) this.d);
                --this.d;
                this.a(d3, d4, d5);
                this.b(this.v, this.w);
            } else {
                d3 = this.p + this.s;
                d4 = this.q + this.t;
                d5 = this.r + this.u;
                this.a(d3, d4, d5);
                if (this.A) {
                    this.s *= 0.5D;
                    this.t *= 0.5D;
                    this.u *= 0.5D;
                }

                this.s *= 0.9900000095367432D;
                this.t *= 0.949999988079071D;
                this.u *= 0.9900000095367432D;
            }
        } else {
            d3 = d0 * 2.0D - 1.0D;
            this.t += 0.03999999910593033D * d3;
            if (this.j != null) {
                this.s += this.j.s * 0.2D;
                this.u += this.j.u * 0.2D;
            }

            d4 = 0.4D;
            if (this.s < -d4) {
                this.s = -d4;
            }

            if (this.s > d4) {
                this.s = d4;
            }

            if (this.u < -d4) {
                this.u = -d4;
            }

            if (this.u > d4) {
                this.u = d4;
            }

            if (this.A) {
                this.s *= 0.5D;
                this.t *= 0.5D;
                this.u *= 0.5D;
            }

            this.c(this.s, this.t, this.u);
            d5 = Math.sqrt(this.s * this.s + this.u * this.u);
            double d7;

            if (d5 > 0.15D) {
                d6 = Math.cos((double) this.v * 3.141592653589793D / 180.0D);
                d7 = Math.sin((double) this.v * 3.141592653589793D / 180.0D);

                for (int j = 0; (double) j < 1.0D + d5 * 60.0D; ++j) {
                    double d8 = (double) (this.W.nextFloat() * 2.0F - 1.0F);
                    double d9 = (double) (this.W.nextInt(2) * 2 - 1) * 0.7D;
                    double d10;
                    double d11;

                    if (this.W.nextBoolean()) {
                        d10 = this.p - d6 * d8 * 0.8D + d7 * d9;
                        d11 = this.r - d7 * d8 * 0.8D - d6 * d9;
                        this.l.a("splash", d10, this.q - 0.125D, d11, this.s, this.t, this.u);
                    } else {
                        d10 = this.p + d6 + d7 * d8 * 0.7D;
                        d11 = this.r + d7 - d6 * d8 * 0.7D;
                        this.l.a("splash", d10, this.q - 0.125D, d11, this.s, this.t, this.u);
                    }
                }
            }

            if (this.B && d5 > 0.15D) {
                if (!this.l.z) {
                    this.q();

                    int k;

                    for (k = 0; k < 3; ++k) {
                        this.a(Block.WOOD.bi, 1, 0.0F);
                    }

                    for (k = 0; k < 2; ++k) {
                        this.a(Item.STICK.ba, 1, 0.0F);
                    }
                }
            } else {
                this.s *= 0.9900000095367432D;
                this.t *= 0.949999988079071D;
                this.u *= 0.9900000095367432D;
            }

            this.w = 0.0F;
            d6 = (double) this.v;
            d7 = this.m - this.p;
            double d12 = this.o - this.r;

            if (d7 * d7 + d12 * d12 > 0.0010D) {
                d6 = (double) ((float) (Math.atan2(d12, d7) * 180.0D / 3.141592653589793D));
            }

            double d13;

            for (d13 = d6 - (double) this.v; d13 >= 180.0D; d13 -= 360.0D) {
                ;
            }

            while (d13 < -180.0D) {
                d13 += 360.0D;
            }

            if (d13 > 20.0D) {
                d13 = 20.0D;
            }

            if (d13 < -20.0D) {
                d13 = -20.0D;
            }

            this.v = (float) ((double) this.v + d13);
            this.b(this.v, this.w);
            List list = this.l.b((Entity) this, this.z.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (list != null && list.size() > 0) {
                for (int l = 0; l < list.size(); ++l) {
                    Entity entity = (Entity) list.get(l);

                    if (entity != this.j && entity.z() && entity instanceof EntityBoat) {
                        entity.c((Entity) this);
                    }
                }
            }

            if (this.j != null && this.j.G) {
                this.j = null;
            }
        }
    }

    public void E() {
        if (this.j != null) {
            double d0 = Math.cos((double) this.v * 3.141592653589793D / 180.0D) * 0.4D;
            double d1 = Math.sin((double) this.v * 3.141592653589793D / 180.0D) * 0.4D;

            this.j.a(this.p + d0, this.q + this.k() + this.j.F(), this.r + d1);
        }
    }

    protected void a(NBTTagCompound nbttagcompound) {}

    protected void b(NBTTagCompound nbttagcompound) {}

    public boolean a(EntityHuman entityhuman) {
        if (this.j != null && this.j instanceof EntityHuman && this.j != entityhuman) {
            return true;
        } else {
            if (!this.l.z) {
                entityhuman.e(this);
            }

            return true;
        }
    }
}
