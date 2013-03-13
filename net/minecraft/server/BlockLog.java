package net.minecraft.server;

import java.util.Random;

public class BlockLog extends Block {

    public static final String[] a = new String[] { "oak", "spruce", "birch", "jungle"};
    public static final String[] b = new String[] { "tree_side", "tree_spruce", "tree_birch", "tree_jungle"};

    protected BlockLog(int i) {
        super(i, Material.WOOD);
        this.a(CreativeModeTab.b);
    }

    public int d() {
        return 31;
    }

    public int a(Random random) {
        return 1;
    }

    public int getDropType(int i, Random random, int j) {
        return Block.LOG.id;
    }

    public void remove(World world, int i, int j, int k, int l, int i1) {
        byte b0 = 4;
        int j1 = b0 + 1;

        if (world.e(i - j1, j - j1, k - j1, i + j1, j + j1, k + j1)) {
            for (int k1 = -b0; k1 <= b0; ++k1) {
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        int j2 = world.getTypeId(i + k1, j + l1, k + i2);

                        if (j2 == Block.LEAVES.id) {
                            int k2 = world.getData(i + k1, j + l1, k + i2);

                            if ((k2 & 8) == 0) {
                                world.setData(i + k1, j + l1, k + i2, k2 | 8, 4);
                            }
                        }
                    }
                }
            }
        }
    }

    public int getPlacedData(World world, int i, int j, int k, int l, float f, float f1, float f2, int i1) {
        int j1 = i1 & 3;
        byte b0 = 0;

        switch (l) {
        case 0:
        case 1:
            b0 = 0;
            break;

        case 2:
        case 3:
            b0 = 8;
            break;

        case 4:
        case 5:
            b0 = 4;
        }

        return j1 | b0;
    }

    public int getDropData(int i) {
        return i & 3;
    }

    public static int d(int i) {
        return i & 3;
    }

    protected ItemStack c_(int i) {
        return new ItemStack(this.id, 1, d(i));
    }
}
