package net.minecraft.server;

public abstract class PathfinderGoal {

    private int a;

    public PathfinderGoal() {}

    public abstract boolean a();

    public boolean b() {
        return this.a();
    }

    public boolean i() {
        return true;
    }

    public void c() {}

    public void d() {}

    public void e() {}

    public void a(int i) {
        this.a = i;
    }

    public int j() {
        return this.a;
    }
}
