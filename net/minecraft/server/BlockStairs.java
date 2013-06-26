package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockStairs extends Block {

    private static final int[][] a = new int[][] { { 2, 6}, { 3, 7}, { 2, 3}, { 6, 7}, { 0, 4}, { 1, 5}, { 0, 1}, { 4, 5}};
    private final Block b;
    private final int c;
    private boolean d;
    private int e;

    protected BlockStairs(int i, Block block, int j) {
        super(i, block.material);
        this.b = block;
        this.c = j;
        this.c(block.strength);
        this.b(block.durability / 3.0F);
        this.a(block.stepSound);
        this.k(255);
        this.a(CreativeModeTab.b);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        if (this.d) {
            this.a(0.5F * (float) (this.e % 2), 0.5F * (float) (this.e / 2 % 2), 0.5F * (float) (this.e / 4 % 2), 0.5F + 0.5F * (float) (this.e % 2), 0.5F + 0.5F * (float) (this.e / 2 % 2), 0.5F + 0.5F * (float) (this.e / 4 % 2));
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int d() {
        return 10;
    }

    public void d(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getData(i, j, k);

        if ((l & 4) != 0) {
            this.a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }
    }

    public static boolean d(int i) {
        return i > 0 && Block.byId[i] instanceof BlockStairs;
    }

    private boolean f(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        int i1 = iblockaccess.getTypeId(i, j, k);

        return d(i1) && iblockaccess.getData(i, j, k) == l;
    }

    public boolean g(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getData(i, j, k);
        int i1 = l & 3;
        float f = 0.5F;
        float f1 = 1.0F;

        if ((l & 4) != 0) {
            f = 0.0F;
            f1 = 0.5F;
        }

        float f2 = 0.0F;
        float f3 = 1.0F;
        float f4 = 0.0F;
        float f5 = 0.5F;
        boolean flag = true;
        int j1;
        int k1;
        int l1;

        if (i1 == 0) {
            f2 = 0.5F;
            f5 = 1.0F;
            j1 = iblockaccess.getTypeId(i + 1, j, k);
            k1 = iblockaccess.getData(i + 1, j, k);
            if (d(j1) && (l & 4) == (k1 & 4)) {
                l1 = k1 & 3;
                if (l1 == 3 && !this.f(iblockaccess, i, j, k + 1, l)) {
                    f5 = 0.5F;
                    flag = false;
                } else if (l1 == 2 && !this.f(iblockaccess, i, j, k - 1, l)) {
                    f4 = 0.5F;
                    flag = false;
                }
            }
        } else if (i1 == 1) {
            f3 = 0.5F;
            f5 = 1.0F;
            j1 = iblockaccess.getTypeId(i - 1, j, k);
            k1 = iblockaccess.getData(i - 1, j, k);
            if (d(j1) && (l & 4) == (k1 & 4)) {
                l1 = k1 & 3;
                if (l1 == 3 && !this.f(iblockaccess, i, j, k + 1, l)) {
                    f5 = 0.5F;
                    flag = false;
                } else if (l1 == 2 && !this.f(iblockaccess, i, j, k - 1, l)) {
                    f4 = 0.5F;
                    flag = false;
                }
            }
        } else if (i1 == 2) {
            f4 = 0.5F;
            f5 = 1.0F;
            j1 = iblockaccess.getTypeId(i, j, k + 1);
            k1 = iblockaccess.getData(i, j, k + 1);
            if (d(j1) && (l & 4) == (k1 & 4)) {
                l1 = k1 & 3;
                if (l1 == 1 && !this.f(iblockaccess, i + 1, j, k, l)) {
                    f3 = 0.5F;
                    flag = false;
                } else if (l1 == 0 && !this.f(iblockaccess, i - 1, j, k, l)) {
                    f2 = 0.5F;
                    flag = false;
                }
            }
        } else if (i1 == 3) {
            j1 = iblockaccess.getTypeId(i, j, k - 1);
            k1 = iblockaccess.getData(i, j, k - 1);
            if (d(j1) && (l & 4) == (k1 & 4)) {
                l1 = k1 & 3;
                if (l1 == 1 && !this.f(iblockaccess, i + 1, j, k, l)) {
                    f3 = 0.5F;
                    flag = false;
                } else if (l1 == 0 && !this.f(iblockaccess, i - 1, j, k, l)) {
                    f2 = 0.5F;
                    flag = false;
                }
            }
        }

        this.a(f2, f, f4, f3, f1, f5);
        return flag;
    }

    public boolean h(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getData(i, j, k);
        int i1 = l & 3;
        float f = 0.5F;
        float f1 = 1.0F;

        if ((l & 4) != 0) {
            f = 0.0F;
            f1 = 0.5F;
        }

        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = 0.5F;
        float f5 = 1.0F;
        boolean flag = false;
        int j1;
        int k1;
        int l1;

        if (i1 == 0) {
            j1 = iblockaccess.getTypeId(i - 1, j, k);
            k1 = iblockaccess.getData(i - 1, j, k);
            if (d(j1) && (l & 4) == (k1 & 4)) {
                l1 = k1 & 3;
                if (l1 == 3 && !this.f(iblockaccess, i, j, k - 1, l)) {
                    f4 = 0.0F;
                    f5 = 0.5F;
                    flag = true;
                } else if (l1 == 2 && !this.f(iblockaccess, i, j, k + 1, l)) {
                    f4 = 0.5F;
                    f5 = 1.0F;
                    flag = true;
                }
            }
        } else if (i1 == 1) {
            j1 = iblockaccess.getTypeId(i + 1, j, k);
            k1 = iblockaccess.getData(i + 1, j, k);
            if (d(j1) && (l & 4) == (k1 & 4)) {
                f2 = 0.5F;
                f3 = 1.0F;
                l1 = k1 & 3;
                if (l1 == 3 && !this.f(iblockaccess, i, j, k - 1, l)) {
                    f4 = 0.0F;
                    f5 = 0.5F;
                    flag = true;
                } else if (l1 == 2 && !this.f(iblockaccess, i, j, k + 1, l)) {
                    f4 = 0.5F;
                    f5 = 1.0F;
                    flag = true;
                }
            }
        } else if (i1 == 2) {
            j1 = iblockaccess.getTypeId(i, j, k - 1);
            k1 = iblockaccess.getData(i, j, k - 1);
            if (d(j1) && (l & 4) == (k1 & 4)) {
                f4 = 0.0F;
                f5 = 0.5F;
                l1 = k1 & 3;
                if (l1 == 1 && !this.f(iblockaccess, i - 1, j, k, l)) {
                    flag = true;
                } else if (l1 == 0 && !this.f(iblockaccess, i + 1, j, k, l)) {
                    f2 = 0.5F;
                    f3 = 1.0F;
                    flag = true;
                }
            }
        } else if (i1 == 3) {
            j1 = iblockaccess.getTypeId(i, j, k + 1);
            k1 = iblockaccess.getData(i, j, k + 1);
            if (d(j1) && (l & 4) == (k1 & 4)) {
                l1 = k1 & 3;
                if (l1 == 1 && !this.f(iblockaccess, i - 1, j, k, l)) {
                    flag = true;
                } else if (l1 == 0 && !this.f(iblockaccess, i + 1, j, k, l)) {
                    f2 = 0.5F;
                    f3 = 1.0F;
                    flag = true;
                }
            }
        }

        if (flag) {
            this.a(f2, f, f4, f3, f1, f5);
        }

        return flag;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        this.d(world, i, j, k);
        super.a(world, i, j, k, axisalignedbb, list, entity);
        boolean flag = this.g(world, i, j, k);

        super.a(world, i, j, k, axisalignedbb, list, entity);
        if (flag && this.h(world, i, j, k)) {
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

    public int a(World world) {
        return this.b.a(world);
    }

    public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {
        this.b.a(world, i, j, k, entity, vec3d);
    }

    public boolean m() {
        return this.b.m();
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

    public void a(World world, int i, int j, int k, Random random) {
        this.b.a(world, i, j, k, random);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        return this.b.interact(world, i, j, k, entityhuman, 0, 0.0F, 0.0F, 0.0F);
    }

    public void wasExploded(World world, int i, int j, int k, Explosion explosion) {
        this.b.wasExploded(world, i, j, k, explosion);
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemstack) {
        int l = MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3;
        int i1 = world.getData(i, j, k) & 4;

        if (l == 0) {
            world.setData(i, j, k, 2 | i1, 2);
        }

        if (l == 1) {
            world.setData(i, j, k, 1 | i1, 2);
        }

        if (l == 2) {
            world.setData(i, j, k, 3 | i1, 2);
        }

        if (l == 3) {
            world.setData(i, j, k, 0 | i1, 2);
        }
    }

    public int getPlacedData(World world, int i, int j, int k, int l, float f, float f1, float f2, int i1) {
        return l != 0 && (l == 1 || (double) f1 <= 0.5D) ? i1 : i1 | 4;
    }

    public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
        MovingObjectPosition[] amovingobjectposition = new MovingObjectPosition[8];
        int l = world.getData(i, j, k);
        int i1 = l & 3;
        boolean flag = (l & 4) == 4;
        int[] aint = a[i1 + (flag ? 4 : 0)];

        this.d = true;

        int j1;
        int k1;
        int l1;

        for (int i2 = 0; i2 < 8; ++i2) {
            this.e = i2;
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
