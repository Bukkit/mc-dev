package net.minecraft.server;

public class Navigation {

    private EntityInsentient a;
    private World b;
    private PathEntity c;
    private double d;
    private AttributeInstance e;
    private boolean f;
    private int g;
    private int h;
    private Vec3D i = Vec3D.a(0.0D, 0.0D, 0.0D);
    private boolean j = true;
    private boolean k;
    private boolean l;
    private boolean m;

    public Navigation(EntityInsentient entityinsentient, World world) {
        this.a = entityinsentient;
        this.b = world;
        this.e = entityinsentient.getAttributeInstance(GenericAttributes.b);
    }

    public void a(boolean flag) {
        this.l = flag;
    }

    public boolean a() {
        return this.l;
    }

    public void b(boolean flag) {
        this.k = flag;
    }

    public void c(boolean flag) {
        this.j = flag;
    }

    public boolean c() {
        return this.k;
    }

    public void d(boolean flag) {
        this.f = flag;
    }

    public void a(double d0) {
        this.d = d0;
    }

    public void e(boolean flag) {
        this.m = flag;
    }

    public float d() {
        return (float) this.e.getValue();
    }

    public PathEntity a(double d0, double d1, double d2) {
        return !this.l() ? null : this.b.a(this.a, MathHelper.floor(d0), (int) d1, MathHelper.floor(d2), this.d(), this.j, this.k, this.l, this.m);
    }

    public boolean a(double d0, double d1, double d2, double d3) {
        PathEntity pathentity = this.a((double) MathHelper.floor(d0), (double) ((int) d1), (double) MathHelper.floor(d2));

        return this.a(pathentity, d3);
    }

    public PathEntity a(Entity entity) {
        return !this.l() ? null : this.b.findPath(this.a, entity, this.d(), this.j, this.k, this.l, this.m);
    }

    public boolean a(Entity entity, double d0) {
        PathEntity pathentity = this.a(entity);

        return pathentity != null ? this.a(pathentity, d0) : false;
    }

    public boolean a(PathEntity pathentity, double d0) {
        if (pathentity == null) {
            this.c = null;
            return false;
        } else {
            if (!pathentity.a(this.c)) {
                this.c = pathentity;
            }

            if (this.f) {
                this.n();
            }

            if (this.c.d() == 0) {
                return false;
            } else {
                this.d = d0;
                Vec3D vec3d = this.j();

                this.h = this.g;
                this.i.c = vec3d.c;
                this.i.d = vec3d.d;
                this.i.e = vec3d.e;
                return true;
            }
        }
    }

    public PathEntity e() {
        return this.c;
    }

    public void f() {
        ++this.g;
        if (!this.g()) {
            if (this.l()) {
                this.i();
            }

            if (!this.g()) {
                Vec3D vec3d = this.c.a((Entity) this.a);

                if (vec3d != null) {
                    this.a.getControllerMove().a(vec3d.c, vec3d.d, vec3d.e, this.d);
                }
            }
        }
    }

    private void i() {
        Vec3D vec3d = this.j();
        int i = this.c.d();

        for (int j = this.c.e(); j < this.c.d(); ++j) {
            if (this.c.a(j).b != (int) vec3d.d) {
                i = j;
                break;
            }
        }

        float f = this.a.width * this.a.width;

        int k;

        for (k = this.c.e(); k < i; ++k) {
            if (vec3d.distanceSquared(this.c.a(this.a, k)) < (double) f) {
                this.c.c(k + 1);
            }
        }

        k = MathHelper.f(this.a.width);
        int l = (int) this.a.length + 1;
        int i1 = k;

        for (int j1 = i - 1; j1 >= this.c.e(); --j1) {
            if (this.a(vec3d, this.c.a(this.a, j1), k, l, i1)) {
                this.c.c(j1);
                break;
            }
        }

        if (this.g - this.h > 100) {
            if (vec3d.distanceSquared(this.i) < 2.25D) {
                this.h();
            }

            this.h = this.g;
            this.i.c = vec3d.c;
            this.i.d = vec3d.d;
            this.i.e = vec3d.e;
        }
    }

    public boolean g() {
        return this.c == null || this.c.b();
    }

    public void h() {
        this.c = null;
    }

    private Vec3D j() {
        return this.b.getVec3DPool().create(this.a.locX, (double) this.k(), this.a.locZ);
    }

