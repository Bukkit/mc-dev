package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockStairs extends Block {

    private static final int[][] a = new int[][] { { 2, 6}, { 3, 7}, { 2, 3}, { 6, 7}, { 0, 4}, { 1, 5}, { 0, 1}, { 4, 5}};
    private final Block b;
    private final int c;
    private boolean cr = false;
    private int cs = 0;

    protected BlockStairs(int i, Block block, int j) {
        super(i, block.textureId, block.material);
        this.b = block;
        this.c = j;
        this.c(block.strength);
        this.b(block.durability / 3.0F);
        this.a(block.stepSound);
        this.h(255);
        this.a(CreativeModeTab.b);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        if (this.cr) {
            this.a(0.5F * (float) (this.cs % 2), 0.5F * (float) (this.cs / 2 % 2), 0.5F * (float) (this.cs / 4 % 2), 0.5F + 0.5F * (float) (this.cs % 2), 0.5F + 0.5F * (float) (this.cs / 2 % 2), 0.5F + 0.5F * (float) (this.cs / 4 % 2));
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public boolean d() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public int b() {
        return 10;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
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
        super.a(world, i, j, k, axisalignedbb, list, entity);
        if (i1 == 0) {
            this.a(0.5F, f2, 0.0F, 1.0F, f3, 1.0F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
        } else if (i1 == 1) {
            this.a(0.0F, f2, 0.0F, 0.5F, f3, 1.0F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
        } else if (i1 == 2) {
            this.a(0.0F, f2, 0.5F, 1.0F, f3, 1.0F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
        } else if (i1 == 3) {
            this.a(0.0F, f2, 0.0F, 1.0F, f3, 0.5F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
        }

        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
        this.b.attack(world, i, j, k, entityhuman);
    }

    public void postBreak(World world, int i, int j, int k, int l) {
        this.b.postBreak(world, i, j, k, l);
    }

    public float a(Entity entity) {
        return this.b.a(entity);
    }

    public int a(int i, int j) {
        return this.b.a(i, this.c);
    }

    public int a(int i) {
        return this.b.a(i, this.c);
    }

    public int p_() {
        return this.b.p_();
    }

    public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {
        this.b.a(world, i, j, k, entity, vec3d);
    }

    public boolean l() {
        return this.b.l();
    }

    public boolean a(int i, boolean flag) {
        return this.b.a(i, flag);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return this.b.canPlace(world, i, j, k);
    }

    public void onPlace(World world, int i, int j, int k) {
        this.doPhysics(world, i, j, k, 0);
        this.b.onPlace(world, i, j, k);
    }

    public void remove(World world, int i, int j, int k, int l, int i1) {
        this.b.remove(world, i, j, k, l, i1);
    }

    public void b(World world, int i, int j, int k, Entity entity) {
        this.b.b(world, i, j, k, entity);
    }

    public void b(World world, int i, int j, int k, Random random) {
        this.b.b(world, i, j, k, random);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        return this.b.interact(world, i, j, k, entityhuman, 0, 0.0F, 0.0F, 0.0F);
    }

    public void wasExploded(World world, int i, int j, int k) {
        this.b.wasExploded(world, i, j, k);
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

    public void postPlace(World world, int i, int j, int k, int l, float f, float f1, float f2) {
        if (l == 0 || l != 1 && (double) f1 > 0.5D) {
            int i1 = world.getData(i, j, k);

            world.setData(i, j, k, i1 | 4);
        }
    }

    public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
        MovingObjectPosition[] amovingobjectposition = new MovingObjectPosition[8];
        int l = world.getData(i, j, k);
        int i1 = l & 3;
        boolean flag = (l & 4) == 4;
        int[] aint = a[i1 + (flag ? 4 : 0)];

        this.cr = true;

        int j1;
        int k1;
        int l1;

        for (int i2 = 0; i2 < 8; ++i2) {
            this.cs = i2;
            int[] aint1 = aint;

            j1 = aint.length;

            for (k1 = 0; k1 < j1; ++k1) {
                l1 = aint1[k1];
                if (l1 == i2) {
                    ;
                }
            }

            amovingobjectposition[i2] = super.a(world, i, j, k, vec3d, vec3d1);
        }

        int[] aint2 = aint;
        int j2 = aint.length;

        for (j1 = 0; j1 < j2; ++j1) {
            k1 = aint2[j1];
            amovingobjectposition[k1] = null;
        }

        MovingObjectPosition movingobjectposition = null;
        double d0 = 0.0D;
        MovingObjectPosition[] amovingobjectposition1 = amovingobjectposition;

        l1 = amovingobjectposition.length;

        for (int k2 = 0; k2 < l1; ++k2) {
            MovingObjectPosition movingobjectposition1 = amovingobjectposition1[k2];

            if (movingobjectposition1 != null) {
                double d1 = movingobjectposition1.pos.distanceSquared(vec3d1);

                if (d1 > d0) {
                    movingobjectposition = movingobjectposition1;
                    d0 = d1;
                }
            }
        }

        return movingobjectposition;
    }
}
