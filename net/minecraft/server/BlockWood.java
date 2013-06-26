package net.minecraft.server;

public class BlockWood extends Block {

    public static final String[] a = new String[] { "oak", "spruce", "birch", "jungle"};

    public BlockWood(int i) {
        super(i, Material.WOOD);
        this.a(CreativeModeTab.b);
    }

    public int getDropData(int i) {
        return i;
    }
}
