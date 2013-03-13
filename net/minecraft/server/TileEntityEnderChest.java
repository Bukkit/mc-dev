package net.minecraft.server;

public class TileEntityEnderChest extends TileEntity {

    public float a;
    public float b;
    public int c;
    private int d;

    public TileEntityEnderChest() {}

    public void h() {
        super.h();
        if (++this.d % 20 * 4 == 0) {
            this.world.playNote(this.x, this.y, this.z, Block.ENDER_CHEST.id, 1, this.c);
        }

        this.b = this.a;
        float f = 0.1F;
        double d0;

        if (this.c > 0 && this.a == 0.0F) {
            double d1 = (double) this.x + 0.5D;

            d0 = (double) this.z + 0.5D;
            this.world.makeSound(d1, (double) this.y + 0.5D, d0, "random.chestopen", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
        }

        if (this.c == 0 && this.a > 0.0F || this.c > 0 && this.a < 1.0F) {
            float f1 = this.a;

            if (this.c > 0) {
                this.a += f;
            } else {
                this.a -= f;
            }

            if (this.a > 1.0F) {
                this.a = 1.0F;
            }

            float f2 = 0.5F;

            if (this.a < f2 && f1 >= f2) {
                d0 = (double) this.x + 0.5D;
                double d2 = (double) this.z + 0.5D;

                this.world.makeSound(d0, (double) this.y + 0.5D, d2, "random.chestclosed", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
            }

            if (this.a < 0.0F) {
                this.a = 0.0F;
            }
        }
    }

    public boolean b(int i, int j) {
        if (i == 1) {
            this.c = j;
            return true;
        } else {
            return super.b(i, j);
        }
    }

    public void w_() {
        this.i();
        super.w_();
    }

    public void a() {
        ++this.c;
        this.world.playNote(this.x, this.y, this.z, Block.ENDER_CHEST.id, 1, this.c);
    }

    public void b() {
        --this.c;
        this.world.playNote(this.x, this.y, this.z, Block.ENDER_CHEST.id, 1, this.c);
    }

    public boolean a(EntityHuman entityhuman) {
        return this.world.getTileEntity(this.x, this.y, this.z) != this ? false : entityhuman.e((double) this.x + 0.5D, (double) this.y + 0.5D, (double) this.z + 0.5D) <= 64.0D;
    }
}
