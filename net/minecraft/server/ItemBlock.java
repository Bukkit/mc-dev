package net.minecraft.server;

public class ItemBlock extends Item {

    private int id;

    public ItemBlock(int i) {
        super(i);
        this.id = i + 256;
        this.b(Block.byId[i + 256].a(2));
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (world.getTypeId(i, j, k) == Block.SNOW.id) {
            l = 0;
        } else {
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
        } else if (world.a(this.id, i, j, k, false)) {
            Block block = Block.byId[this.id];

            if (world.setTypeIdAndData(i, j, k, this.id, this.filterData(itemstack.getData()))) {
                Block.byId[this.id].postPlace(world, i, j, k, l);
                Block.byId[this.id].postPlace(world, i, j, k, entityhuman);
                world.makeSound((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), block.stepSound.getName(), (block.stepSound.getVolume1() + 1.0F) / 2.0F, block.stepSound.getVolume2() * 0.8F);
                --itemstack.count;
            }

            return true;
        } else {
            return false;
        }
    }

    public String a() {
        return Block.byId[this.id].f();
    }
}
