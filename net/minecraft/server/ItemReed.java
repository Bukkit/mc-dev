package net.minecraft.server;

public class ItemReed extends Item {

    private int id;

    public ItemReed(int i, Block block) {
        super(i);
        this.id = block.id;
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        int i1 = world.getTypeId(i, j, k);

        if (i1 == Block.SNOW.id) {
            l = 1;
        } else if (i1 != Block.VINE.id && i1 != Block.LONG_GRASS.id && i1 != Block.DEAD_BUSH.id) {
            if (l == 0) {
                --j;
            }

            if (l == 1) {
                ++j;
            }

            if (l == 2) {
                --k;
            }

            if (l == 3) {
                ++k;
            }

            if (l == 4) {
                --i;
            }

            if (l == 5) {
                ++i;
            }
        }

        if (!entityhuman.a(i, j, k, l, itemstack)) {
            return false;
        } else if (itemstack.count == 0) {
            return false;
        } else {
            if (world.mayPlace(this.id, i, j, k, false, l, (Entity) null)) {
                Block block = Block.byId[this.id];

                if (world.setTypeId(i, j, k, this.id)) {
                    if (world.getTypeId(i, j, k) == this.id) {
                        Block.byId[this.id].postPlace(world, i, j, k, l, f, f1, f2);
                        Block.byId[this.id].postPlace(world, i, j, k, entityhuman);
                    }

                    world.makeSound((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), block.stepSound.b(), (block.stepSound.getVolume1() + 1.0F) / 2.0F, block.stepSound.getVolume2() * 0.8F);
                    --itemstack.count;
                }
            }

            return true;
        }
    }
}
