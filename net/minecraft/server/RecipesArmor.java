package net.minecraft.server;

public class RecipesArmor {

    private String[][] a = new String[][] { { "XXX", "X X"}, { "X X", "XXX", "XXX"}, { "XXX", "X X", "X X"}, { "X X", "X X"}};
    private Object[][] b;

    public RecipesArmor() {
        this.b = new Object[][] { { Item.LEATHER, Block.FIRE, Item.IRON_INGOT, Item.DIAMOND, Item.GOLD_INGOT}, { Item.LEATHER_HELMET, Item.CHAINMAIL_HELMET, Item.IRON_HELMET, Item.DIAMOND_HELMET, Item.GOLD_HELMET}, { Item.LEATHER_CHESTPLATE, Item.CHAINMAIL_CHESTPLATE, Item.IRON_CHESTPLATE, Item.DIAMOND_CHESTPLATE, Item.GOLD_CHESTPLATE}, { Item.LEATHER_LEGGINGS, Item.CHAINMAIL_LEGGINGS, Item.IRON_LEGGINGS, Item.DIAMOND_LEGGINGS, Item.GOLD_LEGGINGS}, { Item.LEATHER_BOOTS, Item.CHAINMAIL_BOOTS, Item.IRON_BOOTS, Item.DIAMOND_BOOTS, Item.GOLD_BOOTS}};
    }

    public void a(CraftingManager craftingmanager) {
        for (int i = 0; i < this.b[0].length; ++i) {
            Object object = this.b[0][i];

            for (int j = 0; j < this.b.length - 1; ++j) {
                Item item = (Item) this.b[j + 1][i];

                craftingmanager.registerShapedRecipe(new ItemStack(item), new Object[] { this.a[j], Character.valueOf('X'), object});
            }
        }
    }
}
