package net.minecraft.server;

public class PathfinderGoalMoveTowardsRestriction extends PathfinderGoal {

    private EntityCreature a;
    private double b;
    private double c;
    private double d;
    private double e;

    public PathfinderGoalMoveTowardsRestriction(EntityCreature entitycreature, double d0) {
        this.a = entitycreature;
        this.e = d0;
        this.a(1);
    }

    public boolean a() {
        if (this.a.bU()) {
            return false;
        } else {
            ChunkCoordinates chunkcoordinates = this.a.bV();
            Vec3D vec3d = RandomPositionGenerator.a(this.a, 16, 7, Vec3D.a((double) chunkcoordinates.x, (double) chunkcoordinates.y, (double) chunkcoordinates.z));

            if (vec3d == null) {
                return false;
            } else {
                this.b = vec3d.a;
                this.c = vec3d.b;
                this.d = vec3d.c;
                return true;
            }
        }
    }

    public boolean b() {
        return !this.a.getNavigation().g();
    }

    public void c() {
        this.a.getNavigation().a(this.b, this.c, this.d, this.e);
    }
}
