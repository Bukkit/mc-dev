package net.minecraft.server;

public class RecipesIngots {

    private Object[][] a;

    public RecipesIngots() {
        this.a = new Object[][] { { Block.GOLD_BLOCK, Item.GOLD_INGOT}, { Block.IRON_BLOCK, Item.IRON_INGOT}, { Block.DIAMOND_BLOCK, Item.DIAMOND}};
    }

    public void a(CraftingManager craftingmanager) {
        for (int i = 0; i < this.a.length; ++i) {
            Block block = (Block) this.a[i][0];
            Item item = (Item) this.a[i][1];

            craftingmanager.a(new ItemStack(block), new Object[] { "###", "###", "###", Character.valueOf('#'), item});
            craftingmanager.a(new ItemStack(item, 9), new Object[] { "#", Character.valueOf('#'), block});
        }
    }
}
