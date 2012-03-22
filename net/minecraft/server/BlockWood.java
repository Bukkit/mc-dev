package net.minecraft.server;

public class BlockWood extends Block {

    public BlockWood(int i) {
        super(i, 4, Material.WOOD);
    }

    public int a(int i, int j) {
        switch (j) {
        case 1:
            return 198;

        case 2:
            return 214;

        case 3:
            return 199;

        default:
            return 4;
        }
    }

    protected int getDropData(int i) {
        return i;
    }
}
