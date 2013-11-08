package net.minecraft.server;

public class BlockPotatoes extends BlockCrops {

    public BlockPotatoes() {}

    protected Item i() {
        return Items.POTATO;
    }

    protected Item P() {
        return Items.POTATO;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        super.dropNaturally(world, i, j, k, l, f, i1);
        if (!world.isStatic) {
            if (l >= 7 && world.random.nextInt(50) == 0) {
                this.a(world, i, j, k, new ItemStack(Items.POTATO_POISON));
            }
        }
    }
}
