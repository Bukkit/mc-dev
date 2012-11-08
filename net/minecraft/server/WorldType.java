package net.minecraft.server;

public class WorldType {

    public static final WorldType[] types = new WorldType[16];
    public static final WorldType NORMAL = (new WorldType(0, "default", 1)).g();
    public static final WorldType FLAT = new WorldType(1, "flat");
    public static final WorldType LARGE_BIOMES = new WorldType(2, "largeBiomes");
    public static final WorldType NORMAL_1_1 = (new WorldType(8, "default_1_1", 0)).a(false);
    private final int f;
    private final String name;
    private final int version;
    private boolean i;
    private boolean j;

    private WorldType(int i, String s) {
        this(i, s, 0);
    }

    private WorldType(int i, String s, int j) {
        this.name = s;
        this.version = j;
        this.i = true;
        this.f = i;
        types[i] = this;
    }

    public String name() {
        return this.name;
    }

    public int getVersion() {
        return this.version;
    }

    public WorldType a(int i) {
        return this == NORMAL && i == 0 ? NORMAL_1_1 : this;
    }

    private WorldType a(boolean flag) {
        this.i = flag;
        return this;
    }

    private WorldType g() {
        this.j = true;
        return this;
    }

    public boolean e() {
        return this.j;
    }

    public static WorldType getType(String s) {
        for (int i = 0; i < types.length; ++i) {
            if (types[i] != null && types[i].name.equalsIgnoreCase(s)) {
                return types[i];
            }
        }

        return null;
    }

    public int f() {
        return this.f;
    }
}
