package net.minecraft.server;

public class PathfinderGoalMoveTowardsRestriction extends PathfinderGoal {

    private EntityCreature a;
    private double b;
    private double c;
    private double d;
    private float e;

    public PathfinderGoalMoveTowardsRestriction(EntityCreature entitycreature, float f) {
        this.a = entitycreature;
        this.e = f;
        this.a(1);
    }

    public boolean a() {
        if (this.a.aI()) {
            return false;
        } else {
            ChunkCoordinates chunkcoordinates = this.a.aJ();
            Vec3D vec3d = RandomPositionGenerator.a(this.a, 16, 7, this.a.world.getVec3DPool().create((double) chunkcoordinates.x, (double) chunkcoordinates.y, (double) chunkcoordinates.z));

            if (vec3d == null) {
                return false;
            } else {
                this.b = vec3d.c;
                this.c = vec3d.d;
                this.d = vec3d.e;
                return true;
            }
        }
    }

    public boolean b() {
        return !this.a.getNavigation().f();
    }

    public void c() {
        this.a.getNavigation().a(this.b, this.c, this.d, this.e);
    }
}
