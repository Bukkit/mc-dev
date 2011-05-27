package net.minecraft.server;

public class EntityFlying extends EntityLiving {

    public EntityFlying(World world) {
        super(world);
    }

    protected void a(float f) {}

    public void c(float f, float f1) {
        if (this.v()) {
            this.a(f, f1, 0.02F);
            this.c(this.s, this.t, this.u);
            this.s *= 0.800000011920929D;
            this.t *= 0.800000011920929D;
            this.u *= 0.800000011920929D;
        } else if (this.x()) {
            this.a(f, f1, 0.02F);
            this.c(this.s, this.t, this.u);
            this.s *= 0.5D;
            this.t *= 0.5D;
            this.u *= 0.5D;
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

            this.c(this.s, this.t, this.u);
            this.s *= (double) f2;
            this.t *= (double) f2;
            this.u *= (double) f2;
        }

        this.bl = this.bm;
        double d0 = this.p - this.m;
        double d1 = this.r - this.o;
        float f4 = MathHelper.a(d0 * d0 + d1 * d1) * 4.0F;

        if (f4 > 1.0F) {
            f4 = 1.0F;
        }

        this.bm += (f4 - this.bm) * 0.4F;
        this.bn += this.bm;
    }

    public boolean m() {
        return false;
    }
}
