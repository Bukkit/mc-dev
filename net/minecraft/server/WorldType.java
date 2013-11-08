package net.minecraft.server;

public class WorldType {

    public static final WorldType[] types = new WorldType[16];
    public static final WorldType NORMAL = (new WorldType(0, "default", 1)).i();
    public static final WorldType FLAT = new WorldType(1, "flat");
    public static final WorldType LARGE_BIOMES = new WorldType(2, "largeBiomes");
    public static final WorldType AMPLIFIED = (new WorldType(3, "amplified")).j();
    public static final WorldType NORMAL_1_1 = (new WorldType(8, "default_1_1", 0)).a(false);
    private final int g;
    private final String name;
    private final int version;
    private boolean j;
    private boolean k;
    private boolean l;

    private WorldType(int i, String s) {
        this(i, s, 0);
    }

    private WorldType(int i, String s, int j) {
        this.name = s;
        this.version = j;
        this.j = true;
        this.g = i;
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
        this.j = flag;
        return this;
    }

    private WorldType i() {
        this.k = true;
        return this;
    }

    public boolean f() {
        return this.k;
    }

    public static WorldType getType(String s) {
        for (int i = 0; i < types.length; ++i) {
            if (types[i] != null && types[i].name.equalsIgnoreCase(s)) {
                return types[i];
            }
        }

        return null;
    }

    public int g() {
        return this.g;
    }

    private WorldType j() {
        this.l = true;
        return this;
    }
}
