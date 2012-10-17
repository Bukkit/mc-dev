package net.minecraft.server;

public class BlockWood extends Block {

    public static final String[] a = new String[] { "oak", "spruce", "birch", "jungle"};

    public BlockWood(int i) {
        super(i, 4, Material.WOOD);
        this.a(CreativeModeTab.b);
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

    public int getDropData(int i) {
        return i;
    }
}
