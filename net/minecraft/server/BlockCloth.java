package net.minecraft.server;

public class BlockCloth extends Block {

    public BlockCloth() {
        super(35, Material.CLOTH);
        this.a(CreativeModeTab.b);
    }

    public int getDropData(int i) {
        return i;
    }

    public static int g_(int i) {
        return ~i & 15;
    }

    public static int c(int i) {
        return ~i & 15;
    }
}
