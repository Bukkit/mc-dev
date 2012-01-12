package net.minecraft.server;

public class ControllerMove {

    private EntityLiving a;
    private double b;
    private double c;
    private double d;
    private float e;
    private boolean f = false;

    public ControllerMove(EntityLiving entityliving, float f) {
        this.a = entityliving;
        this.b = entityliving.locX;
        this.c = entityliving.locY;
        this.d = entityliving.locZ;
        this.e = f;
    }

    public void a(double d0, double d1, double d2) {
        this.b = d0;
        this.c = d1;
        this.d = d2;
        this.f = true;
    }

    public void a(float f) {
        this.e = f;
    }

    public void a() {
        this.a.d(0.0F);
        if (this.f) {
            this.f = false;
            int i = MathHelper.floor(this.a.boundingBox.b + 0.5D);
            double d0 = this.b - this.a.locX;
            double d1 = this.d - this.a.locZ;
            double d2 = this.c - (double) i;
            float f = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;

            float f1;

            for (f1 = f - this.a.yaw; f1 < -180.0F; f1 += 360.0F) {
                ;
            }

            while (f1 >= 180.0F) {
                f1 -= 360.0F;
            }

            if (f1 > 30.0F) {
                f1 = 30.0F;
            }

            if (f1 < -30.0F) {
                f1 = -30.0F;
            }

            this.a.yaw += f1;
            this.a.d(this.e);
            this.a.e(d2 > 0.0D);
        }
    }
}
