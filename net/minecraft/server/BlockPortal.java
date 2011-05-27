package net.minecraft.server;

import java.util.Random;

public class BlockPortal extends BlockBreakable {

    public BlockPortal(int i, int j) {
        super(i, j, Material.x, false);
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return null;
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k) {
        float f;
        float f1;

        if (iblockaccess.a(i - 1, j, k) != this.bh && iblockaccess.a(i + 1, j, k) != this.bh) {
            f = 0.125F;
            f1 = 0.5F;
            this.a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
        } else {
            f = 0.5F;
            f1 = 0.125F;
            this.a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
        }
    }

    public boolean a() {
        return false;
    }

    public boolean a_(World world, int i, int j, int k) {
        byte b0 = 0;
        byte b1 = 0;

        if (world.a(i - 1, j, k) == Block.OBSIDIAN.bh || world.a(i + 1, j, k) == Block.OBSIDIAN.bh) {
            b0 = 1;
        }

        if (world.a(i, j, k - 1) == Block.OBSIDIAN.bh || world.a(i, j, k + 1) == Block.OBSIDIAN.bh) {
            b1 = 1;
        }

        System.out.println(b0 + ", " + b1);
        if (b0 == b1) {
            return false;
        } else {
            if (world.a(i - b0, j, k - b1) == 0) {
                i -= b0;
                k -= b1;
            }

            int l;
            int i1;

            for (l = -1; l <= 2; ++l) {
                for (i1 = -1; i1 <= 3; ++i1) {
                    boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

                    if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
                        int j1 = world.a(i + b0 * l, j + i1, k + b1 * l);

                        if (flag) {
                            if (j1 != Block.OBSIDIAN.bh) {
                                return false;
                            }
                        } else if (j1 != 0 && j1 != Block.FIRE.bh) {
                            return false;
                        }
                    }
                }
            }

            world.i = true;

            for (l = 0; l < 2; ++l) {
                for (i1 = 0; i1 < 3; ++i1) {
                    world.d(i + b0 * l, j + i1, k + b1 * l, Block.PORTAL.bh);
                }
            }

            world.i = false;
            return true;
        }
    }

    public void b(World world, int i, int j, int k, int l) {
        byte b0 = 0;
        byte b1 = 1;

        if (world.a(i - 1, j, k) == this.bh || world.a(i + 1, j, k) == this.bh) {
            b0 = 1;
            b1 = 0;
        }

        int i1;

        for (i1 = j; world.a(i, i1 - 1, k) == this.bh; --i1) {
            ;
        }

        if (world.a(i, i1 - 1, k) != Block.OBSIDIAN.bh) {
            world.d(i, j, k, 0);
        } else {
            int j1;

            for (j1 = 1; j1 < 4 && world.a(i, i1 + j1, k) == this.bh; ++j1) {
                ;
            }

            if (j1 == 3 && world.a(i, i1 + j1, k) == Block.OBSIDIAN.bh) {
                boolean flag = world.a(i - 1, j, k) == this.bh || world.a(i + 1, j, k) == this.bh;
                boolean flag1 = world.a(i, j, k - 1) == this.bh || world.a(i, j, k + 1) == this.bh;

                if (flag && flag1) {
                    world.d(i, j, k, 0);
                } else if ((world.a(i + b0, j, k + b1) != Block.OBSIDIAN.bh || world.a(i - b0, j, k - b1) != this.bh) && (world.a(i - b0, j, k - b1) != Block.OBSIDIAN.bh || world.a(i + b0, j, k + b1) != this.bh)) {
                    world.d(i, j, k, 0);
                }
            } else {
                world.d(i, j, k, 0);
            }
        }
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return true;
    }

    public int a(Random random) {
        return 0;
    }

    public void a(World world, int i, int j, int k, Entity entity) {
        if (!world.z) {
            entity.D();
        }
    }
}
