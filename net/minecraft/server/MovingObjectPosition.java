package net.minecraft.server;

public class MovingObjectPosition {

    public EnumMovingObjectType type;
    public int b;
    public int c;
    public int d;
    public int face;
    public Vec3D f;
    public Entity entity;

    public MovingObjectPosition(int i, int j, int k, int l, Vec3D vec3d) {
        this.type = EnumMovingObjectType.TILE;
        this.b = i;
        this.c = j;
        this.d = k;
        this.face = l;
        this.f = Vec3D.create(vec3d.a, vec3d.b, vec3d.c);
    }

    public MovingObjectPosition(Entity entity) {
        this.type = EnumMovingObjectType.ENTITY;
        this.entity = entity;
        this.f = Vec3D.create(entity.locX, entity.locY, entity.locZ);
    }
}
