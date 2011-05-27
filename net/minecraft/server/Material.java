package net.minecraft.server;

public class Material {

    public static final Material AIR = new MaterialTransparent();
    public static final Material EARTH = new Material();
    public static final Material WOOD = (new Material()).f();
    public static final Material STONE = new Material();
    public static final Material ORE = new Material();
    public static final Material WATER = new MaterialLiquid();
    public static final Material LAVA = new MaterialLiquid();
    public static final Material LEAVES = (new Material()).f();
    public static final Material PLANT = new MaterialLogic();
    public static final Material SPONGE = new Material();
    public static final Material CLOTH = (new Material()).f();
    public static final Material FIRE = new MaterialTransparent();
    public static final Material SAND = new Material();
    public static final Material ORIENTABLE = new MaterialLogic();
    public static final Material SHATTERABLE = new Material();
    public static final Material TNT = (new Material()).f();
    public static final Material CORAL = new Material();
    public static final Material ICE = new Material();
    public static final Material SNOW_LAYER = new MaterialLogic();
    public static final Material SNOW_BLOCK = new Material();
    public static final Material CACTUS = new Material();
    public static final Material CLAY = new Material();
    public static final Material PUMPKIN = new Material();
    public static final Material PORTAL = new Material();
    public static final Material CAKE = new Material();
    private boolean canBurn;

    public Material() {}

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

    private Material f() {
        this.canBurn = true;
        return this;
    }

    public boolean isBurnable() {
        return this.canBurn;
    }
}
