package net.minecraft.server;

public class MovingObjectPosition {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public Vec3D f;
    public Entity g;

    public MovingObjectPosition(int i, int j, int k, int l, Vec3D vec3d) {
        this.a = 0;
        this.b = i;
        this.c = j;
        this.d = k;
        this.e = l;
        this.f = Vec3D.b(vec3d.a, vec3d.b, vec3d.c);
    }

    public MovingObjectPosition(Entity entity) {
        this.a = 1;
        this.g = entity;
        this.f = Vec3D.b(entity.p, entity.q, entity.r);
    }
}
