package net.minecraft.server;

public class TileEntityNote extends TileEntity {

    public byte a = 0;
    public boolean b = false;

    public TileEntityNote() {}

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("note", this.a);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a = nbttagcompound.c("note");
        if (this.a < 0) {
            this.a = 0;
        }

        if (this.a > 24) {
            this.a = 24;
        }
    }

    public void a() {
        this.a = (byte) ((this.a + 1) % 25);
        this.h();
    }

    public void a(World world, int i, int j, int k) {
        if (world.getMaterial(i, j + 1, k) == Material.AIR) {
            Material material = world.getMaterial(i, j - 1, k);
            byte b0 = 0;

            if (material == Material.STONE) {
                b0 = 1;
            }

            if (material == Material.SAND) {
                b0 = 2;
            }

            if (material == Material.SHATTERABLE) {
                b0 = 3;
            }

            if (material == Material.WOOD) {
                b0 = 4;
            }

            world.d(i, j, k, b0, this.a);
        }
    }
}
