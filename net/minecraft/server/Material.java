package net.minecraft.server;

public class Material {

    public static final Material a = new MaterialTransparent();
    public static final Material b = new Material();
    public static final Material c = (new Material()).f();
    public static final Material d = new Material();
    public static final Material e = new Material();
    public static final Material f = new MaterialLiquid();
    public static final Material g = new MaterialLiquid();
    public static final Material h = (new Material()).f();
    public static final Material i = new MaterialLogic();
    public static final Material j = new Material();
    public static final Material k = (new Material()).f();
    public static final Material l = new MaterialTransparent();
    public static final Material m = new Material();
    public static final Material n = new MaterialLogic();
    public static final Material o = new Material();
    public static final Material p = (new Material()).f();
    public static final Material q = new Material();
    public static final Material r = new Material();
    public static final Material s = new MaterialLogic();
    public static final Material t = new Material();
    public static final Material u = new Material();
    public static final Material v = new Material();
    public static final Material w = new Material();
    public static final Material x = new Material();
    private boolean y;

    public Material() {}

    public boolean d() {
        return false;
    }

    public boolean a() {
        return true;
    }

    public boolean b() {
        return true;
    }

    public boolean c() {
        return true;
    }

    private Material f() {
        this.y = true;
        return this;
    }

    public boolean e() {
        return this.y;
    }
}
