package net.minecraft.server;

public class RecipesCrafting {

    public RecipesCrafting() {}

    public void a(CraftingManager craftingmanager) {
        craftingmanager.registerShapedRecipe(new ItemStack(Block.CHEST), new Object[] { "###", "# #", "###", Character.valueOf('#'), Block.WOOD});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.ENDER_CHEST), new Object[] { "###", "#E#", "###", Character.valueOf('#'), Block.OBSIDIAN, Character.valueOf('E'), Item.EYE_OF_ENDER});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.FURNACE), new Object[] { "###", "# #", "###", Character.valueOf('#'), Block.COBBLESTONE});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.WORKBENCH), new Object[] { "##", "##", Character.valueOf('#'), Block.WOOD});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.SANDSTONE), new Object[] { "##", "##", Character.valueOf('#'), Block.SAND});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.SANDSTONE, 4, 2), new Object[] { "##", "##", Character.valueOf('#'), Block.SANDSTONE});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.SANDSTONE, 1, 1), new Object[] { "#", "#", Character.valueOf('#'), new ItemStack(Block.STEP, 1, 1)});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.SMOOTH_BRICK, 4), new Object[] { "##", "##", Character.valueOf('#'), Block.STONE});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.IRON_FENCE, 16), new Object[] { "###", "###", Character.valueOf('#'), Item.IRON_INGOT});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.THIN_GLASS, 16), new Object[] { "###", "###", Character.valueOf('#'), Block.GLASS});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.REDSTONE_LAMP_OFF, 1), new Object[] { " R ", "RGR", " R ", Character.valueOf('R'), Item.REDSTONE, Character.valueOf('G'), Block.GLOWSTONE});
        craftingmanager.registerShapedRecipe(new ItemStack(Block.BEACON, 1), new Object[] { "GGG", "GSG", "OOO", Character.valueOf('G'), Block.GLASS, Character.valueOf('S'), Item.NETHER_STAR, Character.valueOf('O'), Block.OBSIDIAN});
    }
}
