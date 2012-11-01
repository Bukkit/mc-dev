package net.minecraft.server;

public class BlockPotatoes extends BlockCrops {

    public BlockPotatoes(int i) {
        super(i, 200);
    }

    public int a(int i, int j) {
        if (j < 7) {
            if (j == 6) {
                j = 5;
            }

            return this.textureId + (j >> 1);
        } else {
            return this.textureId + 4;
        }
    }

    protected int h() {
        return Item.POTATO.id;
    }

    protected int j() {
        return Item.POTATO.id;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        super.dropNaturally(world, i, j, k, l, f, 0);
        if (!world.isStatic) {
            if (l >= 7 && world.random.nextInt(50) == 0) {
                this.b(world, i, j, k, new ItemStack(Item.POTATO_POISON));
            }
        }
    }
}
