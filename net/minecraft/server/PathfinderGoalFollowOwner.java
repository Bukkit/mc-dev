package net.minecraft.server;

public class PathfinderGoalFollowOwner extends PathfinderGoal {

    private EntityTameableAnimal d;
    private EntityLiving e;
    World a;
    private float f;
    private Navigation g;
    private int h;
    float b;
    float c;
    private boolean i;

    public PathfinderGoalFollowOwner(EntityTameableAnimal entitytameableanimal, float f, float f1, float f2) {
        this.d = entitytameableanimal;
        this.a = entitytameableanimal.world;
        this.f = f;
        this.g = entitytameableanimal.al();
        this.c = f1;
        this.b = f2;
        this.a(3);
    }

    public boolean a() {
        EntityLiving entityliving = this.d.getOwner();

        if (entityliving == null) {
            return false;
        } else if (this.d.isSitting()) {
            return false;
        } else if (this.d.j(entityliving) < (double) (this.c * this.c)) {
            return false;
        } else {
            this.e = entityliving;
            return true;
        }
    }

    public boolean b() {
        return !this.g.e() && this.d.j(this.e) > (double) (this.b * this.b) && !this.d.isSitting();
    }

    public void c() {
        this.h = 0;
        this.i = this.d.al().a();
        this.d.al().a(false);
    }

    public void d() {
        this.e = null;
        this.g.f();
        this.d.al().a(this.i);
    }

    public void e() {
        this.d.getControllerLook().a(this.e, 10.0F, (float) this.d.D());
        if (!this.d.isSitting()) {
            if (--this.h <= 0) {
                this.h = 10;
                if (!this.g.a(this.e, this.f)) {
                    if (this.d.j(this.e) >= 144.0D) {
                        int i = MathHelper.floor(this.e.locX) - 2;
                        int j = MathHelper.floor(this.e.locZ) - 2;
                        int k = MathHelper.floor(this.e.boundingBox.b);

                        for (int l = 0; l <= 4; ++l) {
                            for (int i1 = 0; i1 <= 4; ++i1) {
                                if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.a.e(i + l, k - 1, j + i1) && !this.a.e(i + l, k, j + i1) && !this.a.e(i + l, k + 1, j + i1)) {
                                    this.d.setPositionRotation((double) ((float) (i + l) + 0.5F), (double) k, (double) ((float) (j + i1) + 0.5F), this.d.yaw, this.d.pitch);
                                    this.g.f();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
