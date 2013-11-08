package net.minecraft.server;

public class BlockSand extends BlockFalling {

    public static final String[] a = new String[] { "default", "red"};

    public BlockSand() {}

    public int getDropData(int i) {
        return i;
    }

    public MaterialMapColor f(int i) {
        return i == 1 ? MaterialMapColor.l : MaterialMapColor.d;
    }
}
