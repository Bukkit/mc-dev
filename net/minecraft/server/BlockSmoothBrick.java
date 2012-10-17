package net.minecraft.server;

public class BlockSmoothBrick extends Block {

    public static final String[] a = new String[] { "default", "mossy", "cracked", "chiseled"};

    public BlockSmoothBrick(int i) {
        super(i, 54, Material.STONE);
        this.a(CreativeModeTab.b);
    }

    public int a(int i, int j) {
        switch (j) {
        case 1:
            return 100;

        case 2:
            return 101;

        case 3:
            return 213;

        default:
            return 54;
        }
    }

    public int getDropData(int i) {
        return i;
    }
}
