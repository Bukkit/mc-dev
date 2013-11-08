package net.minecraft.server;

public class BlockSmoothBrick extends Block {

    public static final String[] a = new String[] { "default", "mossy", "cracked", "chiseled"};
    public static final String[] b = new String[] { null, "mossy", "cracked", "carved"};

    public BlockSmoothBrick() {
        super(Material.STONE);
        this.a(CreativeModeTab.b);
    }

    public int getDropData(int i) {
        return i;
    }
}
