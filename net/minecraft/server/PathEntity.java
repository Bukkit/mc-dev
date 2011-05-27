package net.minecraft.server;

public class PathEntity {

    private final PathPoint[] b;
    public final int a;
    private int c;

    public PathEntity(PathPoint[] apathpoint) {
        this.b = apathpoint;
        this.a = apathpoint.length;
    }

    public void a() {
        ++this.c;
    }

    public boolean b() {
        return this.c >= this.b.length;
    }

    public Vec3D a(Entity entity) {
        double d0 = (double) this.b[this.c].a + (double) ((int) (entity.H + 1.0F)) * 0.5D;
        double d1 = (double) this.b[this.c].b;
        double d2 = (double) this.b[this.c].c + (double) ((int) (entity.H + 1.0F)) * 0.5D;

        return Vec3D.b(d0, d1, d2);
    }
}
