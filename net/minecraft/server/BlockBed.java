package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;

public class BlockBed extends BlockDirectional {

    public static final int[][] a = new int[][] { { 0, 1}, { -1, 0}, { 0, -1}, { 1, 0}};

    public BlockBed(int i) {
        super(i, Material.CLOTH);
        this.q();
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            int i1 = world.getData(i, j, k);

            if (!f_(i1)) {
                int j1 = j(i1);

                i += a[j1][0];
                k += a[j1][1];
                if (world.getTypeId(i, j, k) != this.id) {
                    return true;
                }

                i1 = world.getData(i, j, k);
            }

            if (world.worldProvider.e() && world.getBiome(i, k) != BiomeBase.HELL) {
                if (c(i1)) {
                    EntityHuman entityhuman1 = null;
                    Iterator iterator = world.players.iterator();

                    while (iterator.hasNext()) {
                        EntityHuman entityhuman2 = (EntityHuman) iterator.next();

                        if (entityhuman2.isSleeping()) {
                            ChunkCoordinates chunkcoordinates = entityhuman2.bD;

                            if (chunkcoordinates.x == i && chunkcoordinates.y == j && chunkcoordinates.z == k) {
                                entityhuman1 = entityhuman2;
                            }
                        }
                    }

                    if (entityhuman1 != null) {
                        entityhuman.a("tile.bed.occupied");
                        return true;
                    }

                    a(world, i, j, k, false);
                }

                EnumBedResult enumbedresult = entityhuman.a(i, j, k);

                if (enumbedresult == EnumBedResult.OK) {
                    a(world, i, j, k, true);
                    return true;
                } else {
                    if (enumbedresult == EnumBedResult.NOT_POSSIBLE_NOW) {
                        entityhuman.a("tile.bed.noSleep");
                    } else if (enumbedresult == EnumBedResult.NOT_SAFE) {
                        entityhuman.a("tile.bed.notSafe");
                    }

                    return true;
                }
            } else {
                double d0 = (double) i + 0.5D;
                double d1 = (double) j + 0.5D;
                double d2 = (double) k + 0.5D;

                world.setAir(i, j, k);
                int k1 = j(i1);

                i += a[k1][0];
                k += a[k1][1];
                if (world.getTypeId(i, j, k) == this.id) {
                    world.setAir(i, j, k);
                    d0 = (d0 + (double) i + 0.5D) / 2.0D;
                    d1 = (d1 + (double) j + 0.5D) / 2.0D;
                    d2 = (d2 + (double) k + 0.5D) / 2.0D;
                }

                world.createExplosion((Entity) null, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), 5.0F, true, true);
                return true;
            }
        }
    }

    public int d() {
        return 14;
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        this.q();
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        int i1 = world.getData(i, j, k);
        int j1 = j(i1);

        if (f_(i1)) {
            if (world.getTypeId(i - a[j1][0], j, k - a[j1][1]) != this.id) {
                world.setAir(i, j, k);
            }
        } else if (world.getTypeId(i + a[j1][0], j, k + a[j1][1]) != this.id) {
            world.setAir(i, j, k);
            if (!world.isStatic) {
                this.c(world, i, j, k, i1, 0);
            }
        }
    }

    public int getDropType(int i, Random random, int j) {
        return f_(i) ? 0 : Item.BED.id;
    }

    private void q() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
    }

    public static boolean f_(int i) {
        return (i & 8) != 0;
    }

    public static boolean c(int i) {
        return (i & 4) != 0;
    }

    public static void a(World world, int i, int j, int k, boolean flag) {
        int l = world.getData(i, j, k);

        if (flag) {
            l |= 4;
        } else {
            l &= -5;
        }

        world.setData(i, j, k, l, 4);
    }

    public static ChunkCoordinates b(World world, int i, int j, int k, int l) {
        int i1 = world.getData(i, j, k);
        int j1 = BlockDirectional.j(i1);

        for (int k1 = 0; k1 <= 1; ++k1) {
            int l1 = i - a[j1][0] * k1 - 1;
            int i2 = k - a[j1][1] * k1 - 1;
            int j2 = l1 + 2;
            int k2 = i2 + 2;

            for (int l2 = l1; l2 <= j2; ++l2) {
                for (int i3 = i2; i3 <= k2; ++i3) {
                    if (world.w(l2, j - 1, i3) && !world.getMaterial(l2, j, i3).k() && !world.getMaterial(l2, j + 1, i3).k()) {
                        if (l <= 0) {
                            return new ChunkCoordinates(l2, j, i3);
                        }

                        --l;
                    }
                }
            }
        }

        return null;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        if (!f_(l)) {
            super.dropNaturally(world, i, j, k, l, f, 0);
        }
    }

    public int h() {
        return 1;
    }

    public void a(World world, int i, int j, int k, int l, EntityHuman entityhuman) {
        if (entityhuman.abilities.canInstantlyBuild && f_(l)) {
            int i1 = j(l);

            i -= a[i1][0];
            k -= a[i1][1];
            if (world.getTypeId(i, j, k) == this.id) {
                world.setAir(i, j, k);
            }
        }
    }
}
