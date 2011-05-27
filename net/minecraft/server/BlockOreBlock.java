package net.minecraft.server;

public class BlockOreBlock extends Block {

    public BlockOreBlock(int i, int j) {
        super(i, Material.e);
        this.bg = j;
    }

    public int a(int i) {
        return this.bg - 16;
    }
}
