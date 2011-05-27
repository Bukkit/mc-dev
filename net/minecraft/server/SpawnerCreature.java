package net.minecraft.server;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class SpawnerCreature {

    private static Set b = new HashSet();
    protected static final Class[] a = new Class[] { EntitySpider.class, EntityZombie.class, EntitySkeleton.class};

    public SpawnerCreature() {}

    protected static ChunkPosition a(World world, int i, int j) {
        int k = i + world.k.nextInt(16);
        int l = world.k.nextInt(128);
        int i1 = j + world.k.nextInt(16);

        return new ChunkPosition(k, l, i1);
    }

    public static final int a(World world, boolean flag, boolean flag1) {
        if (!flag && !flag1) {
            return 0;
        } else {
            b.clear();

            int i;
            int j;

            for (i = 0; i < world.d.size(); ++i) {
                EntityHuman entityhuman = (EntityHuman) world.d.get(i);
                int k = MathHelper.b(entityhuman.locX / 16.0D);

                j = MathHelper.b(entityhuman.locZ / 16.0D);
                byte b0 = 8;

                for (int l = -b0; l <= b0; ++l) {
                    for (int i1 = -b0; i1 <= b0; ++i1) {
                        b.add(new ChunkCoordIntPair(l + k, i1 + j));
                    }
                }
            }

            i = 0;
            ChunkCoordinates chunkcoordinates = world.l();
            EnumCreatureType[] aenumcreaturetype = EnumCreatureType.values();

            j = aenumcreaturetype.length;

            for (int j1 = 0; j1 < j; ++j1) {
                EnumCreatureType enumcreaturetype = aenumcreaturetype[j1];

                if ((!enumcreaturetype.d() || flag1) && (enumcreaturetype.d() || flag) && world.a(enumcreaturetype.a()) <= enumcreaturetype.b() * b.size() / 256) {
                    Iterator iterator = b.iterator();

                    label91:
                    while (iterator.hasNext()) {
                        ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) iterator.next();
                        BiomeBase biomebase = world.a().a(chunkcoordintpair);
                        Class[] aclass = biomebase.a(enumcreaturetype);

                        if (aclass != null && aclass.length != 0) {
                            int k1 = world.k.nextInt(aclass.length);
                            ChunkPosition chunkposition = a(world, chunkcoordintpair.a * 16, chunkcoordintpair.b * 16);
                            int l1 = chunkposition.a;
                            int i2 = chunkposition.b;
                            int j2 = chunkposition.c;

                            if (!world.d(l1, i2, j2) && world.getMaterial(l1, i2, j2) == enumcreaturetype.c()) {
                                int k2 = 0;

                                for (int l2 = 0; l2 < 3; ++l2) {
                                    int i3 = l1;
                                    int j3 = i2;
                                    int k3 = j2;
                                    byte b1 = 6;

                                    for (int l3 = 0; l3 < 4; ++l3) {
                                        i3 += world.k.nextInt(b1) - world.k.nextInt(b1);
                                        j3 += world.k.nextInt(1) - world.k.nextInt(1);
                                        k3 += world.k.nextInt(b1) - world.k.nextInt(b1);
                                        if (a(enumcreaturetype, world, i3, j3, k3)) {
                                            float f = (float) i3 + 0.5F;
                                            float f1 = (float) j3;
                                            float f2 = (float) k3 + 0.5F;

                                            if (world.a((double) f, (double) f1, (double) f2, 24.0D) == null) {
                                                float f3 = f - (float) chunkcoordinates.a;
                                                float f4 = f1 - (float) chunkcoordinates.b;
                                                float f5 = f2 - (float) chunkcoordinates.c;
                                                float f6 = f3 * f3 + f4 * f4 + f5 * f5;

                                                if (f6 >= 576.0F) {
                                                    EntityLiving entityliving;

                                                    try {
                                                        entityliving = (EntityLiving) aclass[k1].getConstructor(new Class[] { World.class}).newInstance(new Object[] { world});
                                                    } catch (Exception exception) {
                                                        exception.printStackTrace();
                                                        return i;
                                                    }

                                                    entityliving.c((double) f, (double) f1, (double) f2, world.k.nextFloat() * 360.0F, 0.0F);
                                                    if (entityliving.b()) {
                                                        ++k2;
                                                        world.a((Entity) entityliving);
                                                        a(entityliving, world, f, f1, f2);
                                                        if (k2 >= entityliving.j()) {
                                                            continue label91;
                                                        }
                                                    }

                                                    i += k2;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return i;
        }
    }

    private static boolean a(EnumCreatureType enumcreaturetype, World world, int i, int j, int k) {
        return enumcreaturetype.c() == Material.WATER ? world.getMaterial(i, j, k).isLiquid() && !world.d(i, j + 1, k) : world.d(i, j - 1, k) && !world.d(i, j, k) && !world.getMaterial(i, j, k).isLiquid() && !world.d(i, j + 1, k);
    }

    private static void a(EntityLiving entityliving, World world, float f, float f1, float f2) {
        if (entityliving instanceof EntitySpider && world.k.nextInt(100) == 0) {
            EntitySkeleton entityskeleton = new EntitySkeleton(world);

            entityskeleton.c((double) f, (double) f1, (double) f2, entityliving.yaw, 0.0F);
            world.a((Entity) entityskeleton);
            entityskeleton.b(entityliving);
        } else if (entityliving instanceof EntitySheep) {
            ((EntitySheep) entityliving).a_(EntitySheep.a(world.k));
        }
    }

    public static boolean a(World world, List list) {
        boolean flag = false;
        Pathfinder pathfinder = new Pathfinder(world);
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            EntityHuman entityhuman = (EntityHuman) iterator.next();
            Class[] aclass = a;

            if (aclass != null && aclass.length != 0) {
                boolean flag1 = false;

                for (int i = 0; i < 20 && !flag1; ++i) {
                    int j = MathHelper.b(entityhuman.locX) + world.k.nextInt(32) - world.k.nextInt(32);
                    int k = MathHelper.b(entityhuman.locZ) + world.k.nextInt(32) - world.k.nextInt(32);
                    int l = MathHelper.b(entityhuman.locY) + world.k.nextInt(16) - world.k.nextInt(16);

                    if (l < 1) {
                        l = 1;
                    } else if (l > 128) {
                        l = 128;
                    }

                    int i1 = world.k.nextInt(aclass.length);

                    int j1;

                    for (j1 = l; j1 > 2 && !world.d(j, j1 - 1, k); --j1) {
                        ;
                    }

                    while (!a(EnumCreatureType.MONSTER, world, j, j1, k) && j1 < l + 16 && j1 < 128) {
                        ++j1;
                    }

                    if (j1 < l + 16 && j1 < 128) {
                        float f = (float) j + 0.5F;
                        float f1 = (float) j1;
                        float f2 = (float) k + 0.5F;

                        EntityLiving entityliving;

                        try {
                            entityliving = (EntityLiving) aclass[i1].getConstructor(new Class[] { World.class}).newInstance(new Object[] { world});
                        } catch (Exception exception) {
                            exception.printStackTrace();
                            return flag;
                        }

                        entityliving.c((double) f, (double) f1, (double) f2, world.k.nextFloat() * 360.0F, 0.0F);
                        if (entityliving.b()) {
                            PathEntity pathentity = pathfinder.a(entityliving, entityhuman, 32.0F);

                            if (pathentity != null && pathentity.a > 1) {
                                PathPoint pathpoint = pathentity.c();

                                if (Math.abs((double) pathpoint.a - entityhuman.locX) < 1.5D && Math.abs((double) pathpoint.c - entityhuman.locZ) < 1.5D && Math.abs((double) pathpoint.b - entityhuman.locY) < 1.5D) {
                                    ChunkCoordinates chunkcoordinates = BlockBed.g(world, MathHelper.b(entityhuman.locX), MathHelper.b(entityhuman.locY), MathHelper.b(entityhuman.locZ), 1);

                                    entityliving.c((double) ((float) chunkcoordinates.a + 0.5F), (double) chunkcoordinates.b, (double) ((float) chunkcoordinates.c + 0.5F), 0.0F, 0.0F);
                                    world.a((Entity) entityliving);
                                    a(entityliving, world, (float) chunkcoordinates.a + 0.5F, (float) chunkcoordinates.b, (float) chunkcoordinates.c + 0.5F);
                                    entityhuman.a(true, false);
                                    entityliving.G();
                                    flag = true;
                                    flag1 = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return flag;
    }
}
