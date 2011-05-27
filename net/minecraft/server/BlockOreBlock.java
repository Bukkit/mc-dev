package net.minecraft.server;

public class BlockOreBlock extends Block {

    public BlockOreBlock(int i, int j) {
        super(i, Material.e);
        this.bb = j;
    }

    public int a(int i) {
        return i == 1 ? this.bb - 16 : (i == 0 ? this.bb + 16 : this.bb);
    }
}
