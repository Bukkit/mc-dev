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
        this.f(255);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        return super.e(world, i, j, k);
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int c() {
        return 10;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {
        int l = world.getData(i, j, k);
        int i1 = l & 3;
        float f = 0.0F;
        float f1 = 0.5F;
        float f2 = 0.5F;
        float f3 = 1.0F;

        if ((l & 4) != 0) {
            f = 0.5F;
            f1 = 1.0F;
            f2 = 0.0F;
            f3 = 0.5F;
        }

        this.a(0.0F, f, 0.0F, 1.0F, f1, 1.0F);
        super.a(world, i, j, k, axisalignedbb, arraylist);
        if (i1 == 0) {
            this.a(0.5F, f2, 0.0F, 1.0F, f3, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        } else if (i1 == 1) {
            this.a(0.0F, f2, 0.0F, 0.5F, f3, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        } else if (i1 == 2) {
            this.a(0.0F, f2, 0.5F, 1.0F, f3, 1.0F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        } else if (i1 == 3) {
            this.a(0.0F, f2, 0.0F, 1.0F, f3, 0.5F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        }

        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
        this.a.attack(world, i, j, k, entityhuman);
    }

    public void postBreak(World world, int i, int j, int k, int l) {
        this.a.postBreak(world, i, j, k, l);
    }

    public float a(Entity entity) {
        return this.a.a(entity);
    }

    public int a(int i, int j) {
        return this.a.a(i, 0);
    }

    public int a(int i) {
        return this.a.a(i, 0);
    }

    public int d() {
        return this.a.d();
    }

    public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {
        this.a.a(world, i, j, k, entity, vec3d);
    }

    public boolean F_() {
        return this.a.F_();
    }

    public boolean a(int i, boolean flag) {
        return this.a.a(i, flag);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return this.a.canPlace(world, i, j, k);
    }

    public void onPlace(World world, int i, int j, int k) {
        this.doPhysics(world, i, j, k, 0);
        this.a.onPlace(world, i, j, k);
    }

    public void remove(World world, int i, int j, int k) {
        this.a.remove(world, i, j, k);
    }

    public void b(World world, int i, int j, int k, Entity entity) {
        this.a.b(world, i, j, k, entity);
    }

    public void a(World world, int i, int j, int k, Random random) {
        this.a.a(world, i, j, k, random);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
        return this.a.interact(world, i, j, k, entityhuman);
    }

    public void wasExploded(World world, int i, int j, int k) {
        this.a.wasExploded(world, i, j, k);
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
        int l = MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3;
        int i1 = world.getData(i, j, k) & 4;

        if (l == 0) {
            world.setData(i, j, k, 2 | i1);
        }

        if (l == 1) {
            world.setData(i, j, k, 1 | i1);
        }

        if (l == 2) {
            world.setData(i, j, k, 3 | i1);
        }

        if (l == 3) {
            world.setData(i, j, k, 0 | i1);
        }
    }

    public void postPlace(World world, int i, int j, int k, int l) {
        if (l == 0) {
            int i1 = world.getData(i, j, k);

            world.setData(i, j, k, i1 | 4);
        }
    }
}
