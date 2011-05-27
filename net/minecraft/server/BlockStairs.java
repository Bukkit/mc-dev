package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class BlockStairs extends Block {

    private Block a;

    protected BlockStairs(int i, Block block) {
        super(i, block.textureId, block.material);
        this.a = block;
        this.c(block.strength);
        this.b(block.durability / 3.0F);
        this.a(block.stepSound);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return super.d(world, i, j, k);
    }

    public boolean a() {
        return false;
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return super.a(iblockaccess, i, j, k, l);
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {
        int l = world.getData(i, j, k);

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

    public int b() {
        return this.a.b();
    }

    public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {
        this.a.a(world, i, j, k, entity, vec3d);
    }

    public boolean d() {
        return this.a.d();
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

    public void a_(World world, int i, int j, int k) {
        this.a.a_(world, i, j, k);
    }

    public void a(World world, int i, int j, int k, EntityLiving entityliving) {
        int l = MathHelper.b((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            world.c(i, j, k, 2);
        }

        if (l == 1) {
            world.c(i, j, k, 1);
        }

        if (l == 2) {
            world.c(i, j, k, 3);
        }

        if (l == 3) {
            world.c(i, j, k, 0);
        }
    }
}
