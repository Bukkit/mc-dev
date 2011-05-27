package net.minecraft.server;

public class ItemHoe extends Item {

    public ItemHoe(int i, int j) {
        super(i);
        this.aT = 1;
        this.aU = 32 << j;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        int i1 = world.a(i, j, k);
        Material material = world.c(i, j + 1, k);

        if ((material.a() || i1 != Block.GRASS.bc) && i1 != Block.DIRT.bc) {
            return false;
        } else {
            Block block = Block.SOIL;

            world.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), block.bl.c(), (block.bl.a() + 1.0F) / 2.0F, block.bl.b() * 0.8F);
            world.d(i, j, k, block.bc);
            itemstack.a(1);
            if (world.m.nextInt(8) == 0 && i1 == Block.GRASS.bc) {
                byte b0 = 1;

                for (int j1 = 0; j1 < b0; ++j1) {
                    float f = 0.7F;
                    float f1 = world.m.nextFloat() * f + (1.0F - f) * 0.5F;
                    float f2 = 1.2F;
                    float f3 = world.m.nextFloat() * f + (1.0F - f) * 0.5F;
                    EntityItem entityitem = new EntityItem(world, (double) ((float) i + f1), (double) ((float) j + f2), (double) ((float) k + f3), new ItemStack(Item.SEEDS));

                    entityitem.ad = 10;
                    world.a((Entity) entityitem);
                }
            }

            return true;
        }
    }
}
