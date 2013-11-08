package net.minecraft.server;

public class PathfinderGoalBeg extends PathfinderGoal {

    private EntityWolf a;
    private EntityHuman b;
    private World c;
    private float d;
    private int e;

    public PathfinderGoalBeg(EntityWolf entitywolf, float f) {
        this.a = entitywolf;
        this.c = entitywolf.world;
        this.d = f;
        this.a(2);
    }

    public boolean a() {
        this.b = this.c.findNearbyPlayer(this.a, (double) this.d);
        return this.b == null ? false : this.a(this.b);
    }

    public boolean b() {
        return !this.b.isAlive() ? false : (this.a.e(this.b) > (double) (this.d * this.d) ? false : this.e > 0 && this.a(this.b));
    }

    public void c() {
        this.a.m(true);
        this.e = 40 + this.a.aI().nextInt(40);
    }

    public void d() {
        this.a.m(false);
        this.b = null;
    }

    public void e() {
        this.a.getControllerLook().a(this.b.locX, this.b.locY + (double) this.b.getHeadHeight(), this.b.locZ, 10.0F, (float) this.a.x());
        --this.e;
    }

    private boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        return itemstack == null ? false : (!this.a.isTamed() && itemstack.getItem() == Items.BONE ? true : this.a.c(itemstack));
    }
}
