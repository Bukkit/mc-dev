package net.minecraft.server;

public class PathfinderGoalTradeWithPlayer extends PathfinderGoal {

    private EntityVillager a;

    public PathfinderGoalTradeWithPlayer(EntityVillager entityvillager) {
        this.a = entityvillager;
        this.a(5);
    }

    public boolean a() {
        if (!this.a.isAlive()) {
            return false;
        } else if (this.a.M()) {
            return false;
        } else if (!this.a.onGround) {
            return false;
        } else if (this.a.velocityChanged) {
            return false;
        } else {
            EntityHuman entityhuman = this.a.b();

            return entityhuman == null ? false : (this.a.e(entityhuman) > 16.0D ? false : entityhuman.activeContainer instanceof Container);
        }
    }

    public void c() {
        this.a.getNavigation().h();
    }

    public void d() {
        this.a.a_((EntityHuman) null);
    }
}
