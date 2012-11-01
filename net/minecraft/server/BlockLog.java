package net.minecraft.server;

import java.util.Random;

public class BlockLog extends Block {

    public static final String[] a = new String[] { "oak", "spruce", "birch", "jungle"};

    protected BlockLog(int i) {
        super(i, Material.WOOD);
        this.textureId = 20;
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

        if (world.d(i - j1, j - j1, k - j1, i + j1, j + j1, k + j1)) {
            for (int k1 = -b0; k1 <= b0; ++k1) {
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        int j2 = world.getTypeId(i + k1, j + l1, k + i2);

                        if (j2 == Block.LEAVES.id) {
                            int k2 = world.getData(i + k1, j + l1, k + i2);

                            if ((k2 & 8) == 0) {
                                world.setRawData(i + k1, j + l1, k + i2, k2 | 8);
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

    public int a(int i, int j) {
        int k = j & 12;
        int l = j & 3;

        return k == 0 && (i == 1 || i == 0) ? 21 : (k == 4 && (i == 5 || i == 4) ? 21 : (k == 8 && (i == 2 || i == 3) ? 21 : (l == 1 ? 116 : (l == 2 ? 117 : (l == 3 ? 153 : 20)))));
    }

    public int getDropData(int i) {
        return i & 3;
    }

    public static int e(int i) {
        return i & 3;
    }

    protected ItemStack f_(int i) {
        return new ItemStack(this.id, 1, e(i));
    }
}
