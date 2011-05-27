package net.minecraft.server;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class SpawnerCreature {

    private static Set a = new HashSet();

    public SpawnerCreature() {}

    protected static ChunkPosition a(World world, int i, int j) {
        int k = i + world.l.nextInt(16);
        int l = world.l.nextInt(128);
        int i1 = j + world.l.nextInt(16);

        return new ChunkPosition(k, l, i1);
    }

    public static final int a(World world, boolean flag, boolean flag1) {
        if (!flag && !flag1) {
            return 0;
        } else {
            a.clear();

            int i;
            int j;
            int k;

            for (i = 0; i < world.d.size(); ++i) {
                EntityHuman entityhuman = (EntityHuman) world.d.get(i);

                j = MathHelper.b(entityhuman.locX / 16.0D);
                k = MathHelper.b(entityhuman.locZ / 16.0D);
                byte b0 = 8;

                for (int l = -b0; l <= b0; ++l) {
                    for (int i1 = -b0; i1 <= b0; ++i1) {
                        a.add(new ChunkCoordIntPair(l + j, i1 + k));
                    }
                }
            }

            i = 0;
            EnumCreatureType[] aenumcreaturetype = EnumCreatureType.values();

            j = aenumcreaturetype.length;

            for (k = 0; k < j; ++k) {
                EnumCreatureType enumcreaturetype = aenumcreaturetype[k];

                if ((!enumcreaturetype.d() || flag1) && (enumcreaturetype.d() || flag) && world.a(enumcreaturetype.a()) <= enumcreaturetype.b() * a.size() / 256) {
                    Iterator iterator = a.iterator();

                    label91:
                    while (iterator.hasNext()) {
                        ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) iterator.next();
                        BiomeBase biomebase = world.a().a(chunkcoordintpair);
                        Class[] aclass = biomebase.a(enumcreaturetype);

                        if (aclass != null && aclass.length != 0) {
                            int j1 = world.l.nextInt(aclass.length);
                            ChunkPosition chunkposition = a(world, chunkcoordintpair.a * 16, chunkcoordintpair.b * 16);
                            int k1 = chunkposition.a;
                            int l1 = chunkposition.b;
                            int i2 = chunkposition.c;

                            if (!world.d(k1, l1, i2) && world.getMaterial(k1, l1, i2) == enumcreaturetype.c()) {
                                int j2 = 0;

                                for (int k2 = 0; k2 < 3; ++k2) {
                                    int l2 = k1;
                                    int i3 = l1;
                                    int j3 = i2;
                                    byte b1 = 6;

                                    for (int k3 = 0; k3 < 4; ++k3) {
                                        l2 += world.l.nextInt(b1) - world.l.nextInt(b1);
                                        i3 += world.l.nextInt(1) - world.l.nextInt(1);
                                        j3 += world.l.nextInt(b1) - world.l.nextInt(b1);
                                        if (a(enumcreaturetype, world, l2, i3, j3)) {
                                            float f = (float) l2 + 0.5F;
                                            float f1 = (float) i3;
                                            float f2 = (float) j3 + 0.5F;

                                            if (world.a((double) f, (double) f1, (double) f2, 24.0D) == null) {
                                                float f3 = f - (float) world.spawnX;
                                                float f4 = f1 - (float) world.spawnY;
                                                float f5 = f2 - (float) world.spawnZ;
                                                float f6 = f3 * f3 + f4 * f4 + f5 * f5;

                                                if (f6 >= 576.0F) {
                                                    EntityLiving entityliving;

                                                    try {
                                                        entityliving = (EntityLiving) aclass[j1].getConstructor(new Class[] { World.class}).newInstance(new Object[] { world});
                                                    } catch (Exception exception) {
                                                        exception.printStackTrace();
                                                        return i;
                                                    }

                                                    entityliving.c((double) f, (double) f1, (double) f2, world.l.nextFloat() * 360.0F, 0.0F);
                                                    if (entityliving.b()) {
                                                        ++j2;
                                                        world.a((Entity) entityliving);
                                                        a(entityliving, world, f, f1, f2);
                                                        if (j2 >= entityliving.j()) {
                                                            continue label91;
                                                        }
                                                    }

                                                    i += j2;
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
        if (entityliving instanceof EntitySpider && world.l.nextInt(100) == 0) {
            EntitySkeleton entityskeleton = new EntitySkeleton(world);

            entityskeleton.c((double) f, (double) f1, (double) f2, entityliving.yaw, 0.0F);
            world.a((Entity) entityskeleton);
            entityskeleton.e(entityliving);
        } else if (entityliving instanceof EntitySheep) {
            ((EntitySheep) entityliving).a(EntitySheep.a(world.l));
        }
    }
}
