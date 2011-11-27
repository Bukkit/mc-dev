package net.minecraft.server;

public class BlockSmoothBrick extends Block {

    public BlockSmoothBrick(int i) {
        super(i, 54, Material.STONE);
    }

    public int a(int i, int j) {
        switch (j) {
        case 1:
            return 100;

        case 2:
            return 101;

        default:
            return 54;
        }
    }

    protected int getDropData(int i) {
        return i;
    }
}
