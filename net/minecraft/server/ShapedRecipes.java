package net.minecraft.server;

public class ShapedRecipes implements CraftingRecipe {

    private int width;
    private int height;
    private ItemStack[] items;
    private ItemStack result;
    public final int a;

    public ShapedRecipes(int i, int j, ItemStack[] aitemstack, ItemStack itemstack) {
        this.a = itemstack.id;
        this.width = i;
        this.height = j;
        this.items = aitemstack;
        this.result = itemstack;
    }

    public ItemStack b() {
        return this.result;
    }

    public boolean a(InventoryCrafting inventorycrafting) {
        for (int i = 0; i <= 3 - this.width; ++i) {
            for (int j = 0; j <= 3 - this.height; ++j) {
                if (this.a(inventorycrafting, i, j, true)) {
                    return true;
                }

                if (this.a(inventorycrafting, i, j, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean a(InventoryCrafting inventorycrafting, int i, int j, boolean flag) {
        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 3; ++l) {
                int i1 = k - i;
                int j1 = l - j;
                ItemStack itemstack = null;

                if (i1 >= 0 && j1 >= 0 && i1 < this.width && j1 < this.height) {
                    if (flag) {
                        itemstack = this.items[this.width - i1 - 1 + j1 * this.width];
                    } else {
                        itemstack = this.items[i1 + j1 * this.width];
                    }
                }

                ItemStack itemstack1 = inventorycrafting.b(k, l);

                if (itemstack1 != null || itemstack != null) {
                    if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null) {
                        return false;
                    }

                    if (itemstack.id != itemstack1.id) {
                        return false;
                    }

                    if (itemstack.getData() != -1 && itemstack.getData() != itemstack1.getData()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public ItemStack b(InventoryCrafting inventorycrafting) {
        return new ItemStack(this.result.id, this.result.count, this.result.getData());
    }

    public int a() {
        return this.width * this.height;
    }
}
