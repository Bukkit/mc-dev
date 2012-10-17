package net.minecraft.server;

import java.util.Random;

public class BlockSapling extends BlockFlower {

    public static final String[] a = new String[] { "oak", "spruce", "birch", "jungle"};

    protected BlockSapling(int i, int j) {
        super(i, j);
        float f = 0.4F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.a(CreativeModeTab.c);
    }

    public void b(World world, int i, int j, int k, Random random) {
        if (!world.isStatic) {
            super.b(world, i, j, k, random);
            if (world.getLightLevel(i, j + 1, k) >= 9 && random.nextInt(7) == 0) {
                int l = world.getData(i, j, k);

                if ((l & 8) == 0) {
                    world.setData(i, j, k, l | 8);
                } else {
                    this.grow(world, i, j, k, random);
                }
            }
        }
    }

    public int a(int i, int j) {
        j &= 3;
        return j == 1 ? 63 : (j == 2 ? 79 : (j == 3 ? 30 : super.a(i, j)));
    }

    public void grow(World world, int i, int j, int k, Random random) {
        int l = world.getData(i, j, k) & 3;
        Object object = null;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;

        if (l == 1) {
            object = new WorldGenTaiga2(true);
        } else if (l == 2) {
            object = new WorldGenForest(true);
        } else if (l == 3) {
            for (i1 = 0; i1 >= -1; --i1) {
                for (j1 = 0; j1 >= -1; --j1) {
                    if (this.d(world, i + i1, j, k + j1, 3) && this.d(world, i + i1 + 1, j, k + j1, 3) && this.d(world, i + i1, j, k + j1 + 1, 3) && this.d(world, i + i1 + 1, j, k + j1 + 1, 3)) {
                        object = new WorldGenMegaTree(true, 10 + random.nextInt(20), 3, 3);
                        flag = true;
                        break;
                    }
                }

                if (object != null) {
                    break;
                }
            }

            if (object == null) {
                j1 = 0;
                i1 = 0;
                object = new WorldGenTrees(true, 4 + random.nextInt(7), 3, 3, false);
            }
        } else {
            object = new WorldGenTrees(true);
            if (random.nextInt(10) == 0) {
                object = new WorldGenBigTree(true);
            }
        }

        if (flag) {
            world.setRawTypeId(i + i1, j, k + j1, 0);
            world.setRawTypeId(i + i1 + 1, j, k + j1, 0);
            world.setRawTypeId(i + i1, j, k + j1 + 1, 0);
            world.setRawTypeId(i + i1 + 1, j, k + j1 + 1, 0);
        } else {
            world.setRawTypeId(i, j, k, 0);
        }

        if (!((WorldGenerator) object).a(world, random, i + i1, j, k + j1)) {
            if (flag) {
                world.setRawTypeIdAndData(i + i1, j, k + j1, this.id, l);
                world.setRawTypeIdAndData(i + i1 + 1, j, k + j1, this.id, l);
                world.setRawTypeIdAndData(i + i1, j, k + j1 + 1, this.id, l);
                world.setRawTypeIdAndData(i + i1 + 1, j, k + j1 + 1, this.id, l);
            } else {
                world.setRawTypeIdAndData(i, j, k, this.id, l);
            }
        }
    }

    public boolean d(World world, int i, int j, int k, int l) {
        return world.getTypeId(i, j, k) == this.id && (world.getData(i, j, k) & 3) == l;
    }

    public int getDropData(int i) {
        return i & 3;
    }
}
