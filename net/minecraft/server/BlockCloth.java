package net.minecraft.server;

public class BlockCloth extends Block {

    public BlockCloth() {
        super(35, 64, Material.CLOTH);
    }

    protected int b(int i) {
        return i;
    }

    public static int c(int i) {
        return ~i & 15;
    }

    public static int d(int i) {
        return ~i & 15;
    }
}
