package net.minecraft.server;

public class BlockQuartz extends Block {

    public static final String[] a = new String[] { "default", "chiseled", "lines"};
    private static final String[] b = new String[] { "quartzblock_side", "quartzblock_chiseled", "quartzblock_lines", null, null};

    public BlockQuartz(int i) {
        super(i, Material.STONE);
        this.a(CreativeModeTab.b);
    }

    public int getPlacedData(World world, int i, int j, int k, int l, float f, float f1, float f2, int i1) {
        if (i1 == 2) {
            switch (l) {
            case 0:
            case 1:
                i1 = 2;
                break;

            case 2:
            case 3:
                i1 = 4;
                break;

            case 4:
            case 5:
                i1 = 3;
            }
        }

        return i1;
    }

    public int getDropData(int i) {
        return i != 3 && i != 4 ? i : 2;
    }

    protected ItemStack c_(int i) {
        return i != 3 && i != 4 ? super.c_(i) : new ItemStack(this.id, 1, 2);
    }

    public int d() {
        return 39;
    }
}
