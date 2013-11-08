package net.minecraft.server;

public class BlockQuartz extends Block {

    public static final String[] a = new String[] { "default", "chiseled", "lines"};
    private static final String[] b = new String[] { "side", "chiseled", "lines", null, null};

    public BlockQuartz() {
        super(Material.STONE);
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

    protected ItemStack j(int i) {
        return i != 3 && i != 4 ? super.j(i) : new ItemStack(Item.getItemOf(this), 1, 2);
    }

    public int b() {
        return 39;
    }

    public MaterialMapColor f(int i) {
        return MaterialMapColor.p;
    }
}
