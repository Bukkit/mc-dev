package net.minecraft.server;

public class BlockDirt extends Block {

    public static final String[] a = new String[] { "default", "default", "podzol"};

    protected BlockDirt() {
        super(Material.EARTH);
        this.a(CreativeModeTab.b);
    }

    public int getDropData(int i) {
        return 0;
    }

    protected ItemStack j(int i) {
        if (i == 1) {
            i = 0;
        }

        return super.j(i);
    }

    public int getDropData(World world, int i, int j, int k) {
        int l = world.getData(i, j, k);

        if (l == 1) {
            l = 0;
        }

        return l;
    }
}
