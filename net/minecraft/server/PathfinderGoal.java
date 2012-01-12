package net.minecraft.server;

abstract class PathfinderGoal {

    private int a = 0;

    PathfinderGoal() {}

    public abstract boolean a();

    public boolean g() {
        return this.a();
    }

    public boolean f() {
        return true;
    }

    public void e() {}

    public void d() {}

    public void b() {}

    public void a(int i) {
        this.a = i;
    }

    public int c() {
        return this.a;
    }
}
