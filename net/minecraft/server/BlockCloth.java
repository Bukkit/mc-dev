package net.minecraft.server;

public class BlockCloth extends Block {

    public BlockCloth(Material material) {
        super(material);
        this.a(CreativeModeTab.b);
    }

    public int getDropData(int i) {
        return i;
    }

    public static int b(int i) {
        return c(i);
    }

    public static int c(int i) {
        return ~i & 15;
    }

    public MaterialMapColor f(int i) {
        return MaterialMapColor.a(i);
    }
}
