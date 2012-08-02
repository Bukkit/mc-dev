package net.minecraft.server;

public class RecipeIngots {

    private Object[][] a;

    public RecipeIngots() {
        this.a = new Object[][] { { Block.GOLD_BLOCK, new ItemStack(Item.GOLD_INGOT, 9)}, { Block.IRON_BLOCK, new ItemStack(Item.IRON_INGOT, 9)}, { Block.DIAMOND_BLOCK, new ItemStack(Item.DIAMOND, 9)}, { Block.EMERALD_BLOCK, new ItemStack(Item.EMERALD, 9)}, { Block.LAPIS_BLOCK, new ItemStack(Item.INK_SACK, 9, 4)}};
    }

    public void a(CraftingManager craftingmanager) {
        Object[][] aobject = this.a;
        int i = aobject.length;

        for (int j = 0; j < i; ++j) {
            Object[] aobject1 = aobject[j];
            Block block = (Block) aobject1[0];
            ItemStack itemstack = (ItemStack) aobject1[1];

            craftingmanager.registerShapedRecipe(new ItemStack(block), new Object[] { "###", "###", "###", Character.valueOf('#'), itemstack});
            craftingmanager.registerShapedRecipe(itemstack, new Object[] { "#", Character.valueOf('#'), block});
        }

        craftingmanager.registerShapedRecipe(new ItemStack(Item.GOLD_INGOT), new Object[] { "###", "###", "###", Character.valueOf('#'), Item.GOLD_NUGGET});
        craftingmanager.registerShapedRecipe(new ItemStack(Item.GOLD_NUGGET, 9), new Object[] { "#", Character.valueOf('#'), Item.GOLD_INGOT});
    }
}
