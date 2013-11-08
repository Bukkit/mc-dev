package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;

public class BlockBed extends BlockDirectional {

    public static final int[][] a = new int[][] { { 0, 1}, { -1, 0}, { 0, -1}, { 1, 0}};

    public BlockBed() {
        super(Material.CLOTH);
        this.e();
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            int i1 = world.getData(i, j, k);

            if (!b(i1)) {
                int j1 = l(i1);

                i += a[j1][0];
                k += a[j1][1];
                if (world.getType(i, j, k) != this) {
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
                            ChunkCoordinates chunkcoordinates = entityhuman2.bC;

                            if (chunkcoordinates.x == i && chunkcoordinates.y == j && chunkcoordinates.z == k) {
                                entityhuman1 = entityhuman2;
                            }
                        }
                    }

                    if (entityhuman1 != null) {
                        entityhuman.b((IChatBaseComponent) (new ChatMessage("tile.bed.occupied", new Object[0])));
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
                        entityhuman.b((IChatBaseComponent) (new ChatMessage("tile.bed.noSleep", new Object[0])));
                    } else if (enumbedresult == EnumBedResult.NOT_SAFE) {
                        entityhuman.b((IChatBaseComponent) (new ChatMessage("tile.bed.notSafe", new Object[0])));
                    }

                    return true;
                }
            } else {
                double d0 = (double) i + 0.5D;
                double d1 = (double) j + 0.5D;
                double d2 = (double) k + 0.5D;

                world.setAir(i, j, k);
                int k1 = l(i1);

                i += a[k1][0];
                k += a[k1][1];
                if (world.getType(i, j, k) == this) {
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

    public int b() {
        return 14;
    }

    public boolean d() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        this.e();
    }

    public void doPhysics(World world, int i, int j, int k, Block block) {
        int l = world.getData(i, j, k);
        int i1 = l(l);

        if (b(l)) {
            if (world.getType(i - a[i1][0], j, k - a[i1][1]) != this) {
                world.setAir(i, j, k);
            }
        } else if (world.getType(i + a[i1][0], j, k + a[i1][1]) != this) {
            world.setAir(i, j, k);
            if (!world.isStatic) {
                this.b(world, i, j, k, l, 0);
            }
        }
    }

    public Item getDropType(int i, Random random, int j) {
        return b(i) ? Item.d(0) : Items.BED;
    }

    private void e() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
    }

    public static boolean b(int i) {
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

    public static ChunkCoordinates a(World world, int i, int j, int k, int l) {
        int i1 = world.getData(i, j, k);
        int j1 = BlockDirectional.l(i1);

        for (int k1 = 0; k1 <= 1; ++k1) {
            int l1 = i - a[j1][0] * k1 - 1;
            int i2 = k - a[j1][1] * k1 - 1;
            int j2 = l1 + 2;
            int k2 = i2 + 2;

            for (int l2 = l1; l2 <= j2; ++l2) {
                for (int i3 = i2; i3 <= k2; ++i3) {
                    if (World.a((IBlockAccess) world, l2, j - 1, i3) && !world.getType(l2, j, i3).getMaterial().k() && !world.getType(l2, j + 1, i3).getMaterial().k()) {
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
        if (!b(l)) {
            super.dropNaturally(world, i, j, k, l, f, 0);
        }
    }

    public int h() {
        return 1;
    }

    public void a(World world, int i, int j, int k, int l, EntityHuman entityhuman) {
        if (entityhuman.abilities.canInstantlyBuild && b(l)) {
            int i1 = l(l);

            i -= a[i1][0];
            k -= a[i1][1];
            if (world.getType(i, j, k) == this) {
                world.setAir(i, j, k);
            }
        }
    }
}
