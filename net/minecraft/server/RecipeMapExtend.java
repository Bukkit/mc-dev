package net.minecraft.server;

public class RecipeMapExtend extends ShapedRecipes {

    public RecipeMapExtend() {
        super(3, 3, new ItemStack[] { new ItemStack(Items.PAPER), new ItemStack(Items.PAPER), new ItemStack(Items.PAPER), new ItemStack(Items.PAPER), new ItemStack(Items.MAP, 0, 32767), new ItemStack(Items.PAPER), new ItemStack(Items.PAPER), new ItemStack(Items.PAPER), new ItemStack(Items.PAPER)}, new ItemStack(Items.MAP_EMPTY, 0, 0));
    }

    public boolean a(InventoryCrafting inventorycrafting, World world) {
        if (!super.a(inventorycrafting, world)) {
            return false;
        } else {
            ItemStack itemstack = null;

            for (int i = 0; i < inventorycrafting.getSize() && itemstack == null; ++i) {
                ItemStack itemstack1 = inventorycrafting.getItem(i);

                if (itemstack1 != null && itemstack1.getItem() == Items.MAP) {
                    itemstack = itemstack1;
                }
            }

            if (itemstack == null) {
                return false;
            } else {
                WorldMap worldmap = Items.MAP.getSavedMap(itemstack, world);

                return worldmap == null ? false : worldmap.scale < 4;
            }
        }
    }

    public ItemStack a(InventoryCrafting inventorycrafting) {
        ItemStack itemstack = null;

        for (int i = 0; i < inventorycrafting.getSize() && itemstack == null; ++i) {
            ItemStack itemstack1 = inventorycrafting.getItem(i);

            if (itemstack1 != null && itemstack1.getItem() == Items.MAP) {
                itemstack = itemstack1;
            }
        }

        itemstack = itemstack.cloneItemStack();
        itemstack.count = 1;
        if (itemstack.getTag() == null) {
            itemstack.setTag(new NBTTagCompound());
        }

        itemstack.getTag().setBoolean("map_is_scaling", true);
        return itemstack;
    }
}
