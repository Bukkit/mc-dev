package net.minecraft.server;

public class PathfinderGoalTame extends PathfinderGoal {

    private EntityHorse a;
    private double b;
    private double c;
    private double d;
    private double e;

    public PathfinderGoalTame(EntityHorse entityhorse, double d0) {
        this.a = entityhorse;
        this.b = d0;
        this.a(1);
    }

    public boolean a() {
        if (!this.a.bS() && this.a.passenger != null) {
            Vec3D vec3d = RandomPositionGenerator.a(this.a, 5, 4);

            if (vec3d == null) {
                return false;
            } else {
                this.c = vec3d.c;
                this.d = vec3d.d;
                this.e = vec3d.e;
                return true;
            }
        } else {
            return false;
        }
    }

    public void c() {
        this.a.getNavigation().a(this.c, this.d, this.e, this.b);
    }

    public boolean b() {
        return !this.a.getNavigation().g() && this.a.passenger != null;
    }

    public void e() {
        if (this.a.aB().nextInt(50) == 0) {
            if (this.a.passenger instanceof EntityHuman) {
                int i = this.a.cg();
                int j = this.a.cm();

                if (j > 0 && this.a.aB().nextInt(j) < i) {
                    this.a.g((EntityHuman) this.a.passenger);
                    this.a.world.broadcastEntityEffect(this.a, (byte) 7);
                    return;
                }

                this.a.t(5);
            }

            this.a.passenger.mount((Entity) null);
            this.a.passenger = null;
            this.a.cz();
            this.a.world.broadcastEntityEffect(this.a, (byte) 6);
        }
    }
}
