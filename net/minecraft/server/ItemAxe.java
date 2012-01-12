package net.minecraft.server;

public class ItemAxe extends ItemTool {

    private static Block[] bS = new Block[] { Block.WOOD, Block.BOOKSHELF, Block.LOG, Block.CHEST, Block.DOUBLE_STEP, Block.STEP, Block.PUMPKIN, Block.JACK_O_LANTERN};

    protected ItemAxe(int i, EnumToolMaterial enumtoolmaterial) {
        super(i, 3, enumtoolmaterial, bS);
    }

    public float a(ItemStack itemstack, Block block) {
        return block != null && block.material == Material.WOOD ? this.a : super.a(itemstack, block);
    }
}
