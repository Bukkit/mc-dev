package net.minecraft.server;

public class PathfinderNavigation implements Navigation {

    private EntityLiving a;
    private World b;
    private PathEntity c;
    private float d;

    public PathfinderNavigation(EntityLiving entityliving, World world) {
        this.a = entityliving;
        this.b = world;
    }

    public void a(double d0, double d1, double d2, float f) {
        this.c = this.b.a(this.a, (int) d0, (int) d1, (int) d2, 10.0F);
        this.d = f;
    }

    public void a(EntityLiving entityliving, float f) {
        this.c = this.b.findPath(this.a, entityliving, 16.0F);
        this.d = f;
    }

    public void a() {
        if (this.c != null) {
            float f = this.a.width;
            Vec3D vec3d = this.c.a(this.a);

            while (vec3d != null && vec3d.d(this.a.locX, vec3d.b, this.a.locZ) < (double) (f * f)) {
                this.c.a();
                if (this.c.b()) {
                    vec3d = null;
                    this.c = null;
                } else {
                    vec3d = this.c.a(this.a);
                }
            }

            if (vec3d != null) {
                this.a.getControllerMove().a(this.d);
                this.a.getControllerMove().a(vec3d.a, vec3d.b, vec3d.c);
            }
        }
    }

    public boolean b() {
        return this.c == null || this.c.b();
    }
}
