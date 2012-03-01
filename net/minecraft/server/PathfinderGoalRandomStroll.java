package net.minecraft.server;

public class PathfinderGoalRandomStroll extends PathfinderGoal {

    private EntityCreature a;
    private double b;
    private double c;
    private double d;
    private float e;

    public PathfinderGoalRandomStroll(EntityCreature entitycreature, float f) {
        this.a = entitycreature;
        this.e = f;
        this.a(1);
    }

    public boolean a() {
        if (this.a.ap() >= 100) {
            return false;
        } else if (this.a.am().nextInt(120) != 0) {
            return false;
        } else {
            Vec3D vec3d = RandomPositionGenerator.a(this.a, 10, 7);

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
