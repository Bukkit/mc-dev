package net.minecraft.server;

public class BlockOreBlock extends Block {

    public BlockOreBlock(int i, int j) {
        super(i, Material.e);
        this.bh = j;
    }

    public int a(int i) {
        return this.bh - 16;
    }
}
