package net.minecraft.server;

import java.util.Random;

public class BlockRedstoneComparator extends BlockDiodeAbstract implements IContainer {

    public BlockRedstoneComparator(boolean flag) {
        super(flag);
        this.isTileEntity = true;
    }

    public Item getDropType(int i, Random random, int j) {
        return Items.REDSTONE_COMPARATOR;
    }

    protected int b(int i) {
        return 2;
    }

    protected BlockDiodeAbstract e() {
        return Blocks.REDSTONE_COMPARATOR_ON;
    }

    protected BlockDiodeAbstract i() {
        return Blocks.REDSTONE_COMPARATOR_OFF;
    }

    public int b() {
        return 37;
    }

    protected boolean c(int i) {
        return this.a || (i & 8) != 0;
    }

    protected int f(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return this.e(iblockaccess, i, j, k).a();
    }

    private int j(World world, int i, int j, int k, int l) {
        return !this.d(l) ? this.h(world, i, j, k, l) : Math.max(this.h(world, i, j, k, l) - this.h(world, i, j, k, l), 0);
    }

    public boolean d(int i) {
        return (i & 4) == 4;
    }

    protected boolean a(World world, int i, int j, int k, int l) {
        int i1 = this.h(world, i, j, k, l);

        if (i1 >= 15) {
            return true;
        } else if (i1 == 0) {
            return false;
        } else {
            int j1 = this.h(world, i, j, k, l);

            return j1 == 0 ? true : i1 >= j1;
        }
    }

    protected int h(World world, int i, int j, int k, int l) {
        int i1 = super.h(world, i, j, k, l);
        int j1 = l(l);
        int k1 = i + Direction.a[j1];
        int l1 = k + Direction.b[j1];
        Block block = world.getType(k1, j, l1);

        if (block.M()) {
            i1 = block.g(world, k1, j, l1, Direction.f[j1]);
        } else if (i1 < 15 && block.r()) {
            k1 += Direction.a[j1];
            l1 += Direction.b[j1];
            block = world.getType(k1, j, l1);
            if (block.M()) {
                i1 = block.g(world, k1, j, l1, Direction.f[j1]);
            }
        }

        return i1;
    }

    public TileEntityComparator e(IBlockAccess iblockaccess, int i, int j, int k) {
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

    protected void b(World world, int i, int j, int k, Block block) {
        if (!world.a(i, j, k, (Block) this)) {
            int l = world.getData(i, j, k);
            int i1 = this.j(world, i, j, k, l);
            int j1 = this.e(world, i, j, k).a();

            if (i1 != j1 || this.c(l) != this.a(world, i, j, k, l)) {
                if (this.i(world, i, j, k, l)) {
                    world.a(i, j, k, this, this.b(0), -1);
                } else {
                    world.a(i, j, k, this, this.b(0), 0);
                }
            }
        }
    }

    private void c(World world, int i, int j, int k, Random random) {
        int l = world.getData(i, j, k);
        int i1 = this.j(world, i, j, k, l);
        int j1 = this.e(world, i, j, k).a();

        this.e(world, i, j, k).a(i1);
        if (j1 != i1 || !this.d(l)) {
            boolean flag = this.a(world, i, j, k, l);
            boolean flag1 = this.a || (l & 8) != 0;

            if (flag1 && !flag) {
                world.setData(i, j, k, l & -9, 2);
            } else if (!flag1 && flag) {
                world.setData(i, j, k, l | 8, 2);
            }

            this.e(world, i, j, k);
        }
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (this.a) {
            int l = world.getData(i, j, k);

            world.setTypeAndData(i, j, k, this.i(), l | 8, 4);
        }

        this.c(world, i, j, k, random);
    }

    public void onPlace(World world, int i, int j, int k) {
        super.onPlace(world, i, j, k);
        world.setTileEntity(i, j, k, this.a(world, 0));
    }

    public void remove(World world, int i, int j, int k, Block block, int l) {
        super.remove(world, i, j, k, block, l);
        world.p(i, j, k);
        this.e(world, i, j, k);
    }

    public boolean a(World world, int i, int j, int k, int l, int i1) {
        super.a(world, i, j, k, l, i1);
        TileEntity tileentity = world.getTileEntity(i, j, k);

        return tileentity != null ? tileentity.c(l, i1) : false;
    }

    public TileEntity a(World world, int i) {
        return new TileEntityComparator();
    }
}
