package net.minecraft.server;

public class BlockCarpet extends Block {

    protected BlockCarpet(int i) {
        super(i, Material.WOOL);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        this.b(true);
        this.a(CreativeModeTab.c);
        this.d(0);
    }

    public AxisAlignedBB b(World world, int i, int j, int k) {
        byte b0 = 0;
        float f = 0.0625F;

        return AxisAlignedBB.a().a((double) i + this.minX, (double) j + this.minY, (double) k + this.minZ, (double) i + this.maxX, (double) ((float) j + (float) b0 * f), (double) k + this.maxZ);
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public void g() {
        this.d(0);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        this.d(iblockaccess.getData(i, j, k));
    }

    protected void d(int i) {
        byte b0 = 0;
        float f = (float) (1 * (1 + b0)) / 16.0F;

        this.a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return super.canPlace(world, i, j, k) && this.f(world, i, j, k);
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        this.k(world, i, j, k);
    }

    private boolean k(World world, int i, int j, int k) {
        if (!this.f(world, i, j, k)) {
            this.c(world, i, j, k, world.getData(i, j, k), 0);
            world.setAir(i, j, k);
            return false;
        } else {
            return true;
        }
    }

    public boolean f(World world, int i, int j, int k) {
        return !world.isEmpty(i, j - 1, k);
    }

    public int getDropData(int i) {
        return i;
    }
}
