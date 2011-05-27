package net.minecraft.server;

public class TileEntityNote extends TileEntity {

    public byte e = 0;
    public boolean f = false;

    public TileEntityNote() {}

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("note", this.e);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.e = nbttagcompound.b("note");
        if (this.e < 0) {
            this.e = 0;
        }

        if (this.e > 24) {
            this.e = 24;
        }
    }

    public void a() {
        this.e = (byte) ((this.e + 1) % 25);
        this.d();
    }

    public void a(World world, int i, int j, int k) {
        if (world.c(i, j + 1, k) == Material.a) {
            Material material = world.c(i, j - 1, k);
            byte b0 = 0;

            if (material == Material.d) {
                b0 = 1;
            }

            if (material == Material.m) {
                b0 = 2;
            }

            if (material == Material.o) {
                b0 = 3;
            }

            if (material == Material.c) {
                b0 = 4;
            }

            world.c(i, j, k, b0, this.e);
        }
    }
}
