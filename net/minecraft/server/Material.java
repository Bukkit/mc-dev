package net.minecraft.server;

public class Material {

    public static final Material AIR = new MaterialGas(MaterialMapColor.b);
    public static final Material GRASS = new Material(MaterialMapColor.c);
    public static final Material EARTH = new Material(MaterialMapColor.l);
    public static final Material WOOD = (new Material(MaterialMapColor.o)).g();
    public static final Material STONE = (new Material(MaterialMapColor.m)).f();
    public static final Material ORE = (new Material(MaterialMapColor.h)).f();
    public static final Material HEAVY = (new Material(MaterialMapColor.h)).f().o();
    public static final Material WATER = (new MaterialLiquid(MaterialMapColor.n)).n();
    public static final Material LAVA = (new MaterialLiquid(MaterialMapColor.f)).n();
    public static final Material LEAVES = (new Material(MaterialMapColor.i)).g().r().n();
    public static final Material PLANT = (new MaterialDecoration(MaterialMapColor.i)).n();
    public static final Material REPLACEABLE_PLANT = (new MaterialDecoration(MaterialMapColor.i)).g().n().i();
    public static final Material SPONGE = new Material(MaterialMapColor.e);
    public static final Material CLOTH = (new Material(MaterialMapColor.e)).g();
    public static final Material FIRE = (new MaterialGas(MaterialMapColor.b)).n();
    public static final Material SAND = new Material(MaterialMapColor.d);
    public static final Material ORIENTABLE = (new MaterialDecoration(MaterialMapColor.b)).n();
    public static final Material SHATTERABLE = (new Material(MaterialMapColor.b)).r().p();
    public static final Material BUILDABLE_GLASS = (new Material(MaterialMapColor.b)).p();
    public static final Material TNT = (new Material(MaterialMapColor.f)).g().r();
    public static final Material CORAL = (new Material(MaterialMapColor.i)).n();
    public static final Material ICE = (new Material(MaterialMapColor.g)).r().p();
    public static final Material SNOW_LAYER = (new MaterialDecoration(MaterialMapColor.j)).i().r().f().n();
    public static final Material SNOW_BLOCK = (new Material(MaterialMapColor.j)).f();
    public static final Material CACTUS = (new Material(MaterialMapColor.i)).r().n();
    public static final Material CLAY = new Material(MaterialMapColor.k);
    public static final Material PUMPKIN = (new Material(MaterialMapColor.i)).n();
    public static final Material DRAGON_EGG = (new Material(MaterialMapColor.i)).n();
    public static final Material PORTAL = (new MaterialPortal(MaterialMapColor.b)).o();
    public static final Material CAKE = (new Material(MaterialMapColor.b)).n();
    public static final Material WEB = (new MaterialWeb(MaterialMapColor.e)).f().n();
    public static final Material PISTON = (new Material(MaterialMapColor.m)).o();
    private boolean canBurn;
    private boolean I;
    private boolean J;
    public final MaterialMapColor G;
    private boolean K = true;
    private int L;
    private boolean M;

    public Material(MaterialMapColor materialmapcolor) {
        this.G = materialmapcolor;
    }

    public boolean isLiquid() {
        return false;
    }

    public boolean isBuildable() {
        return true;
    }

    public boolean blocksLight() {
        return true;
    }

    public boolean isSolid() {
        return true;
    }

    private Material r() {
        this.J = true;
        return this;
    }

    protected Material f() {
        this.K = false;
        return this;
    }

    protected Material g() {
        this.canBurn = true;
        return this;
    }

    public boolean isBurnable() {
        return this.canBurn;
    }

    public Material i() {
        this.I = true;
        return this;
    }

    public boolean isReplaceable() {
        return this.I;
    }

    public boolean k() {
        return this.J ? false : this.isSolid();
    }

    public boolean isAlwaysDestroyable() {
        return this.K;
    }

    public int getPushReaction() {
        return this.L;
    }

    protected Material n() {
        this.L = 1;
        return this;
    }

    protected Material o() {
        this.L = 2;
        return this;
    }

    protected Material p() {
        this.M = true;
        return this;
    }

    public boolean q() {
        return this.M;
    }
}
