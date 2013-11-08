package net.minecraft.server;

import java.util.Random;

public abstract class BlockLogAbstract extends BlockRotatable {

    public BlockLogAbstract() {
        super(Material.WOOD);
        this.a(CreativeModeTab.b);
        this.c(2.0F);
        this.a(f);
    }

    public static int c(int i) {
        return i & 3;
    }

    public int a(Random random) {
        return 1;
    }

    public Item getDropType(int i, Random random, int j) {
        return Item.getItemOf(this);
    }

    public void remove(World world, int i, int j, int k, Block block, int l) {
        byte b0 = 4;
        int i1 = b0 + 1;

        if (world.b(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1)) {
            for (int j1 = -b0; j1 <= b0; ++j1) {
                for (int k1 = -b0; k1 <= b0; ++k1) {
                    for (int l1 = -b0; l1 <= b0; ++l1) {
                        if (world.getType(i + j1, j + k1, k + l1).getMaterial() == Material.LEAVES) {
                            int i2 = world.getData(i + j1, j + k1, k + l1);

                            if ((i2 & 8) == 0) {
                                world.setData(i + j1, j + k1, k + l1, i2 | 8, 4);
                            }
                        }
                    }
                }
            }
        }
    }
}
