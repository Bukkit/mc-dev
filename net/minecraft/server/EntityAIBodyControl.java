package net.minecraft.server;

public class EntityAIBodyControl {

    private EntityLiving entity;
    private int b = 0;
    private float c = 0.0F;

    public EntityAIBodyControl(EntityLiving entityliving) {
        this.entity = entityliving;
    }

    public void a() {
        double d0 = this.entity.locX - this.entity.lastX;
        double d1 = this.entity.locZ - this.entity.lastZ;

        if (d0 * d0 + d1 * d1 > 2.500000277905201E-7D) {
            this.entity.aq = this.entity.yaw;
            this.entity.as = this.a(this.entity.aq, this.entity.as, 75.0F);
            this.c = this.entity.as;
            this.b = 0;
        } else {
            float f = 75.0F;

            if (Math.abs(this.entity.as - this.c) > 15.0F) {
                this.b = 0;
                this.c = this.entity.as;
            } else {
                ++this.b;
                if (this.b > 10) {
                    f = Math.max(1.0F - (float) (this.b - 10) / 10.0F, 0.0F) * 75.0F;
                }
            }

            this.entity.aq = this.a(this.entity.as, this.entity.aq, f);
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
