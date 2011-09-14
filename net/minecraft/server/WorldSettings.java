package net.minecraft.server;

public final class WorldSettings {

    private final long a;
    private final int b;
    private final boolean c;

    public WorldSettings(long i, int j, boolean flag) {
        this.a = i;
        this.b = j;
        this.c = flag;
    }

    public long a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
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
