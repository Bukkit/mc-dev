package net.minecraft.server;

public class RecipesDyes {

    public RecipesDyes() {}

    public void a(CraftingManager craftingmanager) {
        for (int i = 0; i < 16; ++i) {
            craftingmanager.registerShapelessRecipe(new ItemStack(Block.WOOL, 1, BlockCloth.d(i)), new Object[] { new ItemStack(Item.INK_SACK, 1, i), new ItemStack(Item.byId[Block.WOOL.id], 1, 0)});
        }

        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 11), new Object[] { Block.YELLOW_FLOWER});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 1), new Object[] { Block.RED_ROSE});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 3, 15), new Object[] { Item.BONE});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 9), new Object[] { new ItemStack(Item.INK_SACK, 1, 1), new ItemStack(Item.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 14), new Object[] { new ItemStack(Item.INK_SACK, 1, 1), new ItemStack(Item.INK_SACK, 1, 11)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 10), new Object[] { new ItemStack(Item.INK_SACK, 1, 2), new ItemStack(Item.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 8), new Object[] { new ItemStack(Item.INK_SACK, 1, 0), new ItemStack(Item.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 7), new Object[] { new ItemStack(Item.INK_SACK, 1, 8), new ItemStack(Item.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 3, 7), new Object[] { new ItemStack(Item.INK_SACK, 1, 0), new ItemStack(Item.INK_SACK, 1, 15), new ItemStack(Item.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 12), new Object[] { new ItemStack(Item.INK_SACK, 1, 4), new ItemStack(Item.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 6), new Object[] { new ItemStack(Item.INK_SACK, 1, 4), new ItemStack(Item.INK_SACK, 1, 2)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 5), new Object[] { new ItemStack(Item.INK_SACK, 1, 4), new ItemStack(Item.INK_SACK, 1, 1)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 2, 13), new Object[] { new ItemStack(Item.INK_SACK, 1, 5), new ItemStack(Item.INK_SACK, 1, 9)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 3, 13), new Object[] { new ItemStack(Item.INK_SACK, 1, 4), new ItemStack(Item.INK_SACK, 1, 1), new ItemStack(Item.INK_SACK, 1, 9)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Item.INK_SACK, 4, 13), new Object[] { new ItemStack(Item.INK_SACK, 1, 4), new ItemStack(Item.INK_SACK, 1, 1), new ItemStack(Item.INK_SACK, 1, 1), new ItemStack(Item.INK_SACK, 1, 15)});
    }
}
