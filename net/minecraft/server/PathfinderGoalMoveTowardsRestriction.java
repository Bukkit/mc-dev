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
        if (this.a.at()) {
            return false;
        } else {
            ChunkCoordinates chunkcoordinates = this.a.au();
            Vec3D vec3d = RandomPositionGenerator.a(this.a, 16, 7, Vec3D.create((double) chunkcoordinates.x, (double) chunkcoordinates.y, (double) chunkcoordinates.z));

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
        return !this.a.ak().e();
    }

    public void c() {
        this.a.ak().a(this.b, this.c, this.d, this.e);
    }
}
