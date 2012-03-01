package net.minecraft.server;

public class PathfinderGoalPanic extends PathfinderGoal {

    private EntityCreature a;
    private float b;
    private double c;
    private double d;
    private double e;

    public PathfinderGoalPanic(EntityCreature entitycreature, float f) {
        this.a = entitycreature;
        this.b = f;
        this.a(1);
    }

    public boolean a() {
        if (this.a.an() == null) {
            return false;
        } else {
            Vec3D vec3d = RandomPositionGenerator.a(this.a, 5, 4);

            if (vec3d == null) {
                return false;
            } else {
                this.c = vec3d.a;
                this.d = vec3d.b;
                this.e = vec3d.c;
                return true;
            }
        }
    }

    public void c() {
        this.a.ak().a(this.c, this.d, this.e, this.b);
    }

    public boolean b() {
        return !this.a.ak().e();
    }
}
