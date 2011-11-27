package net.minecraft.server;

import java.util.Random;

public class TileEntityEnchantTable extends TileEntity {

    public int a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    private static Random r = new Random();

    public TileEntityEnchantTable() {}

    public void l_() {
        super.l_();
        this.g = this.f;
        this.i = this.h;
        EntityHuman entityhuman = this.world.findNearbyPlayer((double) ((float) this.x + 0.5F), (double) ((float) this.y + 0.5F), (double) ((float) this.z + 0.5F), 3.0D);

        if (entityhuman != null) {
            double d0 = entityhuman.locX - (double) ((float) this.x + 0.5F);
            double d1 = entityhuman.locZ - (double) ((float) this.z + 0.5F);

            this.j = (float) Math.atan2(d1, d0);
            this.f += 0.1F;
            if (this.f < 0.5F || r.nextInt(40) == 0) {
                float f = this.d;

                do {
                    this.d += (float) (r.nextInt(4) - r.nextInt(4));
                } while (f == this.d);
            }
        } else {
            this.j += 0.02F;
            this.f -= 0.1F;
        }

        while (this.h >= 3.1415927F) {
            this.h -= 6.2831855F;
        }

        while (this.h < -3.1415927F) {
            this.h += 6.2831855F;
        }

        while (this.j >= 3.1415927F) {
            this.j -= 6.2831855F;
        }

        while (this.j < -3.1415927F) {
            this.j += 6.2831855F;
        }

        float f1;

        for (f1 = this.j - this.h; f1 >= 3.1415927F; f1 -= 6.2831855F) {
            ;
        }

        while (f1 < -3.1415927F) {
            f1 += 6.2831855F;
        }

        this.h += f1 * 0.4F;
        if (this.f < 0.0F) {
            this.f = 0.0F;
        }

        if (this.f > 1.0F) {
            this.f = 1.0F;
        }

        ++this.a;
        this.c = this.b;
        float f2 = (this.d - this.b) * 0.4F;
        float f3 = 0.2F;

        if (f2 < -f3) {
            f2 = -f3;
        }

        if (f2 > f3) {
            f2 = f3;
        }

        this.e += (f2 - this.e) * 0.9F;
        this.b += this.e;
    }
}
