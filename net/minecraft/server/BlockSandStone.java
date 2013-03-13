package net.minecraft.server;

public class BlockSandStone extends Block {

    public static final String[] a = new String[] { "default", "chiseled", "smooth"};
    private static final String[] b = new String[] { "sandstone_side", "sandstone_carved", "sandstone_smooth"};

    public BlockSandStone(int i) {
        super(i, Material.STONE);
        this.a(CreativeModeTab.b);
    }

    public int getDropData(int i) {
        return i;
    }
}
