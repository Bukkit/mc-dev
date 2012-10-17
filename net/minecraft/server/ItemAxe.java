package net.minecraft.server;

public class ItemAxe extends ItemTool {

    private static Block[] c = new Block[] { Block.WOOD, Block.BOOKSHELF, Block.LOG, Block.CHEST, Block.DOUBLE_STEP, Block.STEP, Block.PUMPKIN, Block.JACK_O_LANTERN};

    protected ItemAxe(int i, EnumToolMaterial enumtoolmaterial) {
        super(i, 3, enumtoolmaterial, c);
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        return block != null && (block.material == Material.WOOD || block.material == Material.PLANT || block.material == Material.REPLACEABLE_PLANT) ? this.a : super.getDestroySpeed(itemstack, block);
    }
}
