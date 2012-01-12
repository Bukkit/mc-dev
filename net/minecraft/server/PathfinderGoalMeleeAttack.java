package net.minecraft.server;

public class PathfinderGoalMeleeAttack extends PathfinderGoal {

    World a;
    EntityMonster b;
    EntityLiving c;
    int d = 0;
    float e;

    public PathfinderGoalMeleeAttack(EntityMonster entitymonster, World world, float f) {
        this.b = entitymonster;
        this.a = world;
        this.e = f;
        this.a(3);
    }

    public boolean a() {
        this.c = this.h();
        return this.c != null;
    }

    public void b() {
        this.b.ah().a(this.c, this.b.ar());
        this.b.getControllerLook().a(this.c, 30.0F, 30.0F);
        this.d = Math.max(this.d - 1, 0);
        double d0 = 4.0D;

        if (this.b.i(this.c) <= d0) {
            if (this.d <= 0) {
                this.d = 20;
                this.b.d(this.c);
            }
        }
    }

    private EntityLiving h() {
        Object object = this.b.aj();

        if (object == null) {
            object = this.a.findNearbyVulnerablePlayer(this.b, (double) this.e);
        }

        return (EntityLiving) (object == null ? null : (((EntityLiving) object).boundingBox.e > this.b.boundingBox.b && ((EntityLiving) object).boundingBox.b < this.b.boundingBox.e ? (!this.b.g((Entity) object) ? null : object) : null));
    }
}
