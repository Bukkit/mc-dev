package net.minecraft.server;

import java.util.Random;

public class TileEntityEnchantTable extends TileEntity {

    public int a;
    public float i;
    public float j;
    public float k;
    public float l;
    public float m;
    public float n;
    public float o;
    public float p;
    public float q;
    private static Random r = new Random();
    private String s;

    public TileEntityEnchantTable() {}

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.b()) {
            nbttagcompound.setString("CustomName", this.s);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.hasKeyOfType("CustomName", 8)) {
            this.s = nbttagcompound.getString("CustomName");
        }
    }

    public void h() {
        super.h();
        this.n = this.m;
        this.p = this.o;
        EntityHuman entityhuman = this.world.findNearbyPlayer((double) ((float) this.x + 0.5F), (double) ((float) this.y + 0.5F), (double) ((float) this.z + 0.5F), 3.0D);

        if (entityhuman != null) {
            double d0 = entityhuman.locX - (double) ((float) this.x + 0.5F);
            double d1 = entityhuman.locZ - (double) ((float) this.z + 0.5F);

            this.q = (float) Math.atan2(d1, d0);
            this.m += 0.1F;
            if (this.m < 0.5F || r.nextInt(40) == 0) {
                float f = this.k;

                do {
                    this.k += (float) (r.nextInt(4) - r.nextInt(4));
                } while (f == this.k);
            }
        } else {
            this.q += 0.02F;
            this.m -= 0.1F;
        }

        while (this.o >= 3.1415927F) {
            this.o -= 6.2831855F;
        }

        while (this.o < -3.1415927F) {
            this.o += 6.2831855F;
        }

        while (this.q >= 3.1415927F) {
            this.q -= 6.2831855F;
        }

        while (this.q < -3.1415927F) {
            this.q += 6.2831855F;
        }

        float f1;

        for (f1 = this.q - this.o; f1 >= 3.1415927F; f1 -= 6.2831855F) {
            ;
        }

        while (f1 < -3.1415927F) {
            f1 += 6.2831855F;
        }

        this.o += f1 * 0.4F;
        if (this.m < 0.0F) {
            this.m = 0.0F;
        }

        if (this.m > 1.0F) {
            this.m = 1.0F;
        }

        ++this.a;
        this.j = this.i;
        float f2 = (this.k - this.i) * 0.4F;
        float f3 = 0.2F;

        if (f2 < -f3) {
            f2 = -f3;
        }

        if (f2 > f3) {
            f2 = f3;
        }

        this.l += (f2 - this.l) * 0.9F;
        this.i += this.l;
    }

    public String a() {
        return this.b() ? this.s : "container.enchant";
    }

    public boolean b() {
        return this.s != null && this.s.length() > 0;
    }

    public void a(String s) {
        this.s = s;
    }
}
