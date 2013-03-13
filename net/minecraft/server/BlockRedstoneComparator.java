package net.minecraft.server;

import java.util.Random;

public class BlockRedstoneComparator extends BlockDiodeAbstract implements IContainer {

    public BlockRedstoneComparator(int i, boolean flag) {
        super(i, flag);
        this.isTileEntity = true;
    }

    public int getDropType(int i, Random random, int j) {
        return Item.REDSTONE_COMPARATOR.id;
    }

    protected int i_(int i) {
        return 2;
    }

    protected BlockDiodeAbstract i() {
        return Block.REDSTONE_COMPARATOR_ON;
    }

    protected BlockDiodeAbstract j() {
        return Block.REDSTONE_COMPARATOR_OFF;
    }

    public int d() {
        return 37;
    }

    protected boolean c(int i) {
        return this.a || (i & 8) != 0;
    }

    protected int d(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return this.a_(iblockaccess, i, j, k).a();
    }

    private int m(World world, int i, int j, int k, int l) {
        return !this.d(l) ? this.e(world, i, j, k, l) : Math.max(this.e(world, i, j, k, l) - this.f(world, i, j, k, l), 0);
    }

    public boolean d(int i) {
        return (i & 4) == 4;
    }

    protected boolean d(World world, int i, int j, int k, int l) {
        int i1 = this.e(world, i, j, k, l);

        if (i1 >= 15) {
            return true;
        } else if (i1 == 0) {
            return false;
        } else {
            int j1 = this.f(world, i, j, k, l);

            return j1 == 0 ? true : i1 >= j1;
        }
    }

    protected int e(World world, int i, int j, int k, int l) {
        int i1 = super.e(world, i, j, k, l);
        int j1 = j(l);
        int k1 = i + Direction.a[j1];
        int l1 = k + Direction.b[j1];
        int i2 = world.getTypeId(k1, j, l1);

        if (i2 > 0) {
            if (Block.byId[i2].q_()) {
                i1 = Block.byId[i2].b_(world, k1, j, l1, Direction.f[j1]);
            } else if (i1 < 15 && Block.l(i2)) {
                k1 += Direction.a[j1];
                l1 += Direction.b[j1];
                i2 = world.getTypeId(k1, j, l1);
                if (i2 > 0 && Block.byId[i2].q_()) {
                    i1 = Block.byId[i2].b_(world, k1, j, l1, Direction.f[j1]);
                }
            }
        }

        return i1;
    }

    public TileEntityComparator a_(IBlockAccess iblockaccess, int i, int j, int k) {
        return (TileEntityComparator) iblockaccess.getTileEntity(i, j, k);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        int i1 = world.getData(i, j, k);
        boolean flag = this.a | (i1 & 8) != 0;
        boolean flag1 = !this.d(i1);
        int j1 = flag1 ? 4 : 0;

        j1 |= flag ? 8 : 0;
        world.makeSound((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, flag1 ? 0.55F : 0.5F);
        world.setData(i, j, k, j1 | i1 & 3, 2);
        this.c(world, i, j, k, world.random);
        return true;
    }

    protected void f(World world, int i, int j, int k, int l) {
        if (!world.a(i, j, k, this.id)) {
            int i1 = world.getData(i, j, k);
            int j1 = this.m(world, i, j, k, i1);
            int k1 = this.a_(world, i, j, k).a();

            if (j1 != k1 || this.c(i1) != this.d(world, i, j, k, i1)) {
                if (this.h(world, i, j, k, i1)) {
                    world.a(i, j, k, this.id, this.i_(0), -1);
                } else {
                    world.a(i, j, k, this.id, this.i_(0), 0);
                }
            }
        }
    }

    private void c(World world, int i, int j, int k, Random random) {
        int l = world.getData(i, j, k);
        int i1 = this.m(world, i, j, k, l);
        int j1 = this.a_(world, i, j, k).a();

        this.a_(world, i, j, k).a(i1);
        if (j1 != i1 || !this.d(l)) {
            boolean flag = this.d(world, i, j, k, l);
            boolean flag1 = this.a || (l & 8) != 0;

            if (flag1 && !flag) {
                world.setData(i, j, k, l & -9, 2);
            } else if (!flag1 && flag) {
                world.setData(i, j, k, l | 8, 2);
            }

            this.h_(world, i, j, k);
        }
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (this.a) {
            int l = world.getData(i, j, k);

            world.setTypeIdAndData(i, j, k, this.j().id, l | 8, 4);
        }

        this.c(world, i, j, k, random);
    }

    public void onPlace(World world, int i, int j, int k) {
        super.onPlace(world, i, j, k);
        world.setTileEntity(i, j, k, this.b(world));
    }

    public void remove(World world, int i, int j, int k, int l, int i1) {
        super.remove(world, i, j, k, l, i1);
        world.s(i, j, k);
        this.h_(world, i, j, k);
    }

    public boolean b(World world, int i, int j, int k, int l, int i1) {
        super.b(world, i, j, k, l, i1);
        TileEntity tileentity = world.getTileEntity(i, j, k);

        return tileentity != null ? tileentity.b(l, i1) : false;
    }

    public TileEntity b(World world) {
        return new TileEntityComparator();
    }
}
