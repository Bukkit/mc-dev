package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockRedstoneTorch extends BlockTorch {

    private boolean a = false;
    private static List b = new ArrayList();

    private boolean a(World world, int i, int j, int k, boolean flag) {
        if (flag) {
            b.add(new RedstoneUpdateInfo(i, j, k, world.e));
        }

        int l = 0;

        for (int i1 = 0; i1 < b.size(); ++i1) {
            RedstoneUpdateInfo redstoneupdateinfo = (RedstoneUpdateInfo) b.get(i1);

            if (redstoneupdateinfo.a == i && redstoneupdateinfo.b == j && redstoneupdateinfo.c == k) {
                ++l;
                if (l >= 8) {
                    return true;
                }
            }
        }

        return false;
    }

    protected BlockRedstoneTorch(int i, int j, boolean flag) {
        super(i, j);
        this.a = flag;
        this.a(true);
    }

    public int b() {
        return 2;
    }

    public void e(World world, int i, int j, int k) {
        if (world.b(i, j, k) == 0) {
            super.e(world, i, j, k);
        }

        if (this.a) {
            world.g(i, j - 1, k, this.bi);
            world.g(i, j + 1, k, this.bi);
            world.g(i - 1, j, k, this.bi);
            world.g(i + 1, j, k, this.bi);
            world.g(i, j, k - 1, this.bi);
            world.g(i, j, k + 1, this.bi);
        }
    }

    public void b(World world, int i, int j, int k) {
        if (this.a) {
            world.g(i, j - 1, k, this.bi);
            world.g(i, j + 1, k, this.bi);
            world.g(i - 1, j, k, this.bi);
            world.g(i + 1, j, k, this.bi);
            world.g(i, j, k - 1, this.bi);
            world.g(i, j, k + 1, this.bi);
        }
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        if (!this.a) {
            return false;
        } else {
            int i1 = iblockaccess.b(i, j, k);

            return i1 == 5 && l == 1 ? false : (i1 == 3 && l == 3 ? false : (i1 == 4 && l == 2 ? false : (i1 == 1 && l == 5 ? false : i1 != 2 || l != 4)));
        }
    }

    private boolean g(World world, int i, int j, int k) {
        int l = world.b(i, j, k);

        return l == 5 && world.j(i, j - 1, k, 0) ? true : (l == 3 && world.j(i, j, k - 1, 2) ? true : (l == 4 && world.j(i, j, k + 1, 3) ? true : (l == 1 && world.j(i - 1, j, k, 4) ? true : l == 2 && world.j(i + 1, j, k, 5))));
    }

    public void a(World world, int i, int j, int k, Random random) {
        boolean flag = this.g(world, i, j, k);

        while (b.size() > 0 && world.e - ((RedstoneUpdateInfo) b.get(0)).d > 100L) {
            b.remove(0);
        }

        if (this.a) {
            if (flag) {
                world.b(i, j, k, Block.REDSTONE_TORCH_OFF.bi, world.b(i, j, k));
                if (this.a(world, i, j, k, true)) {
                    world.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), "random.fizz", 0.5F, 2.6F + (world.l.nextFloat() - world.l.nextFloat()) * 0.8F);

                    for (int l = 0; l < 5; ++l) {
                        double d0 = (double) i + random.nextDouble() * 0.6D + 0.2D;
                        double d1 = (double) j + random.nextDouble() * 0.6D + 0.2D;
                        double d2 = (double) k + random.nextDouble() * 0.6D + 0.2D;

                        world.a("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        } else if (!flag && !this.a(world, i, j, k, false)) {
            world.b(i, j, k, Block.REDSTONE_TORCH_ON.bi, world.b(i, j, k));
        }
    }

    public void b(World world, int i, int j, int k, int l) {
        super.b(world, i, j, k, l);
        world.h(i, j, k, this.bi);
    }

    public boolean d(World world, int i, int j, int k, int l) {
        return l == 0 ? this.b((IBlockAccess) world, i, j, k, l) : false;
    }

    public int a(int i, Random random) {
        return Block.REDSTONE_TORCH_ON.bi;
    }

    public boolean c() {
        return true;
    }
}
