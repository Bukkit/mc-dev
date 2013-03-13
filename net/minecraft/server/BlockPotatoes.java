package net.minecraft.server;

public class BlockPotatoes extends BlockCrops {

    public BlockPotatoes(int i) {
        super(i);
    }

    protected int j() {
        return Item.POTATO.id;
    }

    protected int k() {
        return Item.POTATO.id;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        super.dropNaturally(world, i, j, k, l, f, i1);
        if (!world.isStatic) {
            if (l >= 7 && world.random.nextInt(50) == 0) {
                this.b(world, i, j, k, new ItemStack(Item.POTATO_POISON));
            }
        }
    }
}
