package net.minecraft.server;

public class BlockCloth extends Block {

    public BlockCloth() {
        super(35, 64, Material.CLOTH);
        this.a(CreativeModeTab.b);
    }

    public int a(int i, int j) {
        if (j == 0) {
            return this.textureId;
        } else {
            j = ~(j & 15);
            return 113 + ((j & 8) >> 3) + (j & 7) * 16;
        }
    }

    public int getDropData(int i) {
        return i;
    }

    public static int e_(int i) {
        return ~i & 15;
    }

    public static int d(int i) {
        return ~i & 15;
    }
}
