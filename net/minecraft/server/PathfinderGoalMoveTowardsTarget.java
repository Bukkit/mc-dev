package net.minecraft.server;

public class PathfinderGoalMoveTowardsTarget extends PathfinderGoal {

    private EntityCreature a;
    private EntityLiving b;
    private double c;
    private double d;
    private double e;
    private float f;
    private float g;

    public PathfinderGoalMoveTowardsTarget(EntityCreature entitycreature, float f, float f1) {
        this.a = entitycreature;
        this.f = f;
        this.g = f1;
        this.a(1);
    }

    public boolean a() {
        this.b = this.a.as();
        if (this.b == null) {
            return false;
        } else if (this.b.j(this.a) > (double) (this.g * this.g)) {
            return false;
        } else {
            Vec3D vec3d = RandomPositionGenerator.a(this.a, 16, 7, Vec3D.create(this.b.locX, this.b.locY, this.b.locZ));

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

    public boolean b() {
        return !this.a.ak().e() && this.b.isAlive() && this.b.j(this.a) < (double) (this.g * this.g);
    }

    public void d() {
        this.b = null;
    }

    public void c() {
        this.a.ak().a(this.c, this.d, this.e, this.f);
    }
}
