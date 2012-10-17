package net.minecraft.server;

public class RecipesMapClone implements IRecipe {

    public RecipesMapClone() {}

    public boolean a(InventoryCrafting inventorycrafting, World world) {
        int i = 0;
        ItemStack itemstack = null;

        for (int j = 0; j < inventorycrafting.getSize(); ++j) {
            ItemStack itemstack1 = inventorycrafting.getItem(j);

            if (itemstack1 != null) {
                if (itemstack1.id == Item.MAP.id) {
                    if (itemstack != null) {
                        return false;
                    }

                    itemstack = itemstack1;
                } else {
                    if (itemstack1.id != Item.MAP_EMPTY.id) {
                        return false;
                    }

                    ++i;
                }
            }
        }

        return itemstack != null && i > 0;
    }

    public ItemStack a(InventoryCrafting inventorycrafting) {
        int i = 0;
        ItemStack itemstack = null;

        for (int j = 0; j < inventorycrafting.getSize(); ++j) {
            ItemStack itemstack1 = inventorycrafting.getItem(j);

            if (itemstack1 != null) {
                if (itemstack1.id == Item.MAP.id) {
                    if (itemstack != null) {
                        return null;
                    }

                    itemstack = itemstack1;
                } else {
                    if (itemstack1.id != Item.MAP_EMPTY.id) {
                        return null;
                    }

                    ++i;
                }
            }
        }

        if (itemstack != null && i >= 1) {
            return new ItemStack(Item.MAP, i + 1, itemstack.getData());
        } else {
            return null;
        }
    }

    public int a() {
        return 9;
    }

    public ItemStack b() {
        return null;
    }
}
