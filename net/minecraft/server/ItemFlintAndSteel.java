package net.minecraft.server;

public class ItemFlintAndSteel extends Item {

    public ItemFlintAndSteel(int i) {
        super(i);
        this.maxStackSize = 1;
        this.setMaxDurability(64);
        this.a(CreativeModeTab.i);
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
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

        if (!entityhuman.a(i, j, k, l, itemstack)) {
            return false;
        } else {
            int i1 = world.getTypeId(i, j, k);

            if (i1 == 0) {
                world.makeSound((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "fire.ignite", 1.0F, e.nextFloat() * 0.4F + 0.8F);
                world.setTypeIdUpdate(i, j, k, Block.FIRE.id);
            }

            itemstack.damage(1, entityhuman);
            return true;
        }
    }
}
