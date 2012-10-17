package net.minecraft.server;

public class MovingObjectPosition {

    public EnumMovingObjectType type;
    public int b;
    public int c;
    public int d;
    public int face;
    public Vec3D pos;
    public Entity entity;

    public MovingObjectPosition(int i, int j, int k, int l, Vec3D vec3d) {
        this.type = EnumMovingObjectType.TILE;
        this.b = i;
        this.c = j;
        this.d = k;
        this.face = l;
        this.pos = vec3d.b.create(vec3d.c, vec3d.d, vec3d.e);
    }

    public MovingObjectPosition(Entity entity) {
        this.type = EnumMovingObjectType.ENTITY;
        this.entity = entity;
        this.pos = entity.world.getVec3DPool().create(entity.locX, entity.locY, entity.locZ);
    }
}
