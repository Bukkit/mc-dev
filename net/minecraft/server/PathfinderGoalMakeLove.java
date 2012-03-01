package net.minecraft.server;

import java.util.Random;

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
        } else if (this.b.am().nextInt(500) != 0) {
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

    public void c() {
        this.e = 300;
        this.b.a(true);
    }

    public void d() {
        this.a = null;
        this.c = null;
        this.b.a(false);
    }

    public boolean b() {
        return this.e >= 0 && this.f() && this.b.getAge() == 0;
    }

    public void e() {
        --this.e;
        this.b.getControllerLook().a(this.c, 10.0F, 30.0F);
        if (this.b.j(this.c) > 2.25D) {
            this.b.ak().a((EntityLiving) this.c, 0.25F);
        } else if (this.e == 0 && this.c.A()) {
            this.i();
        }

        if (this.b.am().nextInt(35) == 0) {
            this.a(this.b);
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
        entityvillager.setProfession(this.b.am().nextInt(5));
        entityvillager.setPositionRotation(this.b.locX, this.b.locY, this.b.locZ, 0.0F, 0.0F);
        this.d.addEntity(entityvillager);
        this.a(entityvillager);
    }

    private void a(EntityLiving entityliving) {
        Random random = entityliving.am();

        for (int i = 0; i < 5; ++i) {
            double d0 = random.nextGaussian() * 0.02D;
            double d1 = random.nextGaussian() * 0.02D;
            double d2 = random.nextGaussian() * 0.02D;

            this.d.a("heart", entityliving.locX + (double) (random.nextFloat() * entityliving.width * 2.0F) - (double) entityliving.width, entityliving.locY + 1.0D + (double) (random.nextFloat() * entityliving.length), entityliving.locZ + (double) (random.nextFloat() * entityliving.width * 2.0F) - (double) entityliving.width, d0, d1, d2);
        }
    }
}
