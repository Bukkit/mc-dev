package net.minecraft.server;

public class RecipesCrafting {

    public RecipesCrafting() {}

    public void a(CraftingManager craftingmanager) {
        craftingmanager.registerShapedRecipe(new ItemStack(Block.CHEST), new Object[] { "###", "# #", "###", Character.valueOf('#'), Block.WOOD});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.FURNACE), new Object[] { "###", "# #", "###", Character.valueOf('#'), Block.COBBLESTONE});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.WORKBENCH), new Object[] { "##", "##", Character.valueOf('#'), Block.WOOD});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.SANDSTONE), new Object[] { "##", "##", Character.valueOf('#'), Block.SAND});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.SMOOTH_BRICK, 4), new Object[] { "##", "##", Character.valueOf('#'), Block.STONE});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.IRON_FENCE, 16), new Object[] { "###", "###", Character.valueOf('#'), Item.IRON_INGOT});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.THIN_GLASS, 16), new Object[] { "###", "###", Character.valueOf('#'), Block.GLASS});
    }
}
