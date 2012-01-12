package net.minecraft.server;

public final class WorldSettings {

    private final long a;
    private final int b;
    private final boolean c;
    private final boolean d;
    private final WorldType e;

    public WorldSettings(long i, int j, boolean flag, boolean flag1, WorldType worldtype) {
        this.a = i;
        this.b = j;
        this.c = flag;
        this.d = flag1;
        this.e = worldtype;
    }

    public long a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public boolean c() {
        return this.d;
    }

    public boolean d() {
        return this.c;
    }

    public WorldType e() {
        return this.e;
    }

    public static int a(int i) {
        switch (i) {
        case 0:
        case 1:
            return i;

        default:
            return 0;
        }
    }
}
