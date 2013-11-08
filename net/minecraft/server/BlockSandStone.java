package net.minecraft.server;

public class BlockSandStone extends Block {

    public static final String[] a = new String[] { "default", "chiseled", "smooth"};
    private static final String[] b = new String[] { "normal", "carved", "smooth"};

    public BlockSandStone() {
        super(Material.STONE);
        this.a(CreativeModeTab.b);
    }

    public int getDropData(int i) {
        return i;
    }
}
