package net.minecraft.server;

public class EntityGhast extends EntityFlying implements IMonster {

    public int a = 0;
    public double b;
    public double c;
    public double d;
    private Entity ai = null;
    private int aj = 0;
    public int e = 0;
    public int f = 0;

    public EntityGhast(World world) {
        super(world);
        this.aF = "/mob/ghast.png";
        this.a(4.0F, 4.0F);
        this.ad = true;
    }

    protected void c() {
        if (this.l.k == 0) {
            this.l();
        }

        this.e = this.f;
        double d0 = this.b - this.p;
        double d1 = this.c - this.q;
        double d2 = this.d - this.r;
        double d3 = (double) MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        if (d3 < 1.0D || d3 > 60.0D) {
            this.b = this.p + (double) ((this.V.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.c = this.q + (double) ((this.V.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.d = this.r + (double) ((this.V.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.a-- <= 0) {
            this.a += this.V.nextInt(5) + 2;
            if (this.a(this.b, this.c, this.d, d3)) {
                this.s += d0 / d3 * 0.1D;
                this.t += d1 / d3 * 0.1D;
                this.u += d2 / d3 * 0.1D;
            } else {
                this.b = this.p;
                this.c = this.q;
                this.d = this.r;
            }
        }

        if (this.ai != null && this.ai.F) {
            this.ai = null;
        }

        if (this.ai == null || this.aj-- <= 0) {
            this.ai = this.l.a(this, 100.0D);
            if (this.ai != null) {
                this.aj = 20;
            }
        }

        double d4 = 64.0D;

        if (this.ai != null && this.ai.b((Entity) this) < d4 * d4) {
            double d5 = this.ai.p - this.p;
            double d6 = this.ai.z.b + (double) (this.ai.I / 2.0F) - (this.q + (double) (this.I / 2.0F));
            double d7 = this.ai.r - this.r;

            this.ay = this.v = -((float) Math.atan2(d5, d7)) * 180.0F / 3.1415927F;
            if (this.g(this.ai)) {
                if (this.f == 10) {
                    this.l.a(this, "mob.ghast.charge", this.h(), (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
                }

                ++this.f;
                if (this.f == 20) {
                    this.l.a(this, "mob.ghast.fireball", this.h(), (this.V.nextFloat() - this.V.nextFloat()) * 0.2F + 1.0F);
                    EntityFireball entityfireball = new EntityFireball(this.l, this, d5, d6, d7);
                    double d8 = 4.0D;
                    Vec3D vec3d = this.d(1.0F);

                    entityfireball.p = this.p + vec3d.a * d8;
                    entityfireball.q = this.q + (double) (this.I / 2.0F) + 0.5D;
                    entityfireball.r = this.r + vec3d.c * d8;
                    this.l.a((Entity) entityfireball);
                    this.f = -40;
                }
            } else if (this.f > 0) {
                --this.f;
            }
        } else {
            this.ay = this.v = -((float) Math.atan2(this.s, this.u)) * 180.0F / 3.1415927F;
            if (this.f > 0) {
                --this.f;
            }
        }

        this.aF = this.f > 10 ? "/mob/ghast_fire.png" : "/mob/ghast.png";
    }

    private boolean a(double d0, double d1, double d2, double d3) {
        double d4 = (this.b - this.p) / d3;
        double d5 = (this.c - this.q) / d3;
        double d6 = (this.d - this.r) / d3;
        AxisAlignedBB axisalignedbb = this.z.b();

        for (int i = 1; (double) i < d3; ++i) {
            axisalignedbb.d(d4, d5, d6);
            if (this.l.a((Entity) this, axisalignedbb).size() > 0) {
                return false;
            }
        }

        return true;
    }

    protected String d() {
        return "mob.ghast.moan";
    }

    protected String e() {
        return "mob.ghast.scream";
    }

    protected String f() {
        return "mob.ghast.death";
    }

    protected int g() {
        return Item.SULPHUR.aW;
    }

    protected float h() {
        return 10.0F;
    }

    public boolean a() {
        return this.V.nextInt(20) == 0 && super.a() && this.l.k > 0;
    }

    public int i() {
        return 1;
    }
}
