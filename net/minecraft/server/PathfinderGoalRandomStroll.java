package net.minecraft.server;

public class PathfinderGoalRandomStroll extends PathfinderGoal {

    private EntityCreature a;
    private double b;
    private double c;
    private double d;
    private double e;

    public PathfinderGoalRandomStroll(EntityCreature entitycreature, double d0) {
        this.a = entitycreature;
        this.e = d0;
        this.a(1);
    }

    public boolean a() {
        if (this.a.aN() >= 100) {
            return false;
        } else if (this.a.aI().nextInt(120) != 0) {
            return false;
        } else {
            Vec3D vec3d = RandomPositionGenerator.a(this.a, 10, 7);

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
        return !this.a.getNavigation().g();
    }

    public void c() {
        this.a.getNavigation().a(this.b, this.c, this.d, this.e);
    }
}
