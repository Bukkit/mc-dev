package net.minecraft.server;

import java.util.Random;

public class WorldGenMegaTree extends WorldGenMegaTreeAbstract {

    private boolean e;

    public WorldGenMegaTree(boolean flag, boolean flag1) {
        super(flag, 13, 15, 1, 1);
        this.e = flag1;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        int l = this.a(random);

        if (!this.a(world, random, i, j, k, l)) {
            return false;
        } else {
            this.c(world, i, k, j + l, 0, random);

            for (int i1 = 0; i1 < l; ++i1) {
                Block block = world.getType(i, j + i1, k);

                if (block.getMaterial() == Material.AIR || block.getMaterial() == Material.LEAVES) {
                    this.setTypeAndData(world, i, j + i1, k, Blocks.LOG, this.b);
                }

                if (i1 < l - 1) {
                    block = world.getType(i + 1, j + i1, k);
                    if (block.getMaterial() == Material.AIR || block.getMaterial() == Material.LEAVES) {
                        this.setTypeAndData(world, i + 1, j + i1, k, Blocks.LOG, this.b);
                    }

                    block = world.getType(i + 1, j + i1, k + 1);
                    if (block.getMaterial() == Material.AIR || block.getMaterial() == Material.LEAVES) {
                        this.setTypeAndData(world, i + 1, j + i1, k + 1, Blocks.LOG, this.b);
                    }

                    block = world.getType(i, j + i1, k + 1);
                    if (block.getMaterial() == Material.AIR || block.getMaterial() == Material.LEAVES) {
                        this.setTypeAndData(world, i, j + i1, k + 1, Blocks.LOG, this.b);
                    }
                }
            }

            return true;
        }
    }

    private void c(World world, int i, int j, int k, int l, Random random) {
        int i1 = random.nextInt(5);

        if (this.e) {
            i1 += this.a;
        } else {
            i1 += 3;
        }

        int j1 = 0;

        for (int k1 = k - i1; k1 <= k; ++k1) {
            int l1 = k - k1;
            int i2 = l + MathHelper.d((float) l1 / (float) i1 * 3.5F);

            this.a(world, i, k1, j, i2 + (l1 > 0 && i2 == j1 && (k1 & 1) == 0 ? 1 : 0), random);
            j1 = i2;
        }
    }

    public void b(World world, Random random, int i, int j, int k) {
        this.c(world, random, i - 1, j, k - 1);
        this.c(world, random, i + 2, j, k - 1);
        this.c(world, random, i - 1, j, k + 2);
        this.c(world, random, i + 2, j, k + 2);

        for (int l = 0; l < 5; ++l) {
            int i1 = random.nextInt(64);
            int j1 = i1 % 8;
            int k1 = i1 / 8;

            if (j1 == 0 || j1 == 7 || k1 == 0 || k1 == 7) {
                this.c(world, random, i - 3 + j1, j, k - 3 + k1);
            }
        }
    }

    private void c(World world, Random random, int i, int j, int k) {
        for (int l = -2; l <= 2; ++l) {
            for (int i1 = -2; i1 <= 2; ++i1) {
                if (Math.abs(l) != 2 || Math.abs(i1) != 2) {
                    this.a(world, i + l, j, k + i1);
                }
            }
        }
    }

    private void a(World world, int i, int j, int k) {
        for (int l = j + 2; l >= j - 3; --l) {
            Block block = world.getType(i, l, k);

            if (block == Blocks.GRASS || block == Blocks.DIRT) {
                this.setTypeAndData(world, i, l, k, Blocks.DIRT, 2);
                break;
            }

            if (block.getMaterial() != Material.AIR && l < j) {
                break;
            }
        }
    }
}
