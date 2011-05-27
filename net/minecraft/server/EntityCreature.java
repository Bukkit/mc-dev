package net.minecraft.server;

public class EntityCreature extends EntityLiving {

    private PathEntity a;
    protected Entity f;
    protected boolean aj = false;

    public EntityCreature(World world) {
        super(world);
    }

    protected void c() {
        this.aj = false;
        float f = 16.0F;

        if (this.f == null) {
            this.f = this.k();
            if (this.f != null) {
                this.a = this.l.a(this, this.f, f);
            }
        } else if (!this.f.x()) {
            this.f = null;
        } else {
            float f1 = this.f.a((Entity) this);

            if (this.i(this.f)) {
                this.a(this.f, f1);
            }
        }

        if (!this.aj && this.f != null && (this.a == null || this.W.nextInt(20) == 0)) {
            this.a = this.l.a(this, this.f, f);
        } else if (this.a == null && this.W.nextInt(80) == 0 || this.W.nextInt(80) == 0) {
            boolean flag = false;
            int i = -1;
            int j = -1;
            int k = -1;
            float f2 = -99999.0F;

            for (int l = 0; l < 10; ++l) {
                int i1 = MathHelper.b(this.p + (double) this.W.nextInt(13) - 6.0D);
                int j1 = MathHelper.b(this.q + (double) this.W.nextInt(7) - 3.0D);
                int k1 = MathHelper.b(this.r + (double) this.W.nextInt(13) - 6.0D);
                float f3 = this.a(i1, j1, k1);

                if (f3 > f2) {
                    f2 = f3;
                    i = i1;
                    j = j1;
                    k = k1;
                    flag = true;
                }
            }

            if (flag) {
                this.a = this.l.a(this, i, j, k, 10.0F);
            }
        }

        int l1 = MathHelper.b(this.z.b);
        boolean flag1 = this.r();
        boolean flag2 = this.t();

        this.w = 0.0F;
        if (this.a != null && this.W.nextInt(100) != 0) {
            Vec3D vec3d = this.a.a(this);
            double d0 = (double) (this.I * 2.0F);

            while (vec3d != null && vec3d.d(this.p, vec3d.b, this.r) < d0 * d0) {
                this.a.a();
                if (this.a.b()) {
                    vec3d = null;
                    this.a = null;
                } else {
                    vec3d = this.a.a(this);
                }
            }

            this.br = false;
            if (vec3d != null) {
                double d1 = vec3d.a - this.p;
                double d2 = vec3d.c - this.r;
                double d3 = vec3d.b - (double) l1;
                float f4 = (float) (Math.atan2(d2, d1) * 180.0D / 3.1415927410125732D) - 90.0F;
                float f5 = f4 - this.v;

                for (this.bp = this.bt; f5 < -180.0F; f5 += 360.0F) {
                    ;
                }

                while (f5 >= 180.0F) {
                    f5 -= 360.0F;
                }

                if (f5 > 30.0F) {
                    f5 = 30.0F;
                }

                if (f5 < -30.0F) {
                    f5 = -30.0F;
                }

                this.v += f5;
                if (this.aj && this.f != null) {
                    double d4 = this.f.p - this.p;
                    double d5 = this.f.r - this.r;
                    float f6 = this.v;

                    this.v = (float) (Math.atan2(d5, d4) * 180.0D / 3.1415927410125732D) - 90.0F;
                    f5 = (f6 - this.v + 90.0F) * 3.1415927F / 180.0F;
                    this.bo = -MathHelper.a(f5) * this.bp * 1.0F;
                    this.bp = MathHelper.b(f5) * this.bp * 1.0F;
                }

                if (d3 > 0.0D) {
                    this.br = true;
                }
            }

            if (this.f != null) {
                this.b(this.f, 30.0F);
            }

            if (this.B) {
                this.br = true;
            }

            if (this.W.nextFloat() < 0.8F && (flag1 || flag2)) {
                this.br = true;
            }
        } else {
            super.c();
            this.a = null;
        }
    }

    protected void a(Entity entity, float f) {}

    protected float a(int i, int j, int k) {
        return 0.0F;
    }

    protected Entity k() {
        return null;
    }

    public boolean a() {
        int i = MathHelper.b(this.p);
        int j = MathHelper.b(this.z.b);
        int k = MathHelper.b(this.r);

        return super.a() && this.a(i, j, k) >= 0.0F;
    }
}
