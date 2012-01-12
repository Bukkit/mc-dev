package net.minecraft.server;

import java.util.Random;

public class PathfinderGoalRandomStroll extends PathfinderGoal {

    private EntityCreature a;
    private double b;
    private double c;
    private double d;

    public PathfinderGoalRandomStroll(EntityCreature entitycreature) {
        this.a = entitycreature;
        this.a(3);
    }

    public boolean a() {
        if (this.a.ak() >= 100) {
            return false;
        } else if (this.a.ai().nextInt(120) != 0) {
            return false;
        } else {
            Vec3D vec3d = this.h();

            if (vec3d == null) {
                return false;
            } else {
                this.b = vec3d.a;
                this.c = vec3d.b;
                this.d = vec3d.c;
                return true;
            }
        }
    }

    public boolean g() {
        return !this.a.ah().b();
    }

    public void e() {
        this.a.ah().a(this.b, this.c, this.d, this.a.ar());
    }

    private Vec3D h() {
        Random random = this.a.ai();
        boolean flag = false;
        int i = -1;
        int j = -1;
        int k = -1;
        float f = -99999.0F;

        for (int l = 0; l < 10; ++l) {
            int i1 = MathHelper.floor(this.a.locX + (double) random.nextInt(13) - 6.0D);
            int j1 = MathHelper.floor(this.a.locY + (double) random.nextInt(7) - 3.0D);
            int k1 = MathHelper.floor(this.a.locZ + (double) random.nextInt(13) - 6.0D);
            float f1 = this.a.a(i1, j1, k1);

            if (f1 > f) {
                f = f1;
                i = i1;
                j = j1;
                k = k1;
                flag = true;
            }
        }

        if (flag) {
            return Vec3D.create((double) i, (double) j, (double) k);
        } else {
            return null;
        }
    }
}
