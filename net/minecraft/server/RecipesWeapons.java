package net.minecraft.server;

public class RecipesWeapons {

    private String[][] a = new String[][] { { "X", "X", "#"}};
    private Object[][] b;

    public RecipesWeapons() {
        this.b = new Object[][] { { Block.WOOD, Block.COBBLESTONE, Item.IRON_INGOT, Item.DIAMOND, Item.GOLD_INGOT}, { Item.WOOD_SWORD, Item.STONE_SWORD, Item.IRON_SWORD, Item.DIAMOND_SWORD, Item.GOLD_SWORD}};
    }

    public void a(CraftingManager craftingmanager) {
        for (int i = 0; i < this.b[0].length; ++i) {
            Object object = this.b[0][i];

            for (int j = 0; j < this.b.length - 1; ++j) {
                Item item = (Item) this.b[j + 1][i];

                craftingmanager.registerShapedRecipe(new ItemStack(item), new Object[] { this.a[j], Character.valueOf('#'), Item.STICK, Character.valueOf('X'), object});
            }
        }

        craftingmanager.registerShapedRecipe(new ItemStack(Item.BOW, 1), new Object[] { " #X", "# X", " #X", Character.valueOf('X'), Item.STRING, Character.valueOf('#'), Item.STICK});
        craftingmanager.registerShapedRecipe(new ItemStack(Item.ARROW, 4), new Object[] { "X", "#", "Y", Character.valueOf('Y'), Item.FEATHER, Character.valueOf('X'), Item.FLINT, Character.valueOf('#'), Item.STICK});
    }
}
