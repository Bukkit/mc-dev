package net.minecraft.server;

public class RecipesTools {

    private String[][] a = new String[][] { { "XXX", " # ", " # "}, { "X", "#", "#"}, { "XX", "X#", " #"}, { "XX", " #", " #"}};
    private Object[][] b;

    public RecipesTools() {
        this.b = new Object[][] { { Block.WOOD, Block.COBBLESTONE, Item.IRON_INGOT, Item.DIAMOND, Item.GOLD_INGOT}, { Item.WOOD_PICKAXE, Item.STONE_PICKAXE, Item.IRON_PICKAXE, Item.DIAMOND_PICKAXE, Item.GOLD_PICKAXE}, { Item.WOOD_SPADE, Item.STONE_SPADE, Item.IRON_SPADE, Item.DIAMOND_SPADE, Item.GOLD_SPADE}, { Item.WOOD_AXE, Item.STONE_AXE, Item.IRON_AXE, Item.DIAMOND_AXE, Item.GOLD_AXE}, { Item.WOOD_HOE, Item.STONE_HOE, Item.IRON_HOE, Item.DIAMOND_HOE, Item.GOLD_HOE}};
    }

    public void a(CraftingManager craftingmanager) {
        for (int i = 0; i < this.b[0].length; ++i) {
            Object object = this.b[0][i];

            for (int j = 0; j < this.b.length - 1; ++j) {
                Item item = (Item) this.b[j + 1][i];

                craftingmanager.registerShapedRecipe(new ItemStack(item), new Object[] { this.a[j], Character.valueOf('#'), Item.STICK, Character.valueOf('X'), object});
            }
        }

        craftingmanager.registerShapedRecipe(new ItemStack(Item.SHEARS), new Object[] { " #", "# ", Character.valueOf('#'), Item.IRON_INGOT});
    }
}
