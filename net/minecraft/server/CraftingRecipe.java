package net.minecraft.server;

public class CraftingRecipe {

    private int b;
    private int c;
    private int[] d;
    private ItemStack e;
    public final int a;

    public CraftingRecipe(int i, int j, int[] aint, ItemStack itemstack) {
        this.a = itemstack.c;
        this.b = i;
        this.c = j;
        this.d = aint;
        this.e = itemstack;
    }

    public boolean a(int[] aint) {
        for (int i = 0; i <= 3 - this.b; ++i) {
            for (int j = 0; j <= 3 - this.c; ++j) {
                if (this.a(aint, i, j, true)) {
                    return true;
                }

                if (this.a(aint, i, j, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean a(int[] aint, int i, int j, boolean flag) {
        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 3; ++l) {
                int i1 = k - i;
                int j1 = l - j;
                int k1 = -1;

                if (i1 >= 0 && j1 >= 0 && i1 < this.b && j1 < this.c) {
                    if (flag) {
                        k1 = this.d[this.b - i1 - 1 + j1 * this.b];
                    } else {
                        k1 = this.d[i1 + j1 * this.b];
                    }
                }

                if (aint[k + l * 3] != k1) {
                    return false;
                }
            }
        }

        return true;
    }

    public ItemStack b(int[] aint) {
        return new ItemStack(this.e.c, this.e.a);
    }

    public int a() {
        return this.b * this.c;
    }
}
