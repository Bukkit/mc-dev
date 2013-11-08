package net.minecraft.server;

public class RecipesDyes {

    public RecipesDyes() {}

    public void a(CraftingManager craftingmanager) {
        int i;

        for (i = 0; i < 16; ++i) {
            craftingmanager.registerShapelessRecipe(new ItemStack(Blocks.WOOL, 1, BlockCloth.c(i)), new Object[] { new ItemStack(Items.INK_SACK, 1, i), new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 0)});
            craftingmanager.registerShapedRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 8, BlockCloth.c(i)), new Object[] { "###", "#X#", "###", Character.valueOf('#'), new ItemStack(Blocks.HARDENED_CLAY), Character.valueOf('X'), new ItemStack(Items.INK_SACK, 1, i)});
            craftingmanager.registerShapedRecipe(new ItemStack(Blocks.STAINED_GLASS, 8, BlockCloth.c(i)), new Object[] { "###", "#X#", "###", Character.valueOf('#'), new ItemStack(Blocks.GLASS), Character.valueOf('X'), new ItemStack(Items.INK_SACK, 1, i)});
            craftingmanager.registerShapedRecipe(new ItemStack(Blocks.STAINED_GLASS_PANE, 16, i), new Object[] { "###", "###", Character.valueOf('#'), new ItemStack(Blocks.STAINED_GLASS, 1, i)});
        }

        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 1, 11), new Object[] { new ItemStack(Blocks.YELLOW_FLOWER, 1, 0)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 1, 1), new Object[] { new ItemStack(Blocks.RED_ROSE, 1, 0)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 3, 15), new Object[] { Items.BONE});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 9), new Object[] { new ItemStack(Items.INK_SACK, 1, 1), new ItemStack(Items.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 14), new Object[] { new ItemStack(Items.INK_SACK, 1, 1), new ItemStack(Items.INK_SACK, 1, 11)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 10), new Object[] { new ItemStack(Items.INK_SACK, 1, 2), new ItemStack(Items.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 8), new Object[] { new ItemStack(Items.INK_SACK, 1, 0), new ItemStack(Items.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 7), new Object[] { new ItemStack(Items.INK_SACK, 1, 8), new ItemStack(Items.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 3, 7), new Object[] { new ItemStack(Items.INK_SACK, 1, 0), new ItemStack(Items.INK_SACK, 1, 15), new ItemStack(Items.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 12), new Object[] { new ItemStack(Items.INK_SACK, 1, 4), new ItemStack(Items.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 6), new Object[] { new ItemStack(Items.INK_SACK, 1, 4), new ItemStack(Items.INK_SACK, 1, 2)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 5), new Object[] { new ItemStack(Items.INK_SACK, 1, 4), new ItemStack(Items.INK_SACK, 1, 1)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 13), new Object[] { new ItemStack(Items.INK_SACK, 1, 5), new ItemStack(Items.INK_SACK, 1, 9)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 3, 13), new Object[] { new ItemStack(Items.INK_SACK, 1, 4), new ItemStack(Items.INK_SACK, 1, 1), new ItemStack(Items.INK_SACK, 1, 9)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 4, 13), new Object[] { new ItemStack(Items.INK_SACK, 1, 4), new ItemStack(Items.INK_SACK, 1, 1), new ItemStack(Items.INK_SACK, 1, 1), new ItemStack(Items.INK_SACK, 1, 15)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 1, 12), new Object[] { new ItemStack(Blocks.RED_ROSE, 1, 1)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 1, 13), new Object[] { new ItemStack(Blocks.RED_ROSE, 1, 2)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 1, 7), new Object[] { new ItemStack(Blocks.RED_ROSE, 1, 3)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 1, 1), new Object[] { new ItemStack(Blocks.RED_ROSE, 1, 4)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 1, 14), new Object[] { new ItemStack(Blocks.RED_ROSE, 1, 5)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 1, 7), new Object[] { new ItemStack(Blocks.RED_ROSE, 1, 6)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 1, 9), new Object[] { new ItemStack(Blocks.RED_ROSE, 1, 7)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 1, 7), new Object[] { new ItemStack(Blocks.RED_ROSE, 1, 8)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 11), new Object[] { new ItemStack(Blocks.DOUBLE_PLANT, 1, 0)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 13), new Object[] { new ItemStack(Blocks.DOUBLE_PLANT, 1, 1)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 1), new Object[] { new ItemStack(Blocks.DOUBLE_PLANT, 1, 4)});
        craftingmanager.registerShapelessRecipe(new ItemStack(Items.INK_SACK, 2, 9), new Object[] { new ItemStack(Blocks.DOUBLE_PLANT, 1, 5)});

        for (i = 0; i < 16; ++i) {
            craftingmanager.registerShapedRecipe(new ItemStack(Blocks.WOOL_CARPET, 3, i), new Object[] { "##", Character.valueOf('#'), new ItemStack(Blocks.WOOL, 1, i)});
        }
    }
}
