package net.minecraft.server;

public class BlockPumpkin extends Block {

    private boolean a;

    protected BlockPumpkin(int i, int j, boolean flag) {
        super(i, Material.w);
        this.bg = j;
        this.a(true);
        this.a = flag;
    }

    public int a(int i) {
        return i == 1 ? this.bg : (i == 0 ? this.bg : (i == 3 ? this.bg + 1 + 16 : this.bg + 16));
    }

    public void e(World world, int i, int j, int k) {
        super.e(world, i, j, k);
    }

    public boolean a(World world, int i, int j, int k) {
        int l = world.a(i, j, k);

        return (l == 0 || Block.m[l].bs.d()) && world.d(i, j - 1, k);
    }

    public void a(World world, int i, int j, int k, EntityLiving entityliving) {
        int l = MathHelper.b((double) (entityliving.v * 4.0F / 360.0F) + 0.5D) & 3;

        world.b(i, j, k, l);
    }
}
