package net.minecraft.server;

public class EntityAIBodyControl {

    private EntityLiving entity;
    private int b;
    private float c;

    public EntityAIBodyControl(EntityLiving entityliving) {
        this.entity = entityliving;
    }

    public void a() {
        double d0 = this.entity.locX - this.entity.lastX;
        double d1 = this.entity.locZ - this.entity.lastZ;

        if (d0 * d0 + d1 * d1 > 2.500000277905201E-7D) {
            this.entity.aN = this.entity.yaw;
            this.entity.aP = this.a(this.entity.aN, this.entity.aP, 75.0F);
            this.c = this.entity.aP;
            this.b = 0;
        } else {
            float f = 75.0F;

            if (Math.abs(this.entity.aP - this.c) > 15.0F) {
                this.b = 0;
                this.c = this.entity.aP;
            } else {
                ++this.b;
                boolean flag = true;

                if (this.b > 10) {
                    f = Math.max(1.0F - (float) (this.b - 10) / 10.0F, 0.0F) * 75.0F;
                }
            }

            this.entity.aN = this.a(this.entity.aP, this.entity.aN, f);
        }
    }

    private float a(float f, float f1, float f2) {
        float f3 = MathHelper.g(f - f1);

        if (f3 < -f2) {
            f3 = -f2;
        }

        if (f3 >= f2) {
            f3 = f2;
        }

        return f - f3;
    }
}
