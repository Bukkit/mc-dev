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
        this.b = this.a.getGoalTarget();
        if (this.b == null) {
            return false;
        } else if (this.b.e(this.a) > (double) (this.g * this.g)) {
            return false;
        } else {
            Vec3D vec3d = RandomPositionGenerator.a(this.a, 16, 7, this.a.world.getVec3DPool().create(this.b.locX, this.b.locY, this.b.locZ));

            if (vec3d == null) {
                return false;
            } else {
                this.c = vec3d.c;
                this.d = vec3d.d;
                this.e = vec3d.e;
                return true;
            }
        }
    }

    public boolean b() {
        return !this.a.getNavigation().f() && this.b.isAlive() && this.b.e(this.a) < (double) (this.g * this.g);
    }

    public void d() {
        this.b = null;
    }

    public void c() {
        this.a.getNavigation().a(this.c, this.d, this.e, this.f);
    }
}
