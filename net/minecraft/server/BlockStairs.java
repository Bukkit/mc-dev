package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class BlockStairs extends Block {

    private Block a;

    protected BlockStairs(int i, Block block) {
        super(i, block.bb, block.bn);
        this.a = block;
        this.c(block.bd);
        this.b(block.be / 3.0F);
        this.a(block.bl);
    }

    public boolean b() {
        return false;
    }

    public int a() {
        return 10;
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return super.a(iblockaccess, i, j, k, l);
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {
        int l = world.b(i, j, k);

        if (l == 0) {
            this.a(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            this.a(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        } else if (l == 1) {
            this.a(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            this.a(0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        } else if (l == 2) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            this.a(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        } else if (l == 3) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
            this.a(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        }

        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void b(World world, int i, int j, int k, int l) {
        if (!world.x) {
            if (world.c(i, j + 1, k).a()) {
                world.d(i, j, k, this.a.bc);
            } else {
                this.g(world, i, j, k);
                this.g(world, i + 1, j - 1, k);
                this.g(world, i - 1, j - 1, k);
                this.g(world, i, j - 1, k - 1);
                this.g(world, i, j - 1, k + 1);
                this.g(world, i + 1, j + 1, k);
                this.g(world, i - 1, j + 1, k);
                this.g(world, i, j + 1, k - 1);
                this.g(world, i, j + 1, k + 1);
            }

            this.a.b(world, i, j, k, l);
        }
    }

    private void g(World world, int i, int j, int k) {
        if (this.i(world, i, j, k)) {
            byte b0 = -1;

            if (this.i(world, i + 1, j + 1, k)) {
                b0 = 0;
            }

            if (this.i(world, i - 1, j + 1, k)) {
                b0 = 1;
            }

            if (this.i(world, i, j + 1, k + 1)) {
                b0 = 2;
            }

            if (this.i(world, i, j + 1, k - 1)) {
                b0 = 3;
            }

            if (b0 < 0) {
                if (this.h(world, i + 1, j, k) && !this.h(world, i - 1, j, k)) {
                    b0 = 0;
                }

                if (this.h(world, i - 1, j, k) && !this.h(world, i + 1, j, k)) {
                    b0 = 1;
                }

                if (this.h(world, i, j, k + 1) && !this.h(world, i, j, k - 1)) {
                    b0 = 2;
                }

                if (this.h(world, i, j, k - 1) && !this.h(world, i, j, k + 1)) {
                    b0 = 3;
                }
            }

            if (b0 < 0) {
                if (this.i(world, i - 1, j - 1, k)) {
                    b0 = 0;
                }

                if (this.i(world, i + 1, j - 1, k)) {
                    b0 = 1;
                }

                if (this.i(world, i, j - 1, k - 1)) {
                    b0 = 2;
                }

                if (this.i(world, i, j - 1, k + 1)) {
                    b0 = 3;
                }
            }

            if (b0 >= 0) {
                world.b(i, j, k, b0);
            }
        }
    }

    private boolean h(World world, int i, int j, int k) {
        return world.c(i, j, k).a();
    }

    private boolean i(World world, int i, int j, int k) {
        int l = world.a(i, j, k);

        return l == 0 ? false : Block.n[l].a() == 10;
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman) {
        this.a.b(world, i, j, k, entityhuman);
    }

    public void a(World world, int i, int j, int k, int l) {
        this.a.a(world, i, j, k, l);
    }

    public float a(Entity entity) {
        return this.a.a(entity);
    }

    public int a(int i, Random random) {
        return this.a.a(i, random);
    }

    public int a(Random random) {
        return this.a.a(random);
    }

    public int a(int i) {
        return this.a.a(i);
    }

    public int c() {
        return this.a.c();
    }

    public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {
        this.a.a(world, i, j, k, entity, vec3d);
    }

    public boolean e() {
        return this.a.e();
    }

    public boolean a(int i, boolean flag) {
        return this.a.a(i, flag);
    }

    public boolean a(World world, int i, int j, int k) {
        return this.a.a(world, i, j, k);
    }

    public void e(World world, int i, int j, int k) {
        this.b(world, i, j, k, 0);
        this.a.e(world, i, j, k);
    }

    public void b(World world, int i, int j, int k) {
        this.a.b(world, i, j, k);
    }

    public void a(World world, int i, int j, int k, int l, float f) {
        this.a.a(world, i, j, k, l, f);
    }

    public void a_(World world, int i, int j, int k, int l) {
        this.a.a_(world, i, j, k, l);
    }

    public void b(World world, int i, int j, int k, Entity entity) {
        this.a.b(world, i, j, k, entity);
    }

    public void a(World world, int i, int j, int k, Random random) {
        this.a.a(world, i, j, k, random);
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        return this.a.a(world, i, j, k, entityhuman);
    }

    public void c(World world, int i, int j, int k) {
        this.a.c(world, i, j, k);
    }
}
