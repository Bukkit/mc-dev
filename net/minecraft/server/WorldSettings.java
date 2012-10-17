package net.minecraft.server;

public final class WorldSettings {

    private final long a;
    private final EnumGamemode b;
    private final boolean c;
    private final boolean d;
    private final WorldType e;
    private boolean f;
    private boolean g;
    private String h;

    public WorldSettings(long i, EnumGamemode enumgamemode, boolean flag, boolean flag1, WorldType worldtype) {
        this.h = "";
        this.a = i;
        this.b = enumgamemode;
        this.c = flag;
        this.d = flag1;
        this.e = worldtype;
    }

    public WorldSettings(WorldData worlddata) {
        this(worlddata.getSeed(), worlddata.getGameType(), worlddata.shouldGenerateMapFeatures(), worlddata.isHardcore(), worlddata.getType());
    }

    public WorldSettings a() {
        this.g = true;
        return this;
    }

    public WorldSettings a(String s) {
        this.h = s;
        return this;
    }

    public boolean c() {
        return this.g;
    }

    public long d() {
        return this.a;
    }

    public EnumGamemode e() {
        return this.b;
    }

    public boolean f() {
        return this.d;
    }

    public boolean g() {
        return this.c;
    }

    public WorldType h() {
        return this.e;
    }

    public boolean i() {
        return this.f;
    }

    public static EnumGamemode a(int i) {
        return EnumGamemode.a(i);
    }

    public String j() {
        return this.h;
    }
}
