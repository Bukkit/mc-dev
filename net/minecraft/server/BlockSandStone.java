package net.minecraft.server;

public class BlockSandStone extends Block {

    public BlockSandStone(int i) {
        super(i, 192, Material.d);
    }

    public int a(int i) {
        return i == 1 ? this.bh - 16 : (i == 0 ? this.bh + 16 : this.bh);
    }
}
