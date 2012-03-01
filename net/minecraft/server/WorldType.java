package net.minecraft.server;

public class WorldType {

    public static final WorldType[] types = new WorldType[16];
    public static final WorldType NORMAL = (new WorldType(0, "default", 1)).d();
    public static final WorldType FLAT = new WorldType(1, "flat");
    public static final WorldType VERSION_1_1f = (new WorldType(8, "default_1_1", 0)).a(false);
    private final String name;
    private final int version;
    private boolean g;
    private boolean h;

    private WorldType(int i, String s) {
        this(i, s, 0);
    }

    private WorldType(int i, String s, int j) {
        this.name = s;
        this.version = j;
        this.g = true;
        types[i] = this;
    }

    public String name() {
        return this.name;
    }

    public int getVersion() {
        return this.version;
    }

    public WorldType a(int i) {
        return this == NORMAL && i == 0 ? VERSION_1_1f : this;
    }

    private WorldType a(boolean flag) {
        this.g = flag;
        return this;
    }

    private WorldType d() {
        this.h = true;
        return this;
    }

    public boolean c() {
        return this.h;
    }

    public static WorldType getType(String s) {
        for (int i = 0; i < types.length; ++i) {
            if (types[i] != null && types[i].name.equalsIgnoreCase(s)) {
                return types[i];
            }
        }

        return null;
    }
}
