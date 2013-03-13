package net.minecraft.server;

public class BlockSmoothBrick extends Block {

    public static final String[] a = new String[] { "default", "mossy", "cracked", "chiseled"};
    public static final String[] b = new String[] { "stonebricksmooth", "stonebricksmooth_mossy", "stonebricksmooth_cracked", "stonebricksmooth_carved"};

    public BlockSmoothBrick(int i) {
        super(i, Material.STONE);
        this.a(CreativeModeTab.b);
    }

    public int getDropData(int i) {
        return i;
    }
}
