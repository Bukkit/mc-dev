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
            this.entity.V = this.entity.yaw;
            this.entity.X = this.a(this.entity.V, this.entity.X, 75.0F);
            this.c = this.entity.X;
            this.b = 0;
        } else {
            float f = 75.0F;

            if (Math.abs(this.entity.X - this.c) > 15.0F) {
                this.b = 0;
                this.c = this.entity.X;
            } else {
                ++this.b;
                if (this.b > 10) {
                    f = Math.max(1.0F - (float) (this.b - 10) / 10.0F, 0.0F) * 75.0F;
                }
            }

            this.entity.V = this.a(this.entity.X, this.entity.V, f);
        }
    }

    private float a(float f, float f1, float f2) {
        float f3;

        for (f3 = f - f1; f3 < -180.0F; f3 += 360.0F) {
            ;
        }

        while (f3 >= 180.0F) {
            f3 -= 360.0F;
        }

        if (f3 < -f2) {
            f3 = -f2;
        }

        if (f3 >= f2) {
            f3 = f2;
        }

        return f - f3;
    }
}
