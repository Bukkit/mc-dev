package net.minecraft.server;

public class BlockOreBlock extends Block {

    public BlockOreBlock(int i, int j) {
        super(i, Material.ORE);
        this.textureId = j;
    }

    public int a(int i) {
        return this.textureId;
    }
}
