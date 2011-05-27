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
        return this.a(entity, entity1.p, entity1.z.b, entity1.r, f);
    }

    public PathEntity a(Entity entity, int i, int j, int k, float f) {
        return this.a(entity, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), f);
    }

    private PathEntity a(Entity entity, double d0, double d1, double d2, float f) {
        this.b.a();
        this.c.a();
        PathPoint pathpoint = this.a(MathHelper.b(entity.z.a), MathHelper.b(entity.z.b), MathHelper.b(entity.z.c));
        PathPoint pathpoint1 = this.a(MathHelper.b(d0 - (double) (entity.H / 2.0F)), MathHelper.b(d1), MathHelper.b(d2 - (double) (entity.H / 2.0F)));
        PathPoint pathpoint2 = new PathPoint(MathHelper.d(entity.H + 1.0F), MathHelper.d(entity.I + 1.0F), MathHelper.d(entity.H + 1.0F));
        PathEntity pathentity = this.a(entity, pathpoint, pathpoint1, pathpoint2, f);

        return pathentity;
    }

    private PathEntity a(Entity entity, PathPoint pathpoint, PathPoint pathpoint1, PathPoint pathpoint2, float f) {
        pathpoint.f = 0.0F;
        pathpoint.g = pathpoint.a(pathpoint1);
        pathpoint.h = pathpoint.g;
        this.b.a();
        this.b.a(pathpoint);
        PathPoint pathpoint3 = pathpoint;

        while (!this.b.c()) {
            PathPoint pathpoint4 = this.b.b();

            if (pathpoint4.d == pathpoint1.d) {
                return this.a(pathpoint, pathpoint1);
            }

            if (pathpoint4.a(pathpoint1) < pathpoint3.a(pathpoint1)) {
                pathpoint3 = pathpoint4;
            }

            pathpoint4.j = true;
            int i = this.b(entity, pathpoint4, pathpoint2, pathpoint1, f);

            for (int j = 0; j < i; ++j) {
                PathPoint pathpoint5 = this.d[j];
                float f1 = pathpoint4.f + pathpoint4.a(pathpoint5);

                if (!pathpoint5.a() || f1 < pathpoint5.f) {
                    pathpoint5.i = pathpoint4;
                    pathpoint5.f = f1;
                    pathpoint5.g = pathpoint5.a(pathpoint1);
                    if (pathpoint5.a()) {
                        this.b.a(pathpoint5, pathpoint5.f + pathpoint5.g);
                    } else {
                        pathpoint5.h = pathpoint5.f + pathpoint5.g;
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

        if (this.a(entity, pathpoint.a, pathpoint.b + 1, pathpoint.c, pathpoint1) > 0) {
            b0 = 1;
        }

        PathPoint pathpoint3 = this.a(entity, pathpoint.a, pathpoint.b, pathpoint.c + 1, pathpoint1, b0);
        PathPoint pathpoint4 = this.a(entity, pathpoint.a - 1, pathpoint.b, pathpoint.c, pathpoint1, b0);
        PathPoint pathpoint5 = this.a(entity, pathpoint.a + 1, pathpoint.b, pathpoint.c, pathpoint1, b0);
        PathPoint pathpoint6 = this.a(entity, pathpoint.a, pathpoint.b, pathpoint.c - 1, pathpoint1, b0);

        if (pathpoint3 != null && !pathpoint3.j && pathpoint3.a(pathpoint2) < f) {
            this.d[i++] = pathpoint3;
        }

        if (pathpoint4 != null && !pathpoint4.j && pathpoint4.a(pathpoint2) < f) {
            this.d[i++] = pathpoint4;
        }

        if (pathpoint5 != null && !pathpoint5.j && pathpoint5.a(pathpoint2) < f) {
            this.d[i++] = pathpoint5;
        }

        if (pathpoint6 != null && !pathpoint6.j && pathpoint6.a(pathpoint2) < f) {
            this.d[i++] = pathpoint6;
        }

        return i;
    }

    private PathPoint a(Entity entity, int i, int j, int k, PathPoint pathpoint, int l) {
        PathPoint pathpoint1 = null;

        if (this.a(entity, i, j, k, pathpoint) > 0) {
            pathpoint1 = this.a(i, j, k);
        }

        if (pathpoint1 == null && this.a(entity, i, j + l, k, pathpoint) > 0) {
            pathpoint1 = this.a(i, j + l, k);
            j += l;
        }

        if (pathpoint1 != null) {
            int i1 = 0;

            int j1;

            for (boolean flag = false; j > 0 && (j1 = this.a(entity, i, j - 1, k, pathpoint)) > 0; --j) {
                if (j1 < 0) {
                    return null;
                }

                ++i1;
                if (i1 >= 4) {
                    return null;
                }
            }

            if (j > 0) {
                pathpoint1 = this.a(i, j, k);
            }
        }

        return pathpoint1;
    }

    private final PathPoint a(int i, int j, int k) {
        int l = i | j << 10 | k << 20;
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
                    Material material = this.a.c(i, j, k);

                    if (material.c()) {
                        return 0;
                    }

                    if (material == Material.f || material == Material.g) {
                        return -1;
                    }
                }
            }
        }

        return 1;
    }

    private PathEntity a(PathPoint pathpoint, PathPoint pathpoint1) {
        int i = 1;

        PathPoint pathpoint2;

        for (pathpoint2 = pathpoint1; pathpoint2.i != null; pathpoint2 = pathpoint2.i) {
            ++i;
        }

        PathPoint[] apathpoint = new PathPoint[i];

        pathpoint2 = pathpoint1;
        --i;

        for (apathpoint[i] = pathpoint1; pathpoint2.i != null; apathpoint[i] = pathpoint2) {
            pathpoint2 = pathpoint2.i;
            --i;
        }

        return new PathEntity(apathpoint);
    }
}
