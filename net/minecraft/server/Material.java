package net.minecraft.server;

public class Material {

    public static final Material AIR = new MaterialTransparent(MaterialMapColor.b);
    public static final Material GRASS = new Material(MaterialMapColor.c);
    public static final Material EARTH = new Material(MaterialMapColor.l);
    public static final Material WOOD = (new Material(MaterialMapColor.o)).j();
    public static final Material STONE = new Material(MaterialMapColor.m);
    public static final Material ORE = new Material(MaterialMapColor.h);
    public static final Material WATER = new MaterialLiquid(MaterialMapColor.n);
    public static final Material LAVA = new MaterialLiquid(MaterialMapColor.f);
    public static final Material LEAVES = (new Material(MaterialMapColor.i)).j().i();
    public static final Material PLANT = new MaterialLogic(MaterialMapColor.i);
    public static final Material SPONGE = new Material(MaterialMapColor.e);
    public static final Material CLOTH = (new Material(MaterialMapColor.e)).j();
    public static final Material FIRE = new MaterialTransparent(MaterialMapColor.b);
    public static final Material SAND = new Material(MaterialMapColor.d);
    public static final Material ORIENTABLE = new MaterialLogic(MaterialMapColor.b);
    public static final Material SHATTERABLE = (new Material(MaterialMapColor.b)).i();
    public static final Material TNT = (new Material(MaterialMapColor.f)).j().i();
    public static final Material CORAL = new Material(MaterialMapColor.i);
    public static final Material ICE = (new Material(MaterialMapColor.g)).i();
    public static final Material SNOW_LAYER = (new MaterialLogic(MaterialMapColor.j)).f().i();
    public static final Material SNOW_BLOCK = new Material(MaterialMapColor.j);
    public static final Material CACTUS = (new Material(MaterialMapColor.i)).i();
    public static final Material CLAY = new Material(MaterialMapColor.k);
    public static final Material PUMPKIN = new Material(MaterialMapColor.i);
    public static final Material PORTAL = new MaterialPortal(MaterialMapColor.b);
    public static final Material CAKE = new Material(MaterialMapColor.b);
    private boolean canBurn;
    private boolean C;
    private boolean D;
    public final MaterialMapColor A;

    public Material(MaterialMapColor materialmapcolor) {
        this.A = materialmapcolor;
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

    private Material i() {
        this.D = true;
        return this;
    }

    private Material j() {
        this.canBurn = true;
        return this;
    }

    public boolean isBurnable() {
        return this.canBurn;
    }

    public Material f() {
        this.C = true;
        return this;
    }

    public boolean isReplacable() {
        return this.C;
    }

    public boolean h() {
        return this.D ? false : this.isSolid();
    }
}
