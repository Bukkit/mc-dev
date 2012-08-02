package net.minecraft.server;

public class PathfinderGoalMakeLove extends PathfinderGoal {

    private EntityVillager b;
    private EntityVillager c;
    private World d;
    private int e = 0;
    Village a;

    public PathfinderGoalMakeLove(EntityVillager entityvillager) {
        this.b = entityvillager;
        this.d = entityvillager.world;
        this.a(3);
    }

    public boolean a() {
        if (this.b.getAge() != 0) {
            return false;
        } else if (this.b.au().nextInt(500) != 0) {
            return false;
        } else {
            this.a = this.d.villages.getClosestVillage(MathHelper.floor(this.b.locX), MathHelper.floor(this.b.locY), MathHelper.floor(this.b.locZ), 0);
            if (this.a == null) {
                return false;
            } else if (!this.f()) {
                return false;
            } else {
                Entity entity = this.d.a(EntityVillager.class, this.b.boundingBox.grow(8.0D, 3.0D, 8.0D), this.b);

                if (entity == null) {
                    return false;
                } else {
                    this.c = (EntityVillager) entity;
                    return this.c.getAge() == 0;
                }
            }
        }
    }

    public void e() {
        this.e = 300;
        this.b.e(true);
    }

    public void c() {
        this.a = null;
        this.c = null;
        this.b.e(false);
    }

    public boolean b() {
        return this.e >= 0 && this.f() && this.b.getAge() == 0;
    }

    public void d() {
        --this.e;
        this.b.getControllerLook().a(this.c, 10.0F, 30.0F);
        if (this.b.e(this.c) > 2.25D) {
            this.b.getNavigation().a((EntityLiving) this.c, 0.25F);
        } else if (this.e == 0 && this.c.o()) {
            this.i();
        }

        if (this.b.au().nextInt(35) == 0) {
            this.d.broadcastEntityEffect(this.b, (byte) 12);
        }
    }

    private boolean f() {
        int i = (int) ((double) ((float) this.a.getDoorCount()) * 0.35D);

        return this.a.getPopulationCount() < i;
    }

    private void i() {
        EntityVillager entityvillager = new EntityVillager(this.d);

        this.c.setAge(6000);
        this.b.setAge(6000);
        entityvillager.setAge(-24000);
        entityvillager.setProfession(this.b.au().nextInt(5));
        entityvillager.setPositionRotation(this.b.locX, this.b.locY, this.b.locZ, 0.0F, 0.0F);
        this.d.addEntity(entityvillager);
        this.d.broadcastEntityEffect(entityvillager, (byte) 12);
    }
}