    private int k() {
        if (this.a.M() && this.m) {
            int i = (int) this.a.boundingBox.b;
            Block block = this.b.getType(MathHelper.floor(this.a.locX), i, MathHelper.floor(this.a.locZ));
            int j = 0;

            do {
                if (block != Blocks.WATER && block != Blocks.STATIONARY_WATER) {
                    return i;
                }

                ++i;
                block = this.b.getType(MathHelper.floor(this.a.locX), i, MathHelper.floor(this.a.locZ));
                ++j;
            } while (j <= 16);

            return (int) this.a.boundingBox.b;
        } else {
            return (int) (this.a.boundingBox.b + 0.5D);
        }
    }

    private boolean l() {
        return this.a.onGround || this.m && this.m();
    }

    private boolean m() {
        return this.a.M() || this.a.P();
    }

    private void n() {
        if (!this.b.i(MathHelper.floor(this.a.locX), (int) (this.a.boundingBox.b + 0.5D), MathHelper.floor(this.a.locZ))) {
            for (int i = 0; i < this.c.d(); ++i) {
                PathPoint pathpoint = this.c.a(i);

                if (this.b.i(pathpoint.a, pathpoint.b, pathpoint.c)) {
                    this.c.b(i - 1);
                    return;
                }
            }
        }
    }

    private boolean a(Vec3D vec3d, Vec3D vec3d1, int i, int j, int k) {
        int l = MathHelper.floor(vec3d.c);
        int i1 = MathHelper.floor(vec3d.e);
        double d0 = vec3d1.c - vec3d.c;
        double d1 = vec3d1.e - vec3d.e;
        double d2 = d0 * d0 + d1 * d1;

        if (d2 < 1.0E-8D) {
            return false;
        } else {
            double d3 = 1.0D / Math.sqrt(d2);

            d0 *= d3;
            d1 *= d3;
            i += 2;
            k += 2;
            if (!this.a(l, (int) vec3d.d, i1, i, j, k, vec3d, d0, d1)) {
                return false;
            } else {
                i -= 2;
                k -= 2;
                double d4 = 1.0D / Math.abs(d0);
                double d5 = 1.0D / Math.abs(d1);
                double d6 = (double) (l * 1) - vec3d.c;
                double d7 = (double) (i1 * 1) - vec3d.e;

                if (d0 >= 0.0D) {
                    ++d6;
                }

                if (d1 >= 0.0D) {
                    ++d7;
                }

                d6 /= d0;
                d7 /= d1;
                int j1 = d0 < 0.0D ? -1 : 1;
                int k1 = d1 < 0.0D ? -1 : 1;
                int l1 = MathHelper.floor(vec3d1.c);
                int i2 = MathHelper.floor(vec3d1.e);
                int j2 = l1 - l;
                int k2 = i2 - i1;

                do {
                    if (j2 * j1 <= 0 && k2 * k1 <= 0) {
                        return true;
                    }

                    if (d6 < d7) {
                        d6 += d4;
                        l += j1;
                        j2 = l1 - l;
                    } else {
                        d7 += d5;
                        i1 += k1;
                        k2 = i2 - i1;
                    }
                } while (this.a(l, (int) vec3d.d, i1, i, j, k, vec3d, d0, d1));

                return false;
            }
        }
    }

    private boolean a(int i, int j, int k, int l, int i1, int j1, Vec3D vec3d, double d0, double d1) {
        int k1 = i - l / 2;
        int l1 = k - j1 / 2;

        if (!this.b(k1, j, l1, l, i1, j1, vec3d, d0, d1)) {
            return false;
        } else {
            for (int i2 = k1; i2 < k1 + l; ++i2) {
                for (int j2 = l1; j2 < l1 + j1; ++j2) {
                    double d2 = (double) i2 + 0.5D - vec3d.c;
                    double d3 = (double) j2 + 0.5D - vec3d.e;

                    if (d2 * d0 + d3 * d1 >= 0.0D) {
                        Block block = this.b.getType(i2, j - 1, j2);
                        Material material = block.getMaterial();

                        if (material == Material.AIR) {
                            return false;
                        }

                        if (material == Material.WATER && !this.a.M()) {
                            return false;
                        }

                        if (material == Material.LAVA) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }
    }

    private boolean b(int i, int j, int k, int l, int i1, int j1, Vec3D vec3d, double d0, double d1) {
        for (int k1 = i; k1 < i + l; ++k1) {
            for (int l1 = j; l1 < j + i1; ++l1) {
                for (int i2 = k; i2 < k + j1; ++i2) {
                    double d2 = (double) k1 + 0.5D - vec3d.c;
                    double d3 = (double) i2 + 0.5D - vec3d.e;

                    if (d2 * d0 + d3 * d1 >= 0.0D) {
                        Block block = this.b.getType(k1, l1, i2);

                        if (!block.b(this.b, k1, l1, i2)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
