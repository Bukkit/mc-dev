package net.minecraft.server;

public class Pathfinder {

    private IBlockAccess a;
    private Path b = new Path();
    private IntHashMap c = new IntHashMap();
    private PathPoint[] d = new PathPoint[32];
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;

    public Pathfinder(IBlockAccess iblockaccess, boolean flag, boolean flag1, boolean flag2, boolean flag3) {
        this.a = iblockaccess;
        this.e = flag;
        this.f = flag1;
        this.g = flag2;
        this.h = flag3;
    }

    public PathEntity a(Entity entity, Entity entity1, float f) {
        return this.a(entity, entity1.locX, entity1.boundingBox.b, entity1.locZ, f);
    }

    public PathEntity a(Entity entity, int i, int j, int k, float f) {
        return this.a(entity, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), f);
    }

    private PathEntity a(Entity entity, double d0, double d1, double d2, float f) {
        this.b.a();
        this.c.c();
        boolean flag = this.g;
        int i = MathHelper.floor(entity.boundingBox.b + 0.5D);

        if (this.h && entity.M()) {
            i = (int) entity.boundingBox.b;

            for (Block block = this.a.getType(MathHelper.floor(entity.locX), i, MathHelper.floor(entity.locZ)); block == Blocks.WATER || block == Blocks.STATIONARY_WATER; block = this.a.getType(MathHelper.floor(entity.locX), i, MathHelper.floor(entity.locZ))) {
                ++i;
            }

            flag = this.g;
            this.g = false;
        } else {
            i = MathHelper.floor(entity.boundingBox.b + 0.5D);
        }

        PathPoint pathpoint = this.a(MathHelper.floor(entity.boundingBox.a), i, MathHelper.floor(entity.boundingBox.c));
        PathPoint pathpoint1 = this.a(MathHelper.floor(d0 - (double) (entity.width / 2.0F)), MathHelper.floor(d1), MathHelper.floor(d2 - (double) (entity.width / 2.0F)));
        PathPoint pathpoint2 = new PathPoint(MathHelper.d(entity.width + 1.0F), MathHelper.d(entity.length + 1.0F), MathHelper.d(entity.width + 1.0F));
        PathEntity pathentity = this.a(entity, pathpoint, pathpoint1, pathpoint2, f);

        this.g = flag;
        return pathentity;
    }

    private PathEntity a(Entity entity, PathPoint pathpoint, PathPoint pathpoint1, PathPoint pathpoint2, float f) {
        pathpoint.e = 0.0F;
        pathpoint.f = pathpoint.b(pathpoint1);
        pathpoint.g = pathpoint.f;
        this.b.a();
        this.b.a(pathpoint);
        PathPoint pathpoint3 = pathpoint;

        while (!this.b.e()) {
            PathPoint pathpoint4 = this.b.c();

            if (pathpoint4.equals(pathpoint1)) {
                return this.a(pathpoint, pathpoint1);
            }

            if (pathpoint4.b(pathpoint1) < pathpoint3.b(pathpoint1)) {
                pathpoint3 = pathpoint4;
            }

            pathpoint4.i = true;
            int i = this.b(entity, pathpoint4, pathpoint2, pathpoint1, f);

            for (int j = 0; j < i; ++j) {
                PathPoint pathpoint5 = this.d[j];
                float f1 = pathpoint4.e + pathpoint4.b(pathpoint5);

                if (!pathpoint5.a() || f1 < pathpoint5.e) {
                    pathpoint5.h = pathpoint4;
                    pathpoint5.e = f1;
                    pathpoint5.f = pathpoint5.b(pathpoint1);
                    if (pathpoint5.a()) {
                        this.b.a(pathpoint5, pathpoint5.e + pathpoint5.f);
                    } else {
                        pathpoint5.g = pathpoint5.e + pathpoint5.f;
                        this.b.a(pathpoint5);
                    }
                }
            }
        }

        if (pathpoint3 == pathpoint) {
            return null;
        } else {
            return this.a(pathpoint, pathpoint3);
        }
    }

    private int b(Entity entity, PathPoint pathpoint, PathPoint pathpoint1, PathPoint pathpoint2, float f) {
        int i = 0;
        byte b0 = 0;

        if (this.a(entity, pathpoint.a, pathpoint.b + 1, pathpoint.c, pathpoint1) == 1) {
            b0 = 1;
        }

        PathPoint pathpoint3 = this.a(entity, pathpoint.a, pathpoint.b, pathpoint.c + 1, pathpoint1, b0);
        PathPoint pathpoint4 = this.a(entity, pathpoint.a - 1, pathpoint.b, pathpoint.c, pathpoint1, b0);
        PathPoint pathpoint5 = this.a(entity, pathpoint.a + 1, pathpoint.b, pathpoint.c, pathpoint1, b0);
        PathPoint pathpoint6 = this.a(entity, pathpoint.a, pathpoint.b, pathpoint.c - 1, pathpoint1, b0);

        if (pathpoint3 != null && !pathpoint3.i && pathpoint3.a(pathpoint2) < f) {
            this.d[i++] = pathpoint3;
        }

        if (pathpoint4 != null && !pathpoint4.i && pathpoint4.a(pathpoint2) < f) {
            this.d[i++] = pathpoint4;
        }

        if (pathpoint5 != null && !pathpoint5.i && pathpoint5.a(pathpoint2) < f) {
            this.d[i++] = pathpoint5;
        }

        if (pathpoint6 != null && !pathpoint6.i && pathpoint6.a(pathpoint2) < f) {
            this.d[i++] = pathpoint6;
        }

        return i;
    }

    private PathPoint a(Entity entity, int i, int j, int k, PathPoint pathpoint, int l) {
        PathPoint pathpoint1 = null;
        int i1 = this.a(entity, i, j, k, pathpoint);

        if (i1 == 2) {
            return this.a(i, j, k);
        } else {
            if (i1 == 1) {
                pathpoint1 = this.a(i, j, k);
            }

            if (pathpoint1 == null && l > 0 && i1 != -3 && i1 != -4 && this.a(entity, i, j + l, k, pathpoint) == 1) {
                pathpoint1 = this.a(i, j + l, k);
                j += l;
            }

            if (pathpoint1 != null) {
                int j1 = 0;
                int k1 = 0;

                while (j > 0) {
                    k1 = this.a(entity, i, j - 1, k, pathpoint);
                    if (this.g && k1 == -1) {
                        return null;
                    }

                    if (k1 != 1) {
                        break;
                    }

                    if (j1++ >= entity.ax()) {
                        return null;
                    }

                    --j;
                    if (j > 0) {
                        pathpoint1 = this.a(i, j, k);
                    }
                }

                if (k1 == -2) {
                    return null;
                }
            }

            return pathpoint1;
        }
    }

    private final PathPoint a(int i, int j, int k) {
        int l = PathPoint.a(i, j, k);
        PathPoint pathpoint = (PathPoint) this.c.get(l);

        if (pathpoint == null) {
            pathpoint = new PathPoint(i, j, k);
            this.c.a(l, pathpoint);
        }

        return pathpoint;
    }

    public int a(Entity entity, int i, int j, int k, PathPoint pathpoint) {
        return a(entity, i, j, k, pathpoint, this.g, this.f, this.e);
    }

    public static int a(Entity entity, int i, int j, int k, PathPoint pathpoint, boolean flag, boolean flag1, boolean flag2) {
        boolean flag3 = false;

        for (int l = i; l < i + pathpoint.a; ++l) {
            for (int i1 = j; i1 < j + pathpoint.b; ++i1) {
                for (int j1 = k; j1 < k + pathpoint.c; ++j1) {
                    Block block = entity.world.getType(l, i1, j1);

                    if (block.getMaterial() != Material.AIR) {
                        if (block == Blocks.TRAP_DOOR) {
                            flag3 = true;
                        } else if (block != Blocks.WATER && block != Blocks.STATIONARY_WATER) {
                            if (!flag2 && block == Blocks.WOODEN_DOOR) {
                                return 0;
                            }
                        } else {
                            if (flag) {
                                return -1;
                            }

                            flag3 = true;
                        }

                        int k1 = block.b();

                        if (entity.world.getType(l, i1, j1).b() == 9) {
                            int l1 = MathHelper.floor(entity.locX);
                            int i2 = MathHelper.floor(entity.locY);
                            int j2 = MathHelper.floor(entity.locZ);

                            if (entity.world.getType(l1, i2, j2).b() != 9 && entity.world.getType(l1, i2 - 1, j2).b() != 9) {
                                return -3;
                            }
                        } else if (!block.b(entity.world, l, i1, j1) && (!flag1 || block != Blocks.WOODEN_DOOR)) {
                            if (k1 == 11 || block == Blocks.FENCE_GATE || k1 == 32) {
                                return -3;
                            }

                            if (block == Blocks.TRAP_DOOR) {
                                return -4;
                            }

                            Material material = block.getMaterial();

                            if (material != Material.LAVA) {
                                return 0;
                            }

                            if (!entity.P()) {
                                return -2;
                            }
                        }
                    }
                }
            }
        }

        return flag3 ? 2 : 1;
    }

    private PathEntity a(PathPoint pathpoint, PathPoint pathpoint1) {
        int i = 1;

        PathPoint pathpoint2;

        for (pathpoint2 = pathpoint1; pathpoint2.h != null; pathpoint2 = pathpoint2.h) {
            ++i;
        }

        PathPoint[] apathpoint = new PathPoint[i];

        pathpoint2 = pathpoint1;
        --i;

        for (apathpoint[i] = pathpoint1; pathpoint2.h != null; apathpoint[i] = pathpoint2) {
            pathpoint2 = pathpoint2.h;
            --i;
        }

        return new PathEntity(apathpoint);
    }
}
