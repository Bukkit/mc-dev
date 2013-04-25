package net.minecraft.server;

public class PathfinderGoalRestrictOpenDoor extends PathfinderGoal {

    private EntityCreature a;
    private VillageDoor b;

    public PathfinderGoalRestrictOpenDoor(EntityCreature entitycreature) {
        this.a = entitycreature;
    }

    public boolean a() {
        if (this.a.world.v()) {
            return false;
        } else {
            Village village = this.a.world.villages.getClosestVillage(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ), 16);

            if (village == null) {
                return false;
            } else {
                this.b = village.b(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ));
                return this.b == null ? false : (double) this.b.c(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ)) < 2.25D;
            }
        }
    }

    public boolean b() {
        return this.a.world.v() ? false : !this.b.removed && this.b.a(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locZ));
    }

    public void c() {
        this.a.getNavigation().b(false);
        this.a.getNavigation().c(false);
    }

    public void d() {
        this.a.getNavigation().b(true);
        this.a.getNavigation().c(true);
        this.b = null;
    }

    public void e() {
        this.b.e();
    }
}
