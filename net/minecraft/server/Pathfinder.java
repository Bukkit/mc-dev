package net.minecraft.server;

public class Pathfinder {

    private IBlockAccess a;
    private Path b = new Path();
    private EntityList c = new EntityList();
    private PathPoint[] d = new PathPoint[32];

    public Pathfinder(IBlockAccess iblockaccess) {
        this.a = iblockaccess;
    }

    public PathEntity a(Entity entity, Entity entity1, float f) {
        return this.a(entity, entity1.locX, entity1.boundingBox.b, entity1.locZ, f);
    }

    public PathEntity a(Entity entity, int i, int j, int k, float f) {
        return this.a(entity, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), f);
    }

    private PathEntity a(Entity entity, double d0, double d1, double d2, float f) {
        this.b.a();
        this.c.a();
        PathPoint pathpoint = this.a(MathHelper.floor(entity.boundingBox.a), MathHelper.floor(entity.boundingBox.b), MathHelper.floor(entity.boundingBox.c));
        PathPoint pathpoint1 = this.a(MathHelper.floor(d0 - (double) (entity.length / 2.0F)), MathHelper.floor(d1), MathHelper.floor(d2 - (double) (entity.length / 2.0F)));
        PathPoint pathpoint2 = new PathPoint(MathHelper.d(entity.length + 1.0F), MathHelper.d(entity.width + 1.0F), MathHelper.d(entity.length + 1.0F));
        PathEntity pathentity = this.a(entity, pathpoint, pathpoint1, pathpoint2, f);

        return pathentity;
    }

    private PathEntity a(Entity entity, PathPoint pathpoint, PathPoint pathpoint1, PathPoint pathpoint2, float f) {
        pathpoint.e = 0.0F;
        pathpoint.f = pathpoint.a(pathpoint1);
        pathpoint.g = pathpoint.f;
        this.b.a();
        this.b.a(pathpoint);
        PathPoint pathpoint3 = pathpoint;

        while (!this.b.c()) {
            PathPoint pathpoint4 = this.b.b();

            if (pathpoint4.equals(pathpoint1)) {
                return this.a(pathpoint, pathpoint1);
            }

            if (pathpoint4.a(pathpoint1) < pathpoint3.a(pathpoint1)) {
                pathpoint3 = pathpoint4;
            }

            pathpoint4.i = true;
            int i = this.b(entity, pathpoint4, pathpoint2, pathpoint1, f);

            for (int j = 0; j < i; ++j) {
                PathPoint pathpoint5 = this.d[j];
                float f1 = pathpoint4.e + pathpoint4.a(pathpoint5);

                if (!pathpoint5.a() || f1 < pathpoint5.e) {
                    pathpoint5.h = pathpoint4;
                    pathpoint5.e = f1;
                    pathpoint5.f = pathpoint5.a(pathpoint1);
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

        if (this.a(entity, i, j, k, pathpoint) == 1) {
            pathpoint1 = this.a(i, j, k);
        }

        if (pathpoint1 == null && l > 0 && this.a(entity, i, j + l, k, pathpoint) == 1) {
            pathpoint1 = this.a(i, j + l, k);
            j += l;
        }

        if (pathpoint1 != null) {
            int i1 = 0;
            int j1 = 0;

            while (j > 0 && (j1 = this.a(entity, i, j - 1, k, pathpoint)) == 1) {
                ++i1;
                if (i1 >= 4) {
                    return null;
                }

                --j;
                if (j > 0) {
                    pathpoint1 = this.a(i, j, k);
                }
            }

            if (j1 == -2) {
                return null;
            }
        }

        return pathpoint1;
    }

    private final PathPoint a(int i, int j, int k) {
        int l = PathPoint.a(i, j, k);
        PathPoint pathpoint = (PathPoint) this.c.a(l);

        if (pathpoint == null) {
            pathpoint = new PathPoint(i, j, k);
            this.c.a(l, pathpoint);
        }

        return pathpoint;
    }

    private int a(Entity entity, int i, int j, int k, PathPoint pathpoint) {
        for (int l = i; l < i + pathpoint.a; ++l) {
            for (int i1 = j; i1 < j + pathpoint.b; ++i1) {
                for (int j1 = k; j1 < k + pathpoint.c; ++j1) {
                    int k1 = this.a.getTypeId(l, i1, j1);

                    if (k1 > 0) {
                        if (k1 != Block.IRON_DOOR_BLOCK.id && k1 != Block.WOODEN_DOOR.id) {
                            Material material = Block.byId[k1].material;

                            if (material.isSolid()) {
                                return 0;
                            }

                            if (material == Material.WATER) {
                                return -1;
                            }

                            if (material == Material.LAVA) {
                                return -2;
                            }
                        } else {
                            int l1 = this.a.getData(l, i1, j1);

                            if (!BlockDoor.e(l1)) {
                                return 0;
                            }
                        }
                    }
                }
            }
        }

        return 1;
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
