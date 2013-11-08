package net.minecraft.server;

public class ItemBlock extends Item {

    protected final Block block;

    public ItemBlock(Block block) {
        this.block = block;
    }

    public ItemBlock b(String s) {
        super.c(s);
        return this;
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        Block block = world.getType(i, j, k);

        if (block == Blocks.SNOW && (world.getData(i, j, k) & 7) < 1) {
            l = 1;
        } else if (block != Blocks.VINE && block != Blocks.LONG_GRASS && block != Blocks.DEAD_BUSH) {
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

        if (itemstack.count == 0) {
            return false;
        } else if (!entityhuman.a(i, j, k, l, itemstack)) {
            return false;
        } else if (j == 255 && this.block.getMaterial().isBuildable()) {
            return false;
        } else if (world.mayPlace(this.block, i, j, k, false, l, entityhuman, itemstack)) {
            int i1 = this.filterData(itemstack.getData());
            int j1 = this.block.getPlacedData(world, i, j, k, l, f, f1, f2, i1);

            if (world.setTypeAndData(i, j, k, this.block, j1, 3)) {
                if (world.getType(i, j, k) == this.block) {
                    this.block.postPlace(world, i, j, k, entityhuman, itemstack);
                    this.block.postPlace(world, i, j, k, j1);
                }

                world.makeSound((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this.block.stepSound.getPlaceSound(), (this.block.stepSound.getVolume1() + 1.0F) / 2.0F, this.block.stepSound.getVolume2() * 0.8F);
                --itemstack.count;
            }

            return true;
        } else {
            return false;
        }
    }

    public String a(ItemStack itemstack) {
        return this.block.a();
    }

    public String getName() {
        return this.block.a();
    }

    public Item c(String s) {
        return this.b(s);
    }
}
