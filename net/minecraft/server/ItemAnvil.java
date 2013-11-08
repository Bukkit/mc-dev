package net.minecraft.server;

public class ItemAnvil extends ItemMultiTexture {

    public ItemAnvil(Block block) {
        super(block, block, BlockAnvil.a);
    }

    public int filterData(int i) {
        return i << 2;
    }
}
